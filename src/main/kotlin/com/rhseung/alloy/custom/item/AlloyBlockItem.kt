package com.rhseung.alloy.custom.item

import com.rhseung.alloy.custom.block.SmartBlock
import com.rhseung.alloy.metal.AlloyMixingRatioComponent
import com.rhseung.alloy.init.ModComponentTypes

class AlloyBlockItem(
    block: SmartBlock,
    alloyMixingRatioComponent: AlloyMixingRatioComponent,
    settings: (Settings) -> Settings = { it.component(ModComponentTypes.ALLOY_MIXING_RATIO, alloyMixingRatioComponent) }
) : SmartBlockItem(block, block.id, settings) {
    
}