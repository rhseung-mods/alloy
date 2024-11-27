package com.rhseung.alloy.item

import com.rhseung.alloy.Mod
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

class SmartItem(
    val name: String,
    val itemGroup: RegistryKey<ItemGroup>? = null,
    val id: Identifier = Mod.id(name),
    val registryKey: RegistryKey<Item> = RegistryKey.of(RegistryKeys.ITEM, id),
    settings: (Settings) -> Settings = { it },
) : Item(settings(Settings().registryKey(registryKey))) {

    init {
        RegistryHelper.register(name, this, itemGroup);
    }
}