package com.rhseung.alloy.data

import com.rhseung.alloy.metal.Metal
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class LootTableProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricBlockLootTableProvider(dataOutput, registryLookup) {

    override fun generate() {
        Metal.entries.forEach {
            if (!Metal.isPreset(it.storageBlock))
                addDrop(it.storageBlock)
        }
    }
}