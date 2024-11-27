package com.rhseung.alloy.init

import com.rhseung.alloy.Mod
import com.rhseung.alloy.block.SmartBlock
import com.rhseung.alloy.item.SmartBucketItem
import com.rhseung.alloy.block.SmartFluidBlock
import com.rhseung.alloy.item.SmartItem
import com.rhseung.alloy.fluid.MoltenMetalFluid
import com.rhseung.alloy.util.Color
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemConvertible
import net.minecraft.item.Items
import net.minecraft.sound.BlockSoundGroup

enum class Metal(
    val color: Color,
    nuggetPreset: Item? = null,
    ingotPreset: Item? = null,
    storageBlockPreset: Block? = null
) {
    COPPER(Color.COPPER.brighter(50),
        ingotPreset = Items.COPPER_INGOT,
        storageBlockPreset = Blocks.COPPER_BLOCK
    ),
    IRON(Color.DARK_RED,
        nuggetPreset = Items.IRON_NUGGET,
        ingotPreset = Items.IRON_INGOT,
        storageBlockPreset = Blocks.IRON_BLOCK
    ),
    GOLD(Color.GOLD.brighter(50),
        nuggetPreset = Items.GOLD_NUGGET,
        ingotPreset = Items.GOLD_INGOT,
        storageBlockPreset = Blocks.GOLD_BLOCK
    ),
    NETHERITE(Color.NETHERITE.brighter(30),
        ingotPreset = Items.NETHERITE_INGOT,
        storageBlockPreset = Blocks.NETHERITE_BLOCK
    ),
    STEEL(Color.IRON.darker(100));

    private val lowerName = name.lowercase();
    val titleName = lowerName.replaceFirstChar { it.uppercase() };
    val id = Mod.id(lowerName);

    val nuggetItem = nuggetPreset ?: SmartItem("${lowerName}_nugget",
        itemGroup = ModItemGroups.METAL_TAB_KEY
    );

    val ingotItem = ingotPreset ?: SmartItem("${lowerName}_ingot",
        itemGroup = ModItemGroups.METAL_TAB_KEY
    );

    val storageBlock = storageBlockPreset ?: SmartBlock("${lowerName}_block",
        itemGroup = ModItemGroups.METAL_TAB_KEY
    ) { settings -> settings
        .requiresTool()
        .strength(5.0F, 6.0F)
        .sounds(BlockSoundGroup.METAL)
    };

    val moltenFluid = MoltenMetalFluid.Still("molten_$lowerName",
        metal = this
    );

    val moltenFluidFlowing = MoltenMetalFluid.Flowing("flowing_molten_$lowerName",
        metal = this
    );

    val moltenFluidBlock = SmartFluidBlock("molten_$lowerName",
        fluid = moltenFluid
    );

    val bucketItem = SmartBucketItem("${lowerName}_bucket",
        fluid = moltenFluid,
        itemGroup = ModItemGroups.METAL_TAB_KEY
    );

    // TODO: 진짜 양동이 자체 재료를 Metal로, 도구 추가, 갑옷 추가, 방패 추가.

    companion object {
        fun initialize() {}

        fun isPreset(item: ItemConvertible): Boolean {
            val presets = listOf<ItemConvertible>(
                Items.COPPER_INGOT, Blocks.COPPER_BLOCK,
                Items.IRON_NUGGET, Items.IRON_INGOT, Blocks.IRON_BLOCK,
                Items.GOLD_NUGGET, Items.GOLD_INGOT, Blocks.GOLD_BLOCK,
                Items.NETHERITE_INGOT, Blocks.NETHERITE_BLOCK
            );

            return item in presets;
        }
    }
}