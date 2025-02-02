package com.rhseung.alloy.data

import com.rhseung.alloy.init.ModFluidTags
import com.rhseung.alloy.metal.Metal
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.FluidTags
import java.util.concurrent.CompletableFuture

class FluidTagProvider(
    output: FabricDataOutput,
    completableFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
): FabricTagProvider.FluidTagProvider(output, completableFuture) {

    override fun configure(lookUp: RegistryWrapper.WrapperLookup) {
        Metal.entries.forEach {
            getOrCreateTagBuilder(ModFluidTags.MOLTEN_METAL)
                .add(it.moltenFluid)
                .add(it.moltenFluidFlowing);
        }

        getOrCreateTagBuilder(FluidTags.LAVA)
            .addTag(ModFluidTags.MOLTEN_METAL);
    }
}