//package com.rhseung.alloy.mixin;
//
//import com.llamalad7.mixinextras.injector.ModifyReturnValue;
//import com.rhseung.alloy.init.ModFluidTags;
//import com.rhseung.alloy.util.Utils;
//import net.minecraft.entity.Entity;
//import net.minecraft.registry.tag.FluidTags;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//
//@Mixin(Entity.class)
//public class EntityMixin {
//    @ModifyReturnValue(
//        method = "isInLava()Z",
//        at = @At("RETURN")
//    )
//    public boolean isInLavaMixin(boolean original) {
//        var that = (Entity) (Object) this;
//        boolean firstUpdate = (boolean) Utils.INSTANCE.get(that, "firstUpdate");
//        return !firstUpdate &&
//                (that.getFluidHeight(FluidTags.LAVA) > 0.0 || that.getFluidHeight(ModFluidTags.INSTANCE.getMOLTEN_METAL()) > 0.0);
//    }
//}
