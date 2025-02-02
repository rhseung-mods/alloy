package com.rhseung.alloy.custom.item

import com.rhseung.alloy.custom.fluid.SmartFluid
import com.rhseung.alloy.metal.AlloyMixingRatioComponent
import com.rhseung.alloy.init.ModComponentTypes
import com.rhseung.alloy.util.Utils
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey

class AlloyBucketItem(
    fluid: SmartFluid,
    alloyMixingRatioComponent: AlloyMixingRatioComponent,
    itemGroup: RegistryKey<ItemGroup>? = null,
    settings: (Settings) -> Settings = { it.component(ModComponentTypes.ALLOY_MIXING_RATIO, alloyMixingRatioComponent) }
) : SmartBucketItem(Utils.alloyName(alloyMixingRatioComponent.mixingRatio), fluid, itemGroup, settings = settings) {
    
}