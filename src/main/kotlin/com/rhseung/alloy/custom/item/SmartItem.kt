package com.rhseung.alloy.custom.item

import com.rhseung.alloy.Mod
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Identifier

open class SmartItem(
    val name: String,
    val itemGroup: RegistryKey<ItemGroup>? = null,
    val displayName: String? = null,
    val id: Identifier = Mod.id(name),
    val registryKey: RegistryKey<Item> = RegistryKey.of(RegistryKeys.ITEM, id),
    settings: (Settings) -> Settings = { it },
) : Item(settings(Settings().registryKey(registryKey))) {

    init {
        RegistryHelper.register(name, this, itemGroup);
    }

    override fun getName(stack: ItemStack): Text {
        if (displayName != null)
            return Text.literal(displayName);
        else
            return super.getName(stack);
    }
}