package com.rhseung.alloy.mixin;

import com.rhseung.alloy.custom.fluid.MoltenMetalFluid;
import com.rhseung.alloy.init.ModFluidTags;
import com.rhseung.alloy.metal.AlloyMixingRatioComponent;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(FluidBlock.class)
public abstract class FluidBlockMixin {
    @Shadow protected abstract void playExtinguishSound(WorldAccess world, BlockPos pos);

    @Shadow @Final protected FlowableFluid fluid;

    @Shadow protected abstract FluidState getFluidState(BlockState state);

    @Unique
    private boolean receiveNeighborFluidsModified(World world, BlockPos thisPos, BlockState state) {
        // TODO: 더 확장 가능하도록 빌더로 만들기

        if (this.fluid.isIn(FluidTags.LAVA) && !this.fluid.isIn(ModFluidTags.INSTANCE.getMOLTEN_METAL())) {
            boolean bl = world.getBlockState(thisPos.down()).isOf(Blocks.SOUL_SOIL);

            for (Direction direction : FluidBlock.FLOW_DIRECTIONS) {
                BlockPos thatPos = thisPos.offset(direction.getOpposite());

                if (world.getFluidState(thatPos).isIn(FluidTags.WATER)) {
                    Block block = world.getFluidState(thisPos).isStill() ? Blocks.OBSIDIAN : Blocks.COBBLESTONE;
                    world.setBlockState(thisPos, block.getDefaultState());
                    this.playExtinguishSound(world, thisPos);
                    return false;
                }

                if (bl && world.getBlockState(thatPos).isOf(Blocks.BLUE_ICE)) {
                    world.setBlockState(thisPos, Blocks.BASALT.getDefaultState());
                    this.playExtinguishSound(world, thisPos);
                    return false;
                }
            }
        }
        else if (this.fluid.isIn(ModFluidTags.INSTANCE.getMOLTEN_METAL())) {
            var motlenMetalFluid = (MoltenMetalFluid) this.fluid;

            for (Direction direction : FluidBlock.FLOW_DIRECTIONS) {
                BlockPos thatPos = thisPos.offset(direction.getOpposite());
                FluidState thisFluidState = this.getFluidState(state);
                FluidState thatFluidState = world.getFluidState(thatPos);

                if (thatFluidState.isIn(FluidTags.WATER)) {
                    Block block = world.getFluidState(thisPos).isStill() ? motlenMetalFluid.getMetal().getStorageBlock() : Blocks.COBBLESTONE;
                    world.setBlockState(thisPos, block.getDefaultState());
                    this.playExtinguishSound(world, thisPos);
                    return false;
                }
                else if (thatFluidState.isIn(ModFluidTags.INSTANCE.getMOLTEN_METAL())) {
                    MoltenMetalFluid thisFluid = (MoltenMetalFluid) thisFluidState.getFluid();
                    MoltenMetalFluid thatFluid = (MoltenMetalFluid) thatFluidState.getFluid();

                    int thisLevel = thisFluidState.getLevel();
                    int thatLevel = thatFluidState.getLevel();

                    int thisRatio = (int) (((float) thisLevel) / (thisLevel + thatLevel) * 100);

                    AlloyMixingRatioComponent component = new AlloyMixingRatioComponent(Map.of(
                        thisFluid.getMetal(), thisRatio,
                        thatFluid.getMetal(), 100 - thisRatio
                    ));

                    world.

                    return false;
                }

                // TODO: alloy + molten, alloy + alloy
            }
        }

        return true;
    }

    @Inject(
        method = "receiveNeighborFluids(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z",
        at = @At("HEAD"),
        cancellable = true
    )
    public void receiveNeighborFluidsMixin(World world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(this.receiveNeighborFluidsModified(world, pos, state));
    }
}
