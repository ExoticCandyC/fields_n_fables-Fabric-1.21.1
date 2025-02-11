package net.exoticcandy.fields_n_fables.data.provider;

import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.fields_n_fables.list.TagList;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class FFItemTagProvider extends FabricTagProvider<Item> {
    public FFItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup)
    {
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

        getOrCreateTagBuilder(TagList.Items.ECHO_LOGS)
                .add(BlockInit.ECHO_LOG.asItem())
                .add(BlockInit.ECHO_WOOD.asItem())
                .add(BlockInit.STRIPPED_ECHO_LOG.asItem())
                .add(BlockInit.STRIPPED_ECHO_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(TagList.Items.ECHO_LOGS);

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(BlockInit.ECHO_LEAVES.asItem());

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(BlockInit.ECHO_SAPLING.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(BlockInit.ECHO_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(BlockInit.ECHO_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(BlockInit.ECHO_FENCE.asItem());

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(BlockInit.ECHO_FENCE_GATE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(BlockInit.ECHO_STAIRS.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(BlockInit.ECHO_SLAB.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(BlockInit.ECHO_PRESSURE_PLATE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(BlockInit.ECHO_TRAPDOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(BlockInit.ECHO_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(BlockInit.ECHO_SIGN_ITEM);

        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(BlockInit.ECHO_HANGING_SIGN_ITEM);

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(BlockInit.ECHO_BOAT);

        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(BlockInit.ECHO_CHEST_BOAT);
    }
}