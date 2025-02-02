//package com.rhseung.alloy.block
//
//import com.rhseung.alloy.init.ModBlockEntityTypes
//import com.rhseung.alloy.init.ModComponentTypes
//import com.rhseung.alloy.metal.AlloyMixingRatioComponent
//import net.minecraft.block.BlockState
//import net.minecraft.block.entity.BlockEntity
//import net.minecraft.component.ComponentMap
//import net.minecraft.util.math.BlockPos
//
//class AlloyBlockEntity(
//    val alloyMixingRatioComponent: AlloyMixingRatioComponent,
//    val pos: BlockPos,
//    val state: BlockState
//) : BlockEntity(ModBlockEntityTypes.ALLOY_BLOCK, pos, state) {
//
//    override fun getComponents(): ComponentMap {
//        return ComponentMap.builder().add(ModComponentTypes.ALLOY_MIXING_RATIO, alloyMixingRatioComponent).build();
//    }
//
//    fun getBlock(): AlloyBlock {
//        return state.block as AlloyBlock;
//    }
//}