package com.rhseung.alloy.mixin;

import com.rhseung.alloy.init.ModDamageTypes;
import com.rhseung.alloy.init.ModFluidTags;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.fluid.Fluid;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow protected boolean firstUpdate;

    @Shadow protected Object2DoubleMap<TagKey<Fluid>> fluidHeight;

    @Shadow public abstract DamageSources getDamageSources();

    @Shadow abstract void checkWaterState();

    @Shadow public abstract World getWorld();

    @Shadow public abstract boolean updateMovementInFluid(TagKey<Fluid> tag, double speed);

    @Shadow public abstract boolean isTouchingWater();

    @Unique
    private boolean isInMoltenMetal() {
        return !this.firstUpdate && this.fluidHeight.getDouble(ModFluidTags.INSTANCE.getMOLTEN_METAL()) > (double)0.0F;
    }

    @Redirect(
        method = "setOnFireFromLava()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"
        )
    )
    public boolean damageMixin(Entity entity, ServerWorld serverWorld, DamageSource damageSource, float v) {    // v = 4.0F
        if (this.isInMoltenMetal()) // TODO: 온도 추가해서 데미지에 적용
            return entity.damage(serverWorld, this.getDamageSources().create(ModDamageTypes.INSTANCE.getMELTING()), v * 5 / 4);
        else
            return entity.damage(serverWorld, damageSource, v);
    }

    @Inject(
        method = "updateWaterState()Z",
        at = @At("HEAD"),
        cancellable = true
    )
    public void updateWaterStateMixin(CallbackInfoReturnable<Boolean> cir) {
        this.fluidHeight.clear();
        this.checkWaterState();
        double d = this.getWorld().getDimension().ultrawarm() ? 0.007 : 0.0023333333333333335;  // TODO: 점성 추가해서 속도에 적용
        boolean bl = this.updateMovementInFluid(FluidTags.LAVA, d);
        boolean bl2 = this.updateMovementInFluid(ModFluidTags.INSTANCE.getMOLTEN_METAL(), d);
        cir.setReturnValue(this.isTouchingWater() || bl || bl2);
    }
}
