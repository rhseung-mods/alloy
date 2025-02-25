package com.rhseung.alloy.custom.item

import com.rhseung.alloy.custom.block.SmartBlock
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.item.BlockItem
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

open class SmartBlockItem(
    val block: SmartBlock,
    val id: Identifier = block.id,
    settings: (Settings) -> Settings = { it },
) : BlockItem(block, settings(Settings().useBlockPrefixedTranslationKey().registryKey(RegistryKey.of(RegistryKeys.ITEM, id)))) {

    init {
        RegistryHelper.register(block.name, this, block.itemGroup);
    }
}