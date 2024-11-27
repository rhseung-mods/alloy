package com.rhseung.alloy.util

import com.rhseung.alloy.Mod
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.block.Block
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey

object RegistryHelper {
    fun <R: Item> register(path: String, entry: R, itemGroup: RegistryKey<ItemGroup>? = null): R {
        val ret = Registry.register(Registries.ITEM, Mod.id(path), entry);
        itemGroup?.let { ItemGroupEvents.modifyEntriesEvent(it).register { entries -> entries.add(entry) } }
        return ret;
    }

    fun <R: Block> register(path: String, entry: R): R {
        return Registry.register(Registries.BLOCK, Mod.id(path), entry);
    }

    fun <R : Fluid> register(path: String, entry: R): R {
        return Registry.register(Registries.FLUID, Mod.id(path), entry);
    }

    fun <R : ItemGroup> register(key: RegistryKey<ItemGroup>, entry: R): R {
        return Registry.register(Registries.ITEM_GROUP, key, entry);
    }
}