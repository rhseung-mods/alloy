package com.rhseung.alloy.block

import com.rhseung.alloy.Mod
import com.rhseung.alloy.util.RegistryHelper
import net.minecraft.block.Block
import net.minecraft.block.FluidBlock
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.fluid.FlowableFluid
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

class SmartFluidBlock(
    val name: String,
    val fluid: FlowableFluid,
    val id: Identifier = Mod.id(name),
    val registryKey: RegistryKey<Block> = RegistryKey.of(RegistryKeys.BLOCK, id),
    settings: (Settings) -> Settings = { it },
) : FluidBlock(fluid, settings(Settings.create()
    .registryKey(registryKey).noCollision().ticksRandomly().strength(100.0F).luminance { 15 }
    .pistonBehavior(PistonBehavior.DESTROY).dropsNothing().liquid().sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)
)) {

    init {
        RegistryHelper.register(name, this);
    }
}