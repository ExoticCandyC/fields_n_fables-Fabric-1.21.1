package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FFBlockLootTableProvider extends FabricBlockLootTableProvider {
    public FFBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_loot_table(this);
        }

        addPottedPlantDrops(BlockInit.POTTED_ECHO_SAPLING);
        addDrop(BlockInit.ECHO_LEAVES, leavesDrops(BlockInit.ECHO_LEAVES, BlockInit.ECHO_SAPLING, SAPLING_DROP_CHANCE)); /* Issues */
        addDrop(BlockInit.ECHO_DOOR, doorDrops(BlockInit.ECHO_DOOR)); /* Issues */
        addDrop(BlockInit.ECHO_LOG);
        addDrop(BlockInit.STRIPPED_ECHO_LOG);
        addDrop(BlockInit.ECHO_WOOD);
        addDrop(BlockInit.STRIPPED_ECHO_WOOD);
        addDrop(BlockInit.ECHO_SAPLING);
        addDrop(BlockInit.ECHO_PLANKS);
        addDrop(BlockInit.ECHO_SLAB);
        addDrop(BlockInit.ECHO_STAIRS);
        addDrop(BlockInit.ECHO_FENCE);
        addDrop(BlockInit.ECHO_FENCE_GATE);
        addDrop(BlockInit.ECHO_TRAPDOOR);
        addDrop(BlockInit.ECHO_BUTTON);
        addDrop(BlockInit.ECHO_PRESSURE_PLATE);
        addDrop(BlockInit.ECHO_SIGN, BlockInit.ECHO_SIGN_ITEM);
        addDrop(BlockInit.ECHO_WALL_SIGN, BlockInit.ECHO_SIGN_ITEM);
        addDrop(BlockInit.ECHO_HANGING_SIGN, BlockInit.ECHO_HANGING_SIGN_ITEM); /* Break Particle Issues */
        addDrop(BlockInit.ECHO_WALL_HANGING_SIGN, BlockInit.ECHO_HANGING_SIGN_ITEM); /* Break Particle Issues */

    }
}