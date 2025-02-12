package net.exoticcandy.fields_n_fables;

import net.exoticcandy.fields_n_fables.data.provider.*;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class FieldsFablesDataGenerator implements DataGeneratorEntrypoint {
	public FabricDataGenerator.Pack pack;
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator)
	{
		for(EC_Tree tree : Trees.trees)
		{
			tree.loadDatagen();
		}

		pack = fabricDataGenerator.createPack();

		pack.addProvider(FFBlockLootTableProvider::new);
		pack.addProvider(FFBlockTagProvider::new);
		pack.addProvider(FFEnglishLanguageProvider::new);
		pack.addProvider(FFItemTagProvider::new);
		pack.addProvider(FFRecipeProvider::new);
		pack.addProvider(FFModelProvider::new);
		/*
		pack.addProvider(TutorialModWorldGenerator::new);
		pack.addProvider(TutorialModEnchantmentGenerator::new);*/

		BlockInit.load_data_generator();
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		//registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, BlockInit::bootstrap);
	}
}
