package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.list.TagList;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.FlowerPetals.EC_Petal;
import net.exoticcandy.world_gen_lib.FlowerPetals.list.Petals;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.exoticcandy.world_gen_lib.Vine.EC_Vine;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

public class FFBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public FFBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    public FabricTagProvider<Block>.FabricTagBuilder publicGetOrCreateTagBuilder(TagKey<Block> tag)
    {
        return getOrCreateTagBuilder(tag);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

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
            if(tree.flowerLeaves)
            {
                getOrCreateTagBuilder(BlockTags.FLOWERS).add(tree.LEAVES);
            }
        }
        for(EC_Vine vine : Vines.vines)
        {
            if(vine.side_vine)
            {
                getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(vine.VINE_STEM);
                getOrCreateTagBuilder(BlockTags.REPLACEABLE).add(vine.VINE_STEM);
                getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(vine.VINE_STEM);
            }
            else
            {
                getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(vine.VINE_PLANT).add(vine.VINE_STEM);
                getOrCreateTagBuilder(BlockTags.REPLACEABLE).add(vine.VINE_PLANT).add(vine.VINE_STEM);
                getOrCreateTagBuilder(BlockTags.REPLACEABLE_BY_TREES).add(vine.VINE_PLANT).add(vine.VINE_STEM);
            }
        }
        for(EC_Petal petal : Petals.petals)
        {
            getOrCreateTagBuilder(BlockTags.FLOWERS).add(petal.PETAL);
        }
    }
}