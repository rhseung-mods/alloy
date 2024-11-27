package com.rhseung.alloy.init

import com.rhseung.alloy.Mod
import net.minecraft.fluid.Fluid
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object ModFluidTags : IModInit {
    fun of(path: String): TagKey<Fluid> {
        return TagKey.of(RegistryKeys.FLUID, Mod.id(path));
    };

    val MOLTEN_METAL = of("molten_metal");
}