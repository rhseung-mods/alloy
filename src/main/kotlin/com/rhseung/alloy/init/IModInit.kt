package com.rhseung.alloy.init

import com.rhseung.alloy.Mod
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier

interface IModInit {
    fun initialize() {}
}