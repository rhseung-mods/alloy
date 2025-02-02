package com.rhseung.alloy.metal

import com.rhseung.alloy.Mod
import com.rhseung.alloy.custom.block.SmartBlock
import com.rhseung.alloy.custom.item.SmartItem
import net.minecraft.sound.BlockSoundGroup

class Alloy(val mixingRatio: Map<Metal, Int>) {
    val name = mixingRatio.entries.sortedBy { it.key.lowerName }.joinToString("_") { it.key.lowerName + it.value } + "_alloy";
    val titleName = mixingRatio.entries.sortedBy { it.key.titleName }.joinToString(" ") { it.key.titleName + " " + it.value + " %" } + " Alloy";
    val id = Mod.id(name);

    val nuggetItem = SmartItem("${name}_nugget");
    val ingotItem = SmartItem("${name}_ingot");
    val storageBlock = SmartBlock("${name}_block") { settings -> settings
        .requiresTool()
        .strength(5.0F, 6.0F)
        .sounds(BlockSoundGroup.METAL)
    };

    val moltenFluidBlock = SmartBlock("molten_${name}_block");
}