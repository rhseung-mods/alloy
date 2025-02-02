//package com.rhseung.alloy.block
//
//import com.rhseung.alloy.metal.AlloyMixingRatioComponent
//import com.rhseung.alloy.util.Utils
//import net.minecraft.block.BlockEntityProvider
//import net.minecraft.block.BlockState
//import net.minecraft.block.entity.BlockEntity
//import net.minecraft.item.ItemGroup
//import net.minecraft.registry.RegistryKey
//import net.minecraft.util.math.BlockPos
//
//class AlloyBlock(
//    val alloyMixingRatioComponent: AlloyMixingRatioComponent,
//    itemGroup: RegistryKey<ItemGroup>? = null,
//    settings: (Settings) -> Settings = { it }
//) : SmartBlock(Utils.alloyName(alloyMixingRatioComponent.mixingRatio), itemGroup, settings = settings), BlockEntityProvider {
//
//    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
//        return AlloyBlockEntity(alloyMixingRatioComponent, pos, state)
//    }
//}