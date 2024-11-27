package com.rhseung.alloy

import com.rhseung.alloy.init.*
import net.fabricmc.api.ModInitializer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object Mod : ModInitializer {
	const val MOD_ID = "alloy";
    val LOGGER = LoggerFactory.getLogger(MOD_ID);

	fun id(path: String): Identifier {
		return Identifier.of(MOD_ID, path);
	}

	override fun onInitialize() {
		Metal.initialize();
		ModFluidTags.initialize();
		ModItemGroups.initialize();
	}
}