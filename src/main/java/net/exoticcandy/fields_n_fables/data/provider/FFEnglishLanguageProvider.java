package net.exoticcandy.fields_n_fables.data.provider;

/*import dev.turtywurty.tutorialmod.block.entity.ExampleEnergyGeneratorBlockEntity;
import dev.turtywurty.tutorialmod.block.entity.ExampleFluidTankBlockEntity;
import dev.turtywurty.tutorialmod.block.entity.ExampleInventoryBlockEntity;
import dev.turtywurty.tutorialmod.init.EnchantmentInit;
import dev.turtywurty.tutorialmod.init.ItemGroupInit;*/

import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.fields_n_fables.list.TagList;
import net.exoticcandy.world_gen_lib.FlowerPetals.EC_Petal;
import net.exoticcandy.world_gen_lib.FlowerPetals.list.Petals;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.exoticcandy.world_gen_lib.Vine.EC_Vine;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
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

    private static void addText(@NotNull TranslationBuilder builder, @NotNull Text text, @NotNull String value)
    {
        if (text.getContent() instanceof TranslatableTextContent translatableTextContent) {
            builder.add(translatableTextContent.getKey(), value);
        } else {
            FieldsFables.LOGGER.warn("Failed to add translation for text: {}", text.getString());
        }
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder)
    {
        for(EC_Tree  tree  :  Trees.trees )  tree.datagen_language(translationBuilder);
        for(EC_Vine  vines :  Vines.vines ) vines.datagen_language(translationBuilder);
        for(EC_Petal petal : Petals.petals) petal.datagen_language(translationBuilder);
    }
}