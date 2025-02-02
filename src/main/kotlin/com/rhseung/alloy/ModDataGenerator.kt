package com.rhseung.alloy

import com.rhseung.alloy.data.*
import com.rhseung.alloy.init.ModDamageTypes
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder
import net.minecraft.registry.RegistryKeys

object ModDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack();

		pack.addProvider(::LanguageProvider);
		pack.addProvider(::ModelProvider);
		pack.addProvider(::FluidTagProvider);
		pack.addProvider(::BlockTagProvider)
		pack.addProvider(::RecipeProvider);
		pack.addProvider(::RegistryProvider);
	}

	override fun buildRegistry(registryBuilder: RegistryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.DAMAGE_TYPE, ModDamageTypes::bootstrap);
	}
}