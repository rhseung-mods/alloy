package com.rhseung.alloy.data

import com.rhseung.alloy.init.Metal
import com.rhseung.alloy.Mod
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.RecipeGenerator
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class RecipeProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
): FabricRecipeProvider(output, registriesFuture) {

    override fun getName(): String {
        return "${Mod.MOD_ID} Recipes";
    }

    override fun getRecipeGenerator(lookUp: RegistryWrapper.WrapperLookup, exporter: RecipeExporter): RecipeGenerator {
        return object : RecipeGenerator(lookUp, exporter) {
            override fun generate() {
                Metal.entries.forEach {
                    if (!(Metal.isPreset(it.nuggetItem) && Metal.isPreset(it.ingotItem)))
                        offerReversibleCompactingRecipes(RecipeCategory.MISC, it.nuggetItem, RecipeCategory.MISC, it.ingotItem, getRecipeName(it.ingotItem) + "_to_nugget", null, getRecipeName(it.nuggetItem) + "_to_ingot", null);
                    if (!(Metal.isPreset(it.ingotItem) && Metal.isPreset(it.storageBlock)))
                        offerReversibleCompactingRecipes(RecipeCategory.MISC, it.ingotItem, RecipeCategory.BUILDING_BLOCKS, it.storageBlock, getRecipeName(it.storageBlock) + "_to_ingot", null, getRecipeName(it.ingotItem) + "_to_block", null);
                }
            }
        }
    }
}