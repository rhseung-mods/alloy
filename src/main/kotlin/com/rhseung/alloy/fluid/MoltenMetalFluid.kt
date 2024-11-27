package com.rhseung.alloy.fluid

import com.rhseung.alloy.init.Metal
import com.rhseung.alloy.init.ModFluidTags
import net.minecraft.block.AbstractFireBlock
import net.minecraft.block.BlockState
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.BlockView
import net.minecraft.world.GameRules
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import java.util.*

abstract class MoltenMetalFluid(
    name: String,
    val metal: Metal
) : SmartFluid(name) {

    override fun getStill(): Fluid {
        return metal.moltenFluid;
    }

    override fun getFlowing(): Fluid {
        return metal.moltenFluidFlowing;
    }

    override fun getBucketItem(): Item {
        return metal.bucketItem;
    }

    override fun canBeReplacedWith(
        state: FluidState,
        world: BlockView,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction
    ): Boolean {
        return direction == Direction.DOWN && !fluid.isIn(ModFluidTags.MOLTEN_METAL);
    }

    override fun matchesType(fluid: Fluid): Boolean {
        return fluid == metal.moltenFluid || fluid == metal.moltenFluidFlowing;
    }

    override fun getTickRate(world: WorldView): Int {
        return 30;
    }

    override fun getBlastResistance(): Float {
        return 100.0F;
    }

    override fun toBlockState(state: FluidState): BlockState {
        return metal.moltenFluidBlock.defaultState.with(FluidBlock.LEVEL, getBlockStateLevel(state));
    }

    override fun isInfinite(world: ServerWorld): Boolean {
        return false;
    }

    /**
     * Ref: [net.minecraft.fluid.LavaFluid#playExtinguishEvent]
     */
    override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
        world.syncWorldEvent(1501, pos, 0);
    }

    override fun getMaxFlowDistance(world: WorldView): Int {
        return 2;
    }

    override fun getLevelDecreasePerBlock(world: WorldView): Int {
        return 4 / this.getMaxFlowDistance(world);
    }

    override fun getBucketFillSound(): Optional<SoundEvent> {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL_LAVA);
    }

    public override fun onRandomTick(world: ServerWorld, pos: BlockPos, state: FluidState?, random: Random) {
        if (world.gameRules.getBoolean(GameRules.DO_FIRE_TICK)) {
            val i = random.nextInt(3)
            if (i > 0) {
                var blockPos = pos

                for (j in 0..<i) {
                    blockPos = blockPos.add(random.nextInt(3) - 1, 1, random.nextInt(3) - 1)
                    if (!world.isPosLoaded(blockPos)) {
                        return
                    }

                    val blockState = world.getBlockState(blockPos)
                    if (blockState.isAir) {
                        if (this.canLightFire(world, blockPos)) {
                            world.setBlockState(blockPos, AbstractFireBlock.getState(world, blockPos))
                            return
                        }
                    } else if (blockState.blocksMovement()) {
                        return
                    }
                }
            } else {
                for (k in 0..2) {
                    val blockPos2 = pos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1)
                    if (!world.isPosLoaded(blockPos2)) {
                        return
                    }

                    if (world.isAir(blockPos2.up()) && this.hasBurnableBlock(world, blockPos2)) {
                        world.setBlockState(blockPos2.up(), AbstractFireBlock.getState(world, blockPos2))
                    }
                }
            }
        }
    }

    private fun canLightFire(world: WorldView, pos: BlockPos): Boolean {
        for (direction in Direction.entries) {
            if (this.hasBurnableBlock(world, pos.offset(direction))) {
                return true
            }
        }

        return false
    }

    private fun hasBurnableBlock(world: WorldView, pos: BlockPos): Boolean {
        return if (world.isInHeightLimit(pos.y) && !world.isChunkLoaded(pos)) false else world.getBlockState(pos).isBurnable
    }

    class Still(name: String, metal: Metal) : MoltenMetalFluid(name, metal) {
        override fun isStill(state: FluidState): Boolean {
            return true;
        }

        override fun getLevel(state: FluidState): Int {
            return 8;
        }
    }

    class Flowing(name: String, metal: Metal) : MoltenMetalFluid(name, metal) {
        override fun appendProperties(builder: StateManager.Builder<Fluid, FluidState>) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        override fun isStill(state: FluidState): Boolean {
            return false;
        }

        override fun getLevel(state: FluidState): Int {
            return state.get(LEVEL);
        }
    }
}