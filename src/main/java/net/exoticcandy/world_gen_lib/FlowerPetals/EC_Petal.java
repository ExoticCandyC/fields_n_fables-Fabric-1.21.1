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
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class EC_Petal {
    String MOD_ID;
    String petal_ID;
    String PetalName;
    boolean flammable;
    boolean placeOnSolid;
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

    public EC_Petal(String MOD_ID, String petal_ID, String PetalName, boolean flammable, boolean placeOnSolid, Item ConversionDye, MapColor PetalColor, BlockSoundGroup SoundGroup, int light_level) {
        this.MOD_ID = MOD_ID;
        this.petal_ID = petal_ID;
        this.PetalName = PetalName;
        this.flammable = flammable;
        this.placeOnSolid = placeOnSolid;
        this.ConversionDye = ConversionDye;
        this.PetalColor = PetalColor;
        this.SoundGroup = SoundGroup;
        this.light_level = light_level;

        if(this.placeOnSolid)
            PETAL = registerWithItem(this.petal_ID, new FlowerbedBlock(AbstractBlock.Settings.create().mapColor(this.PetalColor).noCollision().sounds(this.SoundGroup).luminance((state) -> this.light_level).pistonBehavior(PistonBehavior.DESTROY)){ @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) { BlockPos blockPos = pos.down(); return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP); } });
        else
            PETAL = registerWithItem(this.petal_ID, new FlowerbedBlock(AbstractBlock.Settings.create().mapColor(this.PetalColor).noCollision().sounds(this.SoundGroup).luminance((state) -> this.light_level).pistonBehavior(PistonBehavior.DESTROY)));
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
        inst.addDrop(PETAL, inst.flowerbedDrops(PETAL));
    }

    public void datagen_language(FabricLanguageProvider.TranslationBuilder translationBuilder)
    {
        translationBuilder.add(PETAL, this.PetalName);
    }

    public void datagen_block_model(BlockStateModelGenerator blockStateModelGenerator)
    {
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
