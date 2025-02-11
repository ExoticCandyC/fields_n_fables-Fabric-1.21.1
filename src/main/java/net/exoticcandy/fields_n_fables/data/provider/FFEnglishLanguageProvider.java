package net.exoticcandy.fields_n_fables.data.provider;

/*import dev.turtywurty.tutorialmod.block.entity.ExampleEnergyGeneratorBlockEntity;
import dev.turtywurty.tutorialmod.block.entity.ExampleFluidTankBlockEntity;
import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.init.EnchantmentInit;
import dev.turtywurty.tutorialmod.init.ItemGroupInit;*/

import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.fields_n_fables.list.TagList;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class FFEnglishLanguageProvider extends FabricLanguageProvider {
    public FFEnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    private static void addText(@NotNull TranslationBuilder builder, @NotNull Text text, @NotNull String value) {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        } else {
            FieldsFables.LOGGER.warn("Failed to add translation for text: {}", text.getString());
        }
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        for(EC_Tree tree : Trees.trees)
        {
            tree.datagen_language(translationBuilder);
        }

        translationBuilder.add(BlockInit.ECHO_LOG, "Echo Log");
        translationBuilder.add(BlockInit.STRIPPED_ECHO_LOG, "Stripped Echo Log");
        translationBuilder.add(BlockInit.ECHO_WOOD, "Echo Wood");
        translationBuilder.add(BlockInit.STRIPPED_ECHO_WOOD, "Stripped Echo Wood");
        translationBuilder.add(BlockInit.ECHO_LEAVES, "Echo Leaves");
        translationBuilder.add(BlockInit.ECHO_SAPLING, "Echo Sapling");
        translationBuilder.add(BlockInit.ECHO_PLANKS, "Echo Planks");
        translationBuilder.add(BlockInit.ECHO_SLAB, "Echo Slab");
        translationBuilder.add(BlockInit.ECHO_STAIRS, "Echo Stairs");
        translationBuilder.add(BlockInit.ECHO_FENCE, "Echo Fence");
        translationBuilder.add(BlockInit.ECHO_FENCE_GATE, "Echo Fence Gate");
        translationBuilder.add(BlockInit.ECHO_DOOR, "Echo Door");
        translationBuilder.add(BlockInit.ECHO_TRAPDOOR, "Echo Trapdoor");
        translationBuilder.add(BlockInit.ECHO_BUTTON, "Echo Button");
        translationBuilder.add(BlockInit.ECHO_PRESSURE_PLATE, "Echo Pressure Plate");
        translationBuilder.add(BlockInit.ECHO_SIGN_ITEM, "Echo Sign");
        translationBuilder.add(BlockInit.ECHO_HANGING_SIGN_ITEM, "Echo Hanging Sign");
        translationBuilder.add(BlockInit.ECHO_BOAT, "Echo Boat");
        translationBuilder.add(BlockInit.ECHO_CHEST_BOAT, "Echo Chest Boat");
        translationBuilder.add(TagList.Items.ECHO_LOGS, "Echo Logs");
    }
}