package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.list.TagList;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
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
        }

/*        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(BlockInit.ECHO_BLOCK)
                .add(BlockInit.ECHO_OVERWORLD_ORE)
                .add(BlockInit.ECHO_DEEPSLATE_ORE)
                .add(BlockInit.ECHO_NETHER_ORE)
                .add(BlockInit.ECHO_END_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(BlockInit.ECHO_BE_BLOCK)
                .add(BlockInit.ECHO_INVENTORY_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(BlockInit.ECHO_TICKING_BE_BLOCK)
                .add(BlockInit.ECHO_ENERGY_STORAGE_BLOCK)
                .add(BlockInit.ECHO_ENERGY_GENERATOR_BLOCK);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(BlockInit.ECHO_BLOCK)
                .add(BlockInit.ECHO_OVERWORLD_ORE)
                .add(BlockInit.ECHO_DEEPSLATE_ORE)
                .add(BlockInit.ECHO_NETHER_ORE)
                .add(BlockInit.ECHO_END_ORE)
                .add(BlockInit.ECHO_BE_BLOCK)
                .add(BlockInit.ECHO_TICKING_BE_BLOCK)
                .add(BlockInit.ECHO_ENERGY_STORAGE_BLOCK)
                .add(BlockInit.ECHO_ENERGY_GENERATOR_BLOCK)
                .add(BlockInit.ECHO_INVENTORY_BLOCK);

        getOrCreateTagBuilder(TagList.Blocks.ECHO_TAG)
                .add(BlockInit.ECHO_BLOCK)
                .add(Blocks.BLUE_ORCHID);

        getOrCreateTagBuilder(TagList.Blocks.INCORRECT_FOR_ECHO_TOOL);

        getOrCreateTagBuilder(BlockTags.SMALL_FLOWERS)
                .add(BlockInit.ECHO_FLOWER);*/

        getOrCreateTagBuilder(BlockTags.FLOWER_POTS)
                .add(BlockInit.POTTED_ECHO_SAPLING);

        getOrCreateTagBuilder(TagList.Blocks.ECHO_LOGS)
                .add(BlockInit.ECHO_LOG)
                .add(BlockInit.STRIPPED_ECHO_LOG)
                .add(BlockInit.ECHO_WOOD)
                .add(BlockInit.STRIPPED_ECHO_WOOD);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(BlockInit.ECHO_LOG)
                .add(BlockInit.STRIPPED_ECHO_LOG)
                .add(BlockInit.ECHO_WOOD)
                .add(BlockInit.STRIPPED_ECHO_WOOD);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(BlockInit.ECHO_LOG)
                .add(BlockInit.STRIPPED_ECHO_LOG)
                .add(BlockInit.ECHO_WOOD)
                .add(BlockInit.STRIPPED_ECHO_WOOD);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(BlockInit.ECHO_LEAVES);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(BlockInit.ECHO_SAPLING);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(BlockInit.ECHO_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(BlockInit.ECHO_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(BlockInit.ECHO_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(BlockInit.ECHO_FENCE);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(BlockInit.ECHO_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(BlockInit.ECHO_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(BlockInit.ECHO_STAIRS);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(BlockInit.ECHO_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(BlockInit.ECHO_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(BlockInit.ECHO_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(BlockInit.ECHO_WALL_HANGING_SIGN);
    }
}