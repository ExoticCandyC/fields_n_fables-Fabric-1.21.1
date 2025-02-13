package net.exoticcandy.world_gen_lib.FlowerPetals;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.data.client.*;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

import java.util.stream.IntStream;

public class EC_Petal {
    String MOD_ID;
    String petal_ID;
    String PetalName;
    boolean flammable;
    boolean placeOnSolid;
    boolean useSegments;
    Item ConversionDye;
    MapColor PetalColor;
    BlockSoundGroup SoundGroup;
    int light_level;

    public Block PETAL;

    private Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }

    private <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    private <T extends Item> T register_item(String name, T item) {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    private <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        register_item(name, new BlockItem(registered, settings));
        return registered;
    }

    private <T extends Block> T registerWithItem(String name, T block) {
        return registerWithItem(name, block, new Item.Settings());
    }

    public EC_Petal(String MOD_ID, String petal_ID, String PetalName, boolean flammable, boolean placeOnSolid, boolean useSegments, Item ConversionDye, MapColor PetalColor, BlockSoundGroup SoundGroup, int light_level) {
        this.MOD_ID = MOD_ID;
        this.petal_ID = petal_ID;
        this.PetalName = PetalName;
        this.flammable = flammable;
        this.placeOnSolid = placeOnSolid;
        this.useSegments = useSegments;
        this.ConversionDye = ConversionDye;
        this.PetalColor = PetalColor;
        this.SoundGroup = SoundGroup;
        this.light_level = light_level;

        if(this.useSegments)
        {
            if(this.placeOnSolid)
                PETAL = registerWithItem(this.petal_ID, new EC_FlowerbedBlock_Segment(AbstractBlock.Settings.create().mapColor(this.PetalColor).noCollision().sounds(this.SoundGroup).luminance((state) -> this.light_level).pistonBehavior(PistonBehavior.DESTROY)){ @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) { BlockPos blockPos = pos.down(); return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP); } });
            else
                PETAL = registerWithItem(this.petal_ID, new EC_FlowerbedBlock_Segment(AbstractBlock.Settings.create().mapColor(this.PetalColor).noCollision().sounds(this.SoundGroup).luminance((state) -> this.light_level).pistonBehavior(PistonBehavior.DESTROY)));
        }
        else
        {
            if(this.placeOnSolid)
                PETAL = registerWithItem(this.petal_ID, new FlowerbedBlock(AbstractBlock.Settings.create().mapColor(this.PetalColor).noCollision().sounds(this.SoundGroup).luminance((state) -> this.light_level).pistonBehavior(PistonBehavior.DESTROY)){ @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) { BlockPos blockPos = pos.down(); return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP); } });
            else
                PETAL = registerWithItem(this.petal_ID, new FlowerbedBlock(AbstractBlock.Settings.create().mapColor(this.PetalColor).noCollision().sounds(this.SoundGroup).luminance((state) -> this.light_level).pistonBehavior(PistonBehavior.DESTROY)));

        }
    }

    /* ********************  Load Functions  ******************** */
    public void loadGeneric() {
        if (this.flammable)
            FlammableBlockRegistry.getDefaultInstance().add(PETAL, 60, 100);

        CompostingChanceRegistry.INSTANCE.add(PETAL, 0.3f);
    }

    public void loadClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), PETAL);
    }

    /* ***************  Data Generator Functions  *************** */
    public <T extends FabricBlockLootTableProvider> void datagen_loot_table(T inst)
    {
        if(this.useSegments)
        {
            inst.addDrop(PETAL, LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F)).with((LootPoolEntry.Builder)inst.applyExplosionDecay(PETAL, ItemEntry.builder(PETAL).apply(IntStream.rangeClosed(1, 4).boxed().toList(), (flowerAmount) -> SetCountLootFunction.builder(ConstantLootNumberProvider.create((float)flowerAmount)).conditionally(BlockStatePropertyLootCondition.builder(PETAL).properties(net.minecraft.predicate.StatePredicate.Builder.create().exactMatch(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, flowerAmount))))))));
        }
        else
            inst.addDrop(PETAL, inst.flowerbedDrops(PETAL));
    }

    public void datagen_language(FabricLanguageProvider.TranslationBuilder translationBuilder)
    {
        translationBuilder.add(PETAL, this.PetalName);
    }

    public void datagen_block_model(BlockStateModelGenerator blockStateModelGenerator)
    {
        if(this.useSegments)
        {
            blockStateModelGenerator.registerItemModel(PETAL.asItem());
            Identifier identifier = TexturedModel.FLOWERBED_1.upload(PETAL, blockStateModelGenerator.modelCollector);
            Identifier identifier2 = TexturedModel.FLOWERBED_2.upload(PETAL, blockStateModelGenerator.modelCollector);
            Identifier identifier3 = TexturedModel.FLOWERBED_3.upload(PETAL, blockStateModelGenerator.modelCollector);
            Identifier identifier4 = TexturedModel.FLOWERBED_4.upload(PETAL, blockStateModelGenerator.modelCollector);
            blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(PETAL).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 1, new Integer[]{2, 3, 4}).set(Properties.HORIZONTAL_FACING, Direction.NORTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 1, new Integer[]{2, 3, 4}).set(Properties.HORIZONTAL_FACING, Direction.EAST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 1, new Integer[]{2, 3, 4}).set(Properties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R180)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 1, new Integer[]{2, 3, 4}).set(Properties.HORIZONTAL_FACING, Direction.WEST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier).put(VariantSettings.Y, VariantSettings.Rotation.R270)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 2, new Integer[]{3, 4}).set(Properties.HORIZONTAL_FACING, Direction.NORTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 2, new Integer[]{3, 4}).set(Properties.HORIZONTAL_FACING, Direction.EAST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 2, new Integer[]{3, 4}).set(Properties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R180)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 2, new Integer[]{3, 4}).set(Properties.HORIZONTAL_FACING, Direction.WEST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier2).put(VariantSettings.Y, VariantSettings.Rotation.R270)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 3, new Integer[]{4}).set(Properties.HORIZONTAL_FACING, Direction.NORTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 3, new Integer[]{4}).set(Properties.HORIZONTAL_FACING, Direction.EAST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 3, new Integer[]{4}).set(Properties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R180)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 3, new Integer[]{4}).set(Properties.HORIZONTAL_FACING, Direction.WEST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier3).put(VariantSettings.Y, VariantSettings.Rotation.R270)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 4).set(Properties.HORIZONTAL_FACING, Direction.NORTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 4).set(Properties.HORIZONTAL_FACING, Direction.EAST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R90)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 4).set(Properties.HORIZONTAL_FACING, Direction.SOUTH), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R180)).with(When.create().set(EC_FlowerbedBlock_Segment.FLOWER_AMOUNT, 4).set(Properties.HORIZONTAL_FACING, Direction.WEST), BlockStateVariant.create().put(VariantSettings.MODEL, identifier4).put(VariantSettings.Y, VariantSettings.Rotation.R270)));
        }
        else
            blockStateModelGenerator.registerFlowerbed(PETAL);
    }

    public void datagen_recipes(RecipeExporter exporter)
    {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, this.ConversionDye)
                .input(PETAL)
                .criterion(FabricRecipeProvider.hasItem(PETAL), FabricRecipeProvider.conditionsFromItem(PETAL))
                .offerTo(exporter);
    }
}
