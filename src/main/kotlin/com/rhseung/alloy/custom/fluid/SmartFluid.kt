package com.rhseung.alloy.custom.fluid

import com.rhseung.alloy.Mod
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.fluid.FlowableFluid
import net.minecraft.util.Identifier

abstract class SmartFluid(
    val name: String,
    val id: Identifier = Mod.id(name)
) : FlowableFluid() {

    init {
        RegistryHelper.register(name, this);
    }
}