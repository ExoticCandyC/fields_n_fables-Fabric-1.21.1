package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.FlowerPetals.EC_Petal;
import net.exoticcandy.world_gen_lib.FlowerPetals.list.Petals;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.exoticcandy.world_gen_lib.Vine.EC_Vine;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class FFBlockLootTableProvider extends FabricBlockLootTableProvider {
    public FFBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate()
    {
        for(EC_Tree  tree  :  Trees.trees )  tree.datagen_loot_table(this);
        for(EC_Vine  vines :  Vines.vines ) vines.datagen_loot_table(this);
        for(EC_Petal petal : Petals.petals) petal.datagen_loot_table(this);
    }
}