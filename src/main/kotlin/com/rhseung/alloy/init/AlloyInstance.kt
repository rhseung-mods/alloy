package com.rhseung.alloy.init

//import com.rhseung.alloy.block.AlloyBlock
import com.rhseung.alloy.custom.block.SmartFluidBlock
import com.rhseung.alloy.custom.fluid.AlloyFluid
import com.rhseung.alloy.custom.item.SmartBucketItem
import com.rhseung.alloy.metal.AlloyMixingRatioComponent

object AlloyInstance : IModInit {
//    val ALLOY_NUGGET = AlloyItem(AlloyMixingRatioComponent.DEFAULT);
//    val ALLOY_INGOT = AlloyItem(AlloyMixingRatioComponent.DEFAULT);
//    val ALLOY_BLOCK = AlloyBlock(AlloyMixingRatioComponent.DEFAULT);

    data class Instance(
        val stillFluid: AlloyFluid.Still,
        val flowingFluid: AlloyFluid.Flowing,
        val fluidBlock: SmartFluidBlock,
        val bucket: SmartBucketItem
    );

    val INSTANCES = mutableMapOf<AlloyMixingRatioComponent, Instance>();

//    val STILL_FLUID = mutableMapOf<AlloyMixingRatioComponent, AlloyFluid.Still, AlloyFluid.Flowing>();
//    val FLOWING_FLUID = mutableMapOf<AlloyMixingRatioComponent,>();
//    val FLUID_BLOCK = mutableMapOf<AlloyMixingRatioComponent, SmartFluidBlock>();
//    val BUCKET_ITEM = mutableMapOf<AlloyMixingRatioComponent, SmartBucketItem>();
}