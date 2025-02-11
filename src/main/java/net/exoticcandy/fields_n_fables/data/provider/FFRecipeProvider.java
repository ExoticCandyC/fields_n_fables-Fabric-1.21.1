package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.fields_n_fables.list.TagList;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class FFRecipeProvider extends FabricRecipeProvider {
    public FFRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_recipes(exporter);
        }

        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_PLANKS, 4)
                .input(Ingredient.fromTag(TagList.Items.ECHO_LOGS))
                .criterion(hasTag(TagList.Items.ECHO_LOGS), conditionsFromTag(TagList.Items.ECHO_LOGS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_WOOD, 3)
                .input('L', BlockInit.ECHO_LOG)
                .pattern("LL")
                .pattern("LL")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_LOG), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.STRIPPED_ECHO_WOOD, 3)
                .input('L', BlockInit.STRIPPED_ECHO_LOG)
                .pattern("LL")
                .pattern("LL")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.STRIPPED_ECHO_LOG), FabricRecipeProvider.conditionsFromItem(BlockInit.STRIPPED_ECHO_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_SLAB, 6)
                .input('T', BlockInit.ECHO_PLANKS)
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_STAIRS, 4)
                .input('T', BlockInit.ECHO_PLANKS)
                .pattern("T  ")
                .pattern("TT ")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_FENCE, 3)
                .input('T', BlockInit.ECHO_PLANKS)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("TST")
                .pattern("TST")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_FENCE_GATE)
                .input('T', BlockInit.ECHO_PLANKS)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("STS")
                .pattern("STS")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_DOOR, 3)
                .input('T', BlockInit.ECHO_PLANKS)
                .pattern("TT")
                .pattern("TT")
                .pattern("TT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, BlockInit.ECHO_TRAPDOOR, 2)
                .input('T', BlockInit.ECHO_PLANKS)
                .pattern("TTT")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, BlockInit.ECHO_BUTTON)
                .input(BlockInit.ECHO_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, BlockInit.ECHO_PRESSURE_PLATE)
                .input('T', BlockInit.ECHO_PLANKS)
                .pattern("TT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, BlockInit.ECHO_SIGN_ITEM, 3)
                .input('T', BlockInit.ECHO_PLANKS)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("TTT")
                .pattern("TTT")
                .pattern(" S ")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, BlockInit.ECHO_HANGING_SIGN_ITEM, 6)
                .input('T', BlockInit.ECHO_PLANKS)
                .input('C', ConventionalItemTags.CHAINS)
                .pattern("C C")
                .pattern("TTT")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .criterion(hasTag(ConventionalItemTags.CHAINS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.CHAINS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, BlockInit.ECHO_BOAT)
                .input('T', BlockInit.ECHO_PLANKS)
                .pattern("T T")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_PLANKS), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_PLANKS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, BlockInit.ECHO_CHEST_BOAT)
                .input(BlockInit.ECHO_BOAT)
                .input(ConventionalItemTags.WOODEN_CHESTS)
                .criterion(FabricRecipeProvider.hasItem(BlockInit.ECHO_BOAT), FabricRecipeProvider.conditionsFromItem(BlockInit.ECHO_BOAT))
                .criterion(hasTag(ConventionalItemTags.WOODEN_CHESTS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_CHESTS))
                .offerTo(exporter);

        var echoFamily = new BlockFamily.Builder(BlockInit.ECHO_PLANKS)
                .button(BlockInit.ECHO_BUTTON)
                .fence(BlockInit.ECHO_FENCE)
                .fenceGate(BlockInit.ECHO_FENCE_GATE)
                .pressurePlate(BlockInit.ECHO_PRESSURE_PLATE)
                .sign(BlockInit.ECHO_SIGN, BlockInit.ECHO_WALL_SIGN)
                .slab(BlockInit.ECHO_SLAB)
                .stairs(BlockInit.ECHO_STAIRS)
                .door(BlockInit.ECHO_DOOR)
                .trapdoor(BlockInit.ECHO_TRAPDOOR)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();

        generateFamily(exporter, echoFamily, FeatureSet.empty());
    }

    private static @NotNull String hasTag(@NotNull TagKey<Item> tag) {
        return "has_" + tag.id().toString();
    }
}