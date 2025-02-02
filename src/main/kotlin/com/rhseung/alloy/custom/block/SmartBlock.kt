package com.rhseung.alloy.custom.block

import com.rhseung.alloy.Mod
import com.rhseung.alloy.custom.item.SmartBlockItem
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.block.Block
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

open class SmartBlock(
    val name: String,
    val itemGroup: RegistryKey<ItemGroup>? = null,
    val id: Identifier = Mod.id(name),
    val registryKey: RegistryKey<Block> = RegistryKey.of(RegistryKeys.BLOCK, id),
    settings: (Settings) -> Settings = { it },
) : Block(settings(Settings.create().registryKey(registryKey))) {

    val blockItem: SmartBlockItem = SmartBlockItem(this);

    init {
        RegistryHelper.register(name, this);
    }
}