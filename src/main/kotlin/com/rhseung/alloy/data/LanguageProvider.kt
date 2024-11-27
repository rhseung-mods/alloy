package com.rhseung.alloy.data

import com.rhseung.alloy.init.Metal
import com.rhseung.alloy.init.ModFluidTags
import com.rhseung.alloy.init.ModItemGroups
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class LanguageProvider(
    dataOutput: FabricDataOutput,
    registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricLanguageProvider(dataOutput, registryLookup) {

    override fun generateTranslations(lookUp: RegistryWrapper.WrapperLookup, translationBuilder: TranslationBuilder) {
        Metal.entries.forEach {
            if (!Metal.isPreset(it.nuggetItem))
                translationBuilder.add(it.nuggetItem, "${it.titleName} Nugget");
            if (!Metal.isPreset(it.ingotItem))
                translationBuilder.add(it.ingotItem, "${it.titleName} Ingot");
            if (!Metal.isPreset(it.storageBlock))
                translationBuilder.add(it.storageBlock, "${it.titleName} Block");
            translationBuilder.add(it.moltenFluidBlock, "Molten ${it.titleName}");
            translationBuilder.add(it.bucketItem, "Molten ${it.titleName} Bucket");
        }

        translationBuilder.add(ModFluidTags.MOLTEN_METAL, "Molten Metal Tag");
        translationBuilder.add(ModItemGroups.METAL_TAB_KEY, "Metal");
    }
}