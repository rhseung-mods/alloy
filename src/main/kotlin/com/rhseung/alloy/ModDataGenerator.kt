package com.rhseung.alloy

import com.rhseung.alloy.data.*
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object ModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack();

		pack.addProvider(::LanguageProvider);
		pack.addProvider(::ModelProvider);
		pack.addProvider(::FluidTagProvider);
		pack.addProvider(::BlockTagProvider)
		pack.addProvider(::RecipeProvider);
	}
}