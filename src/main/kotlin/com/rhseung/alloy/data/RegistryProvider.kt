package com.rhseung.alloy.data

import com.rhseung.alloy.Mod.MOD_ID
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class RegistryProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricDynamicRegistryProvider(output, registriesFuture) {

    override fun getName(): String {
        return "$MOD_ID's Registry Provider";
    }

    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        entries.addAll(registries.getOrThrow(RegistryKeys.DAMAGE_TYPE));
    }
}