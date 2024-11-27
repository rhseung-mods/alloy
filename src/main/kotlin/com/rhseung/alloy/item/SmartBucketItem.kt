package com.rhseung.alloy.item

import com.rhseung.alloy.Mod
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.fluid.Fluid
import net.minecraft.item.BucketItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

class SmartBucketItem(
    val name: String,
    val fluid: Fluid,
    val itemGroup: RegistryKey<ItemGroup>? = null,
    val id: Identifier = Mod.id(name),
    val registryKey: RegistryKey<Item> = RegistryKey.of(RegistryKeys.ITEM, id),
    settings: (Settings) -> Settings = { it },
) : BucketItem(fluid, settings(Settings().registryKey(registryKey).maxCount(1).recipeRemainder(Items.BUCKET))) {

    init {
        RegistryHelper.register(name, this, itemGroup);
    }
}