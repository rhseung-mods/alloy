package com.rhseung.alloy.data

import com.rhseung.alloy.init.Metal
import com.rhseung.alloy.init.ModFluidTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import java.util.concurrent.CompletableFuture

class BlockTagProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
): FabricTagProvider.BlockTagProvider(output, completableFuture) {

    override fun configure(lookUp: RegistryWrapper.WrapperLookup) {
        Metal.entries.forEach {
            if (!Metal.isPreset(it.storageBlock)) {
                getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(it.storageBlock);
                getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL).add(it.storageBlock);
            }
        }
    }
}