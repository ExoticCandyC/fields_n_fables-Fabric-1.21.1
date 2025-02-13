package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.FlowerPetals.EC_Petal;
import net.exoticcandy.world_gen_lib.FlowerPetals.list.Petals;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.exoticcandy.world_gen_lib.Vine.EC_Vine;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.family.BlockFamily;

public class FFModelProvider extends FabricModelProvider {
    public FFModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator)
    {
        for(EC_Tree  tree  :  Trees.trees )  tree.datagen_block_model(blockStateModelGenerator);
        for(EC_Vine  vines :  Vines.vines ) vines.datagen_block_model(blockStateModelGenerator);
        for(EC_Petal petal : Petals.petals) petal.datagen_block_model(blockStateModelGenerator);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator)
    {
        for(EC_Tree tree : Trees.trees) tree.datagen_item_model(itemModelGenerator);
    }
}