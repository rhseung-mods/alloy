//package com.rhseung.alloy.mixin;
//
//import com.rhseung.alloy.init.ModFluidTags;
//import net.minecraft.client.render.Camera;
//import net.minecraft.fluid.Fluid;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.registry.tag.TagKey;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Redirect;
//
//@Mixin(Camera.class)
//public class CameraMixin {
//	@Redirect(
//		method = "getSubmersionType()Lnet/minecraft/block/enums/CameraSubmersionType;",
//		at = @At(
//			value = "INVOKE",
//			target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z",
//			ordinal = 1
//		)
//	)
//	private boolean isInMixin(FluidState state, TagKey<Fluid> tag) {
//		return state.isIn(tag) || state.isIn(ModFluidTags.INSTANCE.getMOLTEN_METAL());
//	}
//}