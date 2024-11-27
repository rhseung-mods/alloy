package com.rhseung.alloy.init

import com.rhseung.alloy.Mod
import com.rhseung.alloy.util.RegistryHelper
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.block.Blocks
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.text.Text
import net.minecraft.util.Identifier

object ModItemGroups : IModInit {
    fun of(id: Identifier): RegistryKey<ItemGroup> {
        return RegistryKey.of(RegistryKeys.ITEM_GROUP, id);
    }

    val METAL_TAB_KEY = of(Mod.id("metal_tab"));

    private val METAL_TAB = RegistryHelper.register(METAL_TAB_KEY, FabricItemGroup.builder()
        .icon { ItemStack(Items.NETHERITE_INGOT) }
        .displayName(Text.translatable("itemgroup.${Mod.MOD_ID}.${METAL_TAB_KEY.value.path}"))
        .entries { _, entries ->
            entries.add(Items.COPPER_INGOT)
            entries.add(Blocks.COPPER_BLOCK)
            entries.add(Items.IRON_NUGGET)
            entries.add(Items.IRON_INGOT)
            entries.add(Items.IRON_BLOCK)
            entries.add(Items.GOLD_NUGGET)
            entries.add(Items.GOLD_INGOT)
            entries.add(Items.GOLD_BLOCK)
            entries.add(Items.NETHERITE_INGOT)
            entries.add(Items.NETHERITE_BLOCK)
        }
        .build()
    );
}