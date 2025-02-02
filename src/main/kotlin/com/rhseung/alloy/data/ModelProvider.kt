package com.rhseung.alloy.data

import com.rhseung.alloy.Mod
import com.rhseung.alloy.metal.Metal
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.minecraft.data.client.*
import net.minecraft.util.Identifier
import java.util.*

class ModelProvider(output: FabricDataOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockModel: BlockStateModelGenerator) {
        Metal.entries.forEach {
            blockModel.registerSimpleState(it.moltenFluidBlock);

            if (!Metal.isPreset(it.storageBlock))
                blockModel.registerSingleton(it.storageBlock, TextureMap.all(Mod.id("block/storage_block")), Models.CUBE_ALL);
        }
    }

    /**
     * Ref: [net.minecraft.data.client.Models.item]
     */
    private fun item(parent: String, vararg requiredTextureKeys: TextureKey): Model {
        return Model(Optional.of(Identifier.ofVanilla("item/$parent")), Optional.empty(), *requiredTextureKeys)
    }

    override fun generateItemModels(itemModel: ItemModelGenerator) {
        Metal.entries.forEach {
            if (!Metal.isPreset(it.nuggetItem))
                Models.GENERATED.upload(ModelIds.getItemModelId(it.nuggetItem), TextureMap.layer0(Mod.id("item/nugget")), itemModel.writer);

            if (!Metal.isPreset(it.ingotItem))
                Models.GENERATED.upload(ModelIds.getItemModelId(it.ingotItem), TextureMap.layer0(Mod.id("item/ingot")), itemModel.writer);

            item("generated", TextureKey.LAYER0, TextureKey.LAYER1).upload(
                ModelIds.getItemModelId(it.bucketItem),
                TextureMap.layered(
                    Mod.id("item/bucket"),
                    Mod.id("item/bucket_contents")
                ),
                itemModel.writer
            );
        }
    }
}