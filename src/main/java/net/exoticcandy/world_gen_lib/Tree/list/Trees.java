package net.exoticcandy.world_gen_lib.Tree.list;

import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.minecraft.block.MapColor;
import net.minecraft.block.SaplingGenerator;

import java.util.ArrayList;
import java.util.List;

public class Trees
{
    public static List<EC_Tree> trees = new ArrayList<>();

    public static void register_new_tree(String MOD_ID, String tree_ID_Suffix, String tree_Name, SaplingGenerator Sapling_Generator, MapColor LogColor, MapColor PlankColor, MapColor LeavesColor)
    {
        trees.add(new EC_Tree(MOD_ID, tree_ID_Suffix, tree_Name, Sapling_Generator, LogColor, PlankColor, LeavesColor));
    }

    public static void register_new_tree(String MOD_ID, String tree_ID_Suffix, String tree_Name, SaplingGenerator Sapling_Generator, MapColor LogColor, MapColor PlankColor, MapColor LeavesColor, boolean flammableLeaves, boolean flammableWood)
    {
        trees.add(new EC_Tree(MOD_ID, tree_ID_Suffix, tree_Name, Sapling_Generator, LogColor, PlankColor, LeavesColor, flammableLeaves, flammableWood));
    }

    /*
        // Loot Table Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_loot_table(this);
        }

        // Language Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_language(translationBuilder);
        }

        // Block Tag Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(tree.POTTED_SAPLING);
            getOrCreateTagBuilder(tree.BLOCK_TAG_LIST_LOGS).add(tree.LOG).add(tree.STRIPPED_LOG).add(tree.WOOD).add(tree.STRIPPED_WOOD);
            getOrCreateTagBuilder(BlockTags.LOGS).add(tree.LOG).add(tree.STRIPPED_LOG).add(tree.WOOD).add(tree.STRIPPED_WOOD);
            getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN).add(tree.LOG).add(tree.STRIPPED_LOG).add(tree.WOOD).add(tree.STRIPPED_WOOD);
            getOrCreateTagBuilder(BlockTags.LEAVES).add(tree.LEAVES);
            getOrCreateTagBuilder(BlockTags.SAPLINGS).add(tree.SAPLING);
            getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(tree.BUTTON);
            getOrCreateTagBuilder(BlockTags.WOODEN_DOORS).add(tree.DOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS).add(tree.TRAPDOOR);
            getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(tree.FENCE);
            getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(tree.PRESSURE_PLATE);
            getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(tree.SLAB);
            getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(tree.STAIRS);
            getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(tree.SIGN);
            getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(tree.WALL_SIGN);
            getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(tree.HANGING_SIGN);
            getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(tree.WALL_HANGING_SIGN);
        }

        // Item Tag Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            getOrCreateTagBuilder(tree.ITEM_TAG_LIST_LOGS).add(tree.LOG.asItem()).add(tree.WOOD.asItem()).add(tree.STRIPPED_LOG.asItem()).add(tree.STRIPPED_WOOD.asItem());
            getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN).addTag(tree.ITEM_TAG_LIST_LOGS);
            getOrCreateTagBuilder(ItemTags.LEAVES).add(tree.LEAVES.asItem());
            getOrCreateTagBuilder(ItemTags.SAPLINGS).add(tree.SAPLING.asItem());
            getOrCreateTagBuilder(ItemTags.PLANKS).add(tree.PLANKS.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_DOORS).add(tree.DOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_FENCES).add(tree.FENCE.asItem());
            getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(tree.FENCE_GATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(tree.STAIRS.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(tree.SLAB.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES).add(tree.PRESSURE_PLATE.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(tree.TRAPDOOR.asItem());
            getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(tree.BUTTON.asItem());
            getOrCreateTagBuilder(ItemTags.SIGNS).add(tree.SIGN_ITEM);
            getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(tree.HANGING_SIGN_ITEM);
            getOrCreateTagBuilder(ItemTags.BOATS).add(tree.BOAT);
            getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(tree.CHEST_BOAT);
        }

        // Recipe Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_recipes(exporter);
        }

        // Block Model Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_block_model(blockStateModelGenerator);
        }

        // Item Model Provider Sample
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_item_model(itemModelGenerator);
        }
     */
}
