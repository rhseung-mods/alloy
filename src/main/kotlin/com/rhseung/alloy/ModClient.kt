package com.rhseung.alloy

import com.rhseung.alloy.init.Metal
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry
import net.minecraft.client.color.block.BlockColorProvider
import net.minecraft.client.color.item.ItemColorProvider
import net.minecraft.client.render.RenderLayer

object ModClient : ClientModInitializer {
    override fun onInitializeClient() {
        Metal.entries.forEach {
            BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), it.moltenFluid, it.moltenFluidFlowing);

            FluidRenderHandlerRegistry.INSTANCE.register(it.moltenFluid, it.moltenFluidFlowing, SimpleFluidRenderHandler(
                Mod.id("block/molten_still"),
                Mod.id("block/molten_flowing"),
                it.color.toInt() - (2 shl 23)
            ));

            ColorProviderRegistry.ITEM.register(ItemColorProvider { _, tintIndex ->
                if (tintIndex == 1) it.color.toInt() - (2 shl 23) else -1
            }, it.bucketItem);

            ColorProviderRegistry.ITEM.register(ItemColorProvider { _, _ ->
                it.color.toInt() - (2 shl 23)
            }, *listOf(it.nuggetItem, it.ingotItem).filterNot(Metal::isPreset).toTypedArray());

            ColorProviderRegistry.BLOCK.register(BlockColorProvider { _, _, _, _ ->
                it.color.toInt() - (2 shl 23)
            }, *listOf(it.storageBlock).filterNot(Metal::isPreset).toTypedArray());
        }
    }
}