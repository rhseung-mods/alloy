package com.rhseung.alloy.init

import com.rhseung.alloy.metal.AlloyMixingRatioComponent
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.component.ComponentType

object ModComponentTypes : IModInit {
    val ALLOY_MIXING_RATIO: ComponentType<AlloyMixingRatioComponent> =
        RegistryHelper.registerComponent("alloy_mixing_ratio") { it.codec(AlloyMixingRatioComponent.CODEC) };
}