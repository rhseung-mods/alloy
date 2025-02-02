package com.rhseung.alloy.custom.item

import com.rhseung.alloy.metal.AlloyMixingRatioComponent
import com.rhseung.alloy.init.ModComponentTypes
import com.rhseung.alloy.util.Utils
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey

class AlloyItem(
    alloyMixingRatioComponent: AlloyMixingRatioComponent,
    itemGroup: RegistryKey<ItemGroup>? = null,
    displayName: String? = null,
    settings: (Settings) -> Settings = { it.component(ModComponentTypes.ALLOY_MIXING_RATIO, alloyMixingRatioComponent) }
) : SmartItem(Utils.alloyName(alloyMixingRatioComponent.mixingRatio), itemGroup, displayName, settings = settings) {

}