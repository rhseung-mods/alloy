package com.rhseung.alloy.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.rhseung.alloy.init.Metal;
import com.rhseung.alloy.util.Utils;
import kotlin.Triple;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Arrays;
import java.util.Objects;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {
    @ModifyReturnValue(
        method = "getFogColor(Lnet/minecraft/client/render/Camera;FLnet/minecraft/client/world/ClientWorld;IF)Lorg/joml/Vector4f;",
        at = @At("RETURN")
    )
    private static Vector4f getFogColorMixin(Vector4f original, @Local CameraSubmersionType cameraSubmersionType, @Local(argsOnly = true) Camera camera) {
        if (cameraSubmersionType != CameraSubmersionType.LAVA)
            return original;

        Camera.Projection projection = camera.getProjection();
        Vec3d center = projection.getPosition(0, 0);
        BlockView area = (BlockView) Objects.requireNonNull(Utils.INSTANCE.get(camera, "area"));

        Metal metalMatched = null;
        loop: for (Metal metal : Metal.getEntries()) {
            for (Vec3d vec3d : Arrays.asList(center, projection.getBottomRight(), projection.getTopRight(), projection.getBottomLeft(), projection.getTopLeft())) {
                Vec3d vec3d2 = camera.getPos().add(vec3d);
                BlockPos blockPos = BlockPos.ofFloored(vec3d2);
                FluidState fluidState2 = area.getFluidState(blockPos);

                if (vec3d2.y <= (double) (fluidState2.getHeight(area, blockPos) + (float) blockPos.getY())) {
                    if (metal.getMoltenFluid().matchesType(fluidState2.getFluid())) {
                        metalMatched = metal;
                        break loop;
                    }
                }
            }
        }

        if (metalMatched == null)
            return original;

        Triple<Float, Float, Float> color = metalMatched.getColor().toFloatSize();
        return new Vector4f(color.getFirst(), color.getSecond(), color.getThird(), original.w);
    }
}