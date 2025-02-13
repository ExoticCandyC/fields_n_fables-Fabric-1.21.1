package net.exoticcandy.world_gen_lib.Tree;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.fields_n_fables.init.BlockInit;
import net.exoticcandy.world_gen_lib.FlowerPetals.FallingLeavesBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.recipe.Ingredient;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.data.family.BlockFamily;
import org.jetbrains.annotations.NotNull;

public class EC_Tree
{
    /* ********************  Class Features  ******************** */
    public String   MOD_ID;
    public String   tree_ID_Suffix;
    public String   tree_Name;
    public boolean  flowerLeaves;
    public boolean  fallingLeaves;
    public MapColor LogColor;
    public MapColor PlankColor;
    public MapColor LeavesColor;
    public boolean  flammableLeaves;
    public boolean  flammableWood;
    public SaplingGenerator Sapling_Generator;

    /* ************************  Blocks  ************************ */
    public BlockSetType BLOCK_SET;
    public WoodType     WOODTYPE;
    public Block        LOG;
    public Block        WOOD;
    public Block        STRIPPED_LOG;
    public Block        STRIPPED_WOOD;
    public Block        PLANKS;
    public Block        STAIRS;
    public Block        SLAB;
    public Block        FENCE;
    public Block        FENCE_GATE;
    public Block        DOOR;
    public Block        TRAPDOOR;
    public Block        PRESSURE_PLATE;
    public Block        BUTTON;
    public Block        LEAVES;
    public Block        SAPLING;
    public Block        POTTED_SAPLING;

    public SimpleParticleType FALLING_LEAVES_PARTICLE;

    public Identifier                    SIGN_TEXTURE;
    public Identifier                    HANGING_SIGN_TEXTURE;
    public Identifier                    HANGING_GUI_SIGN_TEXTURE;
    public TerraformSignBlock            SIGN;
    public TerraformWallSignBlock        WALL_SIGN;
    public TerraformHangingSignBlock     HANGING_SIGN;
    public TerraformWallHangingSignBlock WALL_HANGING_SIGN;
    public SignItem                      SIGN_ITEM;
    public HangingSignItem               HANGING_SIGN_ITEM;

    public Identifier                     BOAT_ID;
    public Identifier                     CHEST_BOAT_ID;
    public RegistryKey<TerraformBoatType> BOAT_KEY;
    public Item                           BOAT;
    public Item                           CHEST_BOAT;
    public TerraformBoatType              BOAT_TYPE;

    public TagKey<Block> BLOCK_TAG_LIST_LOGS;
    public TagKey<Item>  ITEM_TAG_LIST_LOGS;

    /* ******************  Registry Functions  ****************** */
    private Identifier id(String path)
    {
        return Identifier.of(MOD_ID, path);
    }

    private <T extends Block> T register(String name, T block)
    {
        return Registry.register(Registries.BLOCK, id(name), block);
    }

    private <T extends Item> T register_item(String name, T item)
    {
        return Registry.register(Registries.ITEM, id(name), item);
    }

    private <T extends Block> T registerWithItem(String name, T block, Item.Settings settings)
    {
        T registered = register(name, block);
        register_item(name, new BlockItem(registered, settings));
        return registered;
    }

    private <T extends Block> T registerWithItem(String name, T block)
    {
        return registerWithItem(name, block, new Item.Settings());
    }

    private static FlowerPotBlock createFlowerPot(Block block, FeatureFlag... featureFlags)
    {
        AbstractBlock.Settings properties = AbstractBlock.Settings.create()
                .strength(0.0F)
                .suffocates(Blocks::never)
                .blockVision(Blocks::never)
                .pistonBehavior(PistonBehavior.DESTROY)
                .noCollision();

        if (featureFlags.length > 0) {
            properties = properties.requires(featureFlags);
        }

        return new FlowerPotBlock(block, properties);
    }

    private static TerraformBoatType register_boatType(RegistryKey<TerraformBoatType> key, TerraformBoatType type)
    {
        return Registry.register(TerraformBoatTypeRegistry.INSTANCE, key, type);
    }

    /* *******************  Helper Functions  ******************* */
    public static RegistryKey<ConfiguredFeature<?, ?>> ConfiguredFeatureRegisterKey(String mod_id, String name)
    {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(mod_id, name));
    }

    /* ******************  Block Construction  ****************** */
    private void registerTree()
    {
        if(this.flowerLeaves)
        {
            BLOCK_SET = new BlockSetType(id(tree_ID_Suffix).toString(), true, true, true, BlockSetType.CHERRY.pressurePlateSensitivity(), BlockSoundGroup.WOOD, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
            WOODTYPE = new WoodTypeBuilder().soundGroup(BlockSoundGroup.WOOD).hangingSignSoundGroup(BlockSoundGroup.HANGING_SIGN).fenceGateCloseSound(SoundEvents.BLOCK_FENCE_GATE_CLOSE).fenceGateOpenSound(SoundEvents.BLOCK_FENCE_GATE_OPEN).register(id(tree_ID_Suffix), BLOCK_SET);
            LOG = registerWithItem(tree_ID_Suffix + "_log", new PillarBlock(Blocks.CHERRY_LOG.getSettings().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? PlankColor : LogColor)));
            WOOD = registerWithItem(tree_ID_Suffix + "_wood", new PillarBlock(Blocks.CHERRY_WOOD.getSettings().mapColor(LogColor)));
            STRIPPED_LOG = registerWithItem("stripped_" + tree_ID_Suffix + "_log", new PillarBlock(Blocks.STRIPPED_CHERRY_LOG.getSettings().mapColor(PlankColor)));
            STRIPPED_WOOD = registerWithItem("stripped_" + tree_ID_Suffix + "_wood", new PillarBlock(Blocks.STRIPPED_CHERRY_WOOD.getSettings().mapColor(PlankColor)));
            PLANKS = registerWithItem(tree_ID_Suffix + "_planks", new Block(Blocks.CHERRY_PLANKS.getSettings().mapColor(PlankColor)));
            STAIRS = registerWithItem(tree_ID_Suffix + "_stairs", new StairsBlock(PLANKS.getDefaultState(), Blocks.CHERRY_STAIRS.getSettings().mapColor(PlankColor)));
            SLAB = registerWithItem(tree_ID_Suffix + "_slab", new SlabBlock(Blocks.CHERRY_SLAB.getSettings().mapColor(PlankColor)));
            FENCE = registerWithItem(tree_ID_Suffix + "_fence", new FenceBlock(Blocks.CHERRY_FENCE.getSettings().mapColor(PlankColor)));
            FENCE_GATE = registerWithItem(tree_ID_Suffix + "_fence_gate", new FenceGateBlock(WOODTYPE, Blocks.CHERRY_FENCE_GATE.getSettings().mapColor(PlankColor)));
            DOOR = registerWithItem(tree_ID_Suffix + "_door", new DoorBlock(BLOCK_SET, Blocks.CHERRY_DOOR.getSettings().mapColor(PlankColor)));
            TRAPDOOR = registerWithItem(tree_ID_Suffix + "_trapdoor", new TrapdoorBlock(BLOCK_SET, Blocks.CHERRY_TRAPDOOR.getSettings().mapColor(PlankColor)));
            PRESSURE_PLATE = registerWithItem(tree_ID_Suffix + "_pressure_plate", new PressurePlateBlock(BLOCK_SET, Blocks.CHERRY_PRESSURE_PLATE.getSettings().mapColor(PlankColor)));
            BUTTON = registerWithItem(tree_ID_Suffix + "_button", new ButtonBlock(BLOCK_SET, 30, Blocks.CHERRY_BUTTON.getSettings()));
            if(this.fallingLeaves)
                LEAVES = registerWithItem(tree_ID_Suffix + "_leaves", new FallingLeavesBlock(FALLING_LEAVES_PARTICLE, Blocks.CHERRY_LEAVES.getSettings().mapColor(LeavesColor)));
            else
                LEAVES = registerWithItem(tree_ID_Suffix + "_leaves", new LeavesBlock(Blocks.CHERRY_LEAVES.getSettings().mapColor(LeavesColor)));
            SAPLING = registerWithItem(tree_ID_Suffix + "_sapling", new SaplingBlock(Sapling_Generator, Blocks.CHERRY_SAPLING.getSettings()));
            POTTED_SAPLING = register("potted_" + tree_ID_Suffix + "_sapling", createFlowerPot(SAPLING));
        }
        else
        {
            BLOCK_SET = new BlockSetType(id(tree_ID_Suffix).toString(), true, true, true, BlockSetType.OAK.pressurePlateSensitivity(), BlockSoundGroup.WOOD, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
            WOODTYPE = new WoodTypeBuilder().soundGroup(BlockSoundGroup.WOOD).hangingSignSoundGroup(BlockSoundGroup.HANGING_SIGN).fenceGateCloseSound(SoundEvents.BLOCK_FENCE_GATE_CLOSE).fenceGateOpenSound(SoundEvents.BLOCK_FENCE_GATE_OPEN).register(id(tree_ID_Suffix), BLOCK_SET);
            LOG = registerWithItem(tree_ID_Suffix + "_log", new PillarBlock(Blocks.OAK_LOG.getSettings().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? PlankColor : LogColor)));
            WOOD = registerWithItem(tree_ID_Suffix + "_wood", new PillarBlock(Blocks.OAK_WOOD.getSettings().mapColor(LogColor)));
            STRIPPED_LOG = registerWithItem("stripped_" + tree_ID_Suffix + "_log", new PillarBlock(Blocks.STRIPPED_OAK_LOG.getSettings().mapColor(PlankColor)));
            STRIPPED_WOOD = registerWithItem("stripped_" + tree_ID_Suffix + "_wood", new PillarBlock(Blocks.STRIPPED_OAK_WOOD.getSettings().mapColor(PlankColor)));
            PLANKS = registerWithItem(tree_ID_Suffix + "_planks", new Block(Blocks.OAK_PLANKS.getSettings().mapColor(PlankColor)));
            STAIRS = registerWithItem(tree_ID_Suffix + "_stairs", new StairsBlock(PLANKS.getDefaultState(), Blocks.OAK_STAIRS.getSettings().mapColor(PlankColor)));
            SLAB = registerWithItem(tree_ID_Suffix + "_slab", new SlabBlock(Blocks.OAK_SLAB.getSettings().mapColor(PlankColor)));
            FENCE = registerWithItem(tree_ID_Suffix + "_fence", new FenceBlock(Blocks.OAK_FENCE.getSettings().mapColor(PlankColor)));
            FENCE_GATE = registerWithItem(tree_ID_Suffix + "_fence_gate", new FenceGateBlock(WOODTYPE, Blocks.OAK_FENCE_GATE.getSettings().mapColor(PlankColor)));
            DOOR = registerWithItem(tree_ID_Suffix + "_door", new DoorBlock(BLOCK_SET, Blocks.OAK_DOOR.getSettings().mapColor(PlankColor)));
            TRAPDOOR = registerWithItem(tree_ID_Suffix + "_trapdoor", new TrapdoorBlock(BLOCK_SET, Blocks.OAK_TRAPDOOR.getSettings().mapColor(PlankColor)));
            PRESSURE_PLATE = registerWithItem(tree_ID_Suffix + "_pressure_plate", new PressurePlateBlock(BLOCK_SET, Blocks.OAK_PRESSURE_PLATE.getSettings().mapColor(PlankColor)));
            BUTTON = registerWithItem(tree_ID_Suffix + "_button", new ButtonBlock(BLOCK_SET, 30, Blocks.OAK_BUTTON.getSettings()));
            if(this.fallingLeaves)
                LEAVES = registerWithItem(tree_ID_Suffix + "_leaves", new FallingLeavesBlock(FALLING_LEAVES_PARTICLE, Blocks.OAK_LEAVES.getSettings().mapColor(LeavesColor)));
            else
                LEAVES = registerWithItem(tree_ID_Suffix + "_leaves", new LeavesBlock(Blocks.OAK_LEAVES.getSettings().mapColor(LeavesColor)));
            SAPLING = registerWithItem(tree_ID_Suffix + "_sapling", new SaplingBlock(Sapling_Generator, Blocks.OAK_SAPLING.getSettings()));
            POTTED_SAPLING = register("potted_" + tree_ID_Suffix + "_sapling", createFlowerPot(SAPLING));
        }

        SIGN_TEXTURE             = id("entity/signs/" + tree_ID_Suffix);
        HANGING_SIGN_TEXTURE     = id("entity/signs/hanging/" + tree_ID_Suffix);
        HANGING_GUI_SIGN_TEXTURE = id("textures/gui/hanging_signs/" + tree_ID_Suffix);
        SIGN                     = register(tree_ID_Suffix + "_sign", new TerraformSignBlock(SIGN_TEXTURE, AbstractBlock.Settings.create().mapColor(PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable()));
        WALL_SIGN                = register(tree_ID_Suffix + "_wall_sign", new TerraformWallSignBlock(SIGN_TEXTURE, AbstractBlock.Settings.create().mapColor(PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable()));
        HANGING_SIGN             = register(tree_ID_Suffix + "_hanging_sign", new TerraformHangingSignBlock(HANGING_SIGN_TEXTURE, HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.create().mapColor(PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable()));
        WALL_HANGING_SIGN        = register(tree_ID_Suffix + "_wall_hanging_sign", new TerraformWallHangingSignBlock(HANGING_SIGN_TEXTURE, HANGING_GUI_SIGN_TEXTURE, AbstractBlock.Settings.create().mapColor(PLANKS.getDefaultMapColor()).solid().instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable()));
        SIGN_ITEM                = register_item(tree_ID_Suffix + "_sign", new SignItem(new Item.Settings().maxCount(16), SIGN, WALL_SIGN));
        HANGING_SIGN_ITEM        = register_item(tree_ID_Suffix + "_hanging_sign", new HangingSignItem(HANGING_SIGN, WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

        BOAT_ID       = id(tree_ID_Suffix + "_boat");
        CHEST_BOAT_ID = id(tree_ID_Suffix + "_chest_boat");
        BOAT_KEY      = TerraformBoatTypeRegistry.createKey(BOAT_ID);
        BOAT          = TerraformBoatItemHelper.registerBoatItem(BOAT_ID, BOAT_KEY, false);
        CHEST_BOAT    = TerraformBoatItemHelper.registerBoatItem(CHEST_BOAT_ID, BOAT_KEY, true);
        BOAT_TYPE     = register_boatType(BOAT_KEY, new TerraformBoatType.Builder().item(BOAT).chestItem(CHEST_BOAT).planks(PLANKS.asItem()).build());

        BLOCK_TAG_LIST_LOGS = TagKey.of(RegistryKeys.BLOCK, id(tree_ID_Suffix + "_logs"));
        ITEM_TAG_LIST_LOGS  = TagKey.of(RegistryKeys.ITEM , id(tree_ID_Suffix + "_logs"));
    }

    /* ********************  Load Functions  ******************** */
    public void loadGeneric()
    {
        StrippableBlockRegistry.register(LOG, STRIPPED_LOG);
        StrippableBlockRegistry.register(WOOD, STRIPPED_WOOD);

        if(this.flammableLeaves)
            FlammableBlockRegistry.getDefaultInstance().add(LEAVES       , 30, 60);
        if(this.flammableWood)
        {
            FlammableBlockRegistry.getDefaultInstance().add(LOG          , 5 , 5 );
            FlammableBlockRegistry.getDefaultInstance().add(WOOD         , 5 , 5 );
            FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_LOG , 5 , 5 );
            FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_WOOD, 5 , 5 );
            FlammableBlockRegistry.getDefaultInstance().add(PLANKS       , 5 , 20);
            FlammableBlockRegistry.getDefaultInstance().add(SLAB         , 5 , 20);
            FlammableBlockRegistry.getDefaultInstance().add(STAIRS       , 5 , 20);
            FlammableBlockRegistry.getDefaultInstance().add(FENCE        , 5 , 20);
            FlammableBlockRegistry.getDefaultInstance().add(FENCE_GATE   , 5 , 20);
        }

        CompostingChanceRegistry.INSTANCE.add(SAPLING, 0.3f);
        CompostingChanceRegistry.INSTANCE.add(LEAVES , 0.3f);
    }

    @Environment(EnvType.CLIENT)
    public static class FallingLeavesParticle extends net.minecraft.client.particle.CherryLeavesParticle
    {
        public FallingLeavesParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider)
        {
            super(world, x, y, z, spriteProvider);
        }

        @Environment(EnvType.CLIENT)
        public static class Factory implements ParticleFactory<SimpleParticleType> {
            private final SpriteProvider spriteProvider;

            public Factory(SpriteProvider spriteProvider) {
                this.spriteProvider = spriteProvider;
            }

            public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
                return new FallingLeavesParticle(clientWorld, d, e, f, /*g, h, i,*/ this.spriteProvider);
            }
        }
    }

    public void loadClient()
    {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), POTTED_SAPLING, DOOR, SAPLING, LEAVES, TRAPDOOR);
        TerraformBoatClientHelper.registerModelLayers(BOAT_ID, false);

        if(this.fallingLeaves)
            ParticleFactoryRegistry.getInstance().register(FALLING_LEAVES_PARTICLE, FallingLeavesParticle.Factory::new);
    }

    public void loadDatagen()
    {

    }

    /* ***************  Data Generator Functions  *************** */
    public <T extends FabricBlockLootTableProvider> void datagen_loot_table(T inst)
    {
        float []SAPLING_DROP_CHANCE = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
        inst.addPottedPlantDrops(POTTED_SAPLING);
        inst.addDrop(LEAVES, inst.leavesDrops(LEAVES, SAPLING, SAPLING_DROP_CHANCE));
        inst.addDrop(DOOR, inst.doorDrops(DOOR));
        inst.addDrop(LOG);
        inst.addDrop(WOOD);
        inst.addDrop(STRIPPED_LOG);
        inst.addDrop(STRIPPED_WOOD);
        inst.addDrop(SAPLING);
        inst.addDrop(PLANKS);
        inst.addDrop(SLAB);
        inst.addDrop(STAIRS);
        inst.addDrop(FENCE);
        inst.addDrop(FENCE_GATE);
        inst.addDrop(TRAPDOOR);
        inst.addDrop(BUTTON);
        inst.addDrop(PRESSURE_PLATE);
        inst.addDrop(     SIGN, SIGN_ITEM);
        inst.addDrop(WALL_SIGN, SIGN_ITEM);
        inst.addDrop(     HANGING_SIGN, HANGING_SIGN_ITEM); /* Break Particle Issues */
        inst.addDrop(WALL_HANGING_SIGN, HANGING_SIGN_ITEM); /* Break Particle Issues */
    }

    public void datagen_language(FabricLanguageProvider.TranslationBuilder translationBuilder)
    {
        translationBuilder.add(STRIPPED_LOG      , "Stripped " + tree_Name + " Log");
        translationBuilder.add(STRIPPED_WOOD     , "Stripped " + tree_Name + " Wood");
        translationBuilder.add(POTTED_SAPLING    , "Potted "   + tree_Name + " Sapling");
        translationBuilder.add(LOG               , tree_Name + " Log");
        translationBuilder.add(WOOD              , tree_Name + " Wood");
        translationBuilder.add(LEAVES            , tree_Name + " Leaves");
        translationBuilder.add(SAPLING           , tree_Name + " Sapling");
        translationBuilder.add(PLANKS            , tree_Name + " Planks");
        translationBuilder.add(SLAB              , tree_Name + " Slab");
        translationBuilder.add(STAIRS            , tree_Name + " Stairs");
        translationBuilder.add(FENCE             , tree_Name + " Fence");
        translationBuilder.add(FENCE_GATE        , tree_Name + " Fence Gate");
        translationBuilder.add(DOOR              , tree_Name + " Door");
        translationBuilder.add(TRAPDOOR          , tree_Name + " Trapdoor");
        translationBuilder.add(BUTTON            , tree_Name + " Button");
        translationBuilder.add(PRESSURE_PLATE    , tree_Name + " Pressure Plate");
        translationBuilder.add(SIGN_ITEM         , tree_Name + " Sign");
        translationBuilder.add(HANGING_SIGN_ITEM , tree_Name + " Hanging Sign");
        translationBuilder.add(BOAT              , tree_Name + " Boat");
        translationBuilder.add(CHEST_BOAT        , tree_Name + " Chest Boat");
        translationBuilder.add(ITEM_TAG_LIST_LOGS, tree_Name + " Logs");
    }

    private static @NotNull String hasTag(@NotNull TagKey<Item> tag)
    {
        return "has_" + tag.id().toString();
    }

    public void datagen_recipes(RecipeExporter exporter)
    {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, PLANKS, 4)
                .input(Ingredient.fromTag(ITEM_TAG_LIST_LOGS))
                .criterion(hasTag(ITEM_TAG_LIST_LOGS), FabricRecipeProvider.conditionsFromTag(ITEM_TAG_LIST_LOGS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, WOOD, 3)
                .input('L', LOG)
                .pattern("LL")
                .pattern("LL")
                .criterion(FabricRecipeProvider.hasItem(LOG), FabricRecipeProvider.conditionsFromItem(LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STRIPPED_WOOD, 3)
                .input('L', STRIPPED_LOG)
                .pattern("LL")
                .pattern("LL")
                .criterion(FabricRecipeProvider.hasItem(STRIPPED_LOG), FabricRecipeProvider.conditionsFromItem(STRIPPED_LOG))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, SLAB, 6)
                .input('T', PLANKS)
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, STAIRS, 4)
                .input('T', PLANKS)
                .pattern("T  ")
                .pattern("TT ")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FENCE, 3)
                .input('T', PLANKS)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("TST")
                .pattern("TST")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, FENCE_GATE)
                .input('T', PLANKS)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("STS")
                .pattern("STS")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, DOOR, 3)
                .input('T', PLANKS)
                .pattern("TT")
                .pattern("TT")
                .pattern("TT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, TRAPDOOR, 2)
                .input('T', PLANKS)
                .pattern("TTT")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, BUTTON)
                .input(PLANKS)
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, PRESSURE_PLATE)
                .input('T', PLANKS)
                .pattern("TT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, SIGN_ITEM, 3)
                .input('T', PLANKS)
                .input('S', ConventionalItemTags.WOODEN_RODS)
                .pattern("TTT")
                .pattern("TTT")
                .pattern(" S ")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .criterion(hasTag(ConventionalItemTags.WOODEN_RODS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_RODS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, HANGING_SIGN_ITEM, 6)
                .input('T', PLANKS)
                .input('C', ConventionalItemTags.CHAINS)
                .pattern("C C")
                .pattern("TTT")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .criterion(hasTag(ConventionalItemTags.CHAINS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.CHAINS))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, BOAT)
                .input('T', PLANKS)
                .pattern("T T")
                .pattern("TTT")
                .criterion(FabricRecipeProvider.hasItem(PLANKS), FabricRecipeProvider.conditionsFromItem(PLANKS))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.TRANSPORTATION, CHEST_BOAT)
                .input(BOAT)
                .input(ConventionalItemTags.WOODEN_CHESTS)
                .criterion(FabricRecipeProvider.hasItem(BOAT), FabricRecipeProvider.conditionsFromItem(BOAT))
                .criterion(hasTag(ConventionalItemTags.WOODEN_CHESTS), FabricRecipeProvider.conditionsFromTag(ConventionalItemTags.WOODEN_CHESTS))
                .offerTo(exporter);

        var woodenFamily = new BlockFamily.Builder(PLANKS)
                .button(BUTTON)
                .fence(FENCE)
                .fenceGate(FENCE_GATE)
                .pressurePlate(PRESSURE_PLATE)
                .sign(SIGN, WALL_SIGN)
                .slab(SLAB)
                .stairs(STAIRS)
                .door(DOOR)
                .trapdoor(TRAPDOOR)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();

        FabricRecipeProvider.generateFamily(exporter, woodenFamily, FeatureSet.empty());
    }

    public void datagen_block_model(BlockStateModelGenerator blockStateModelGenerator)
    {
        blockStateModelGenerator.registerFlowerPotPlant(SAPLING, POTTED_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerLog(LOG).log(LOG).wood(WOOD);
        blockStateModelGenerator.registerLog(STRIPPED_LOG).log(STRIPPED_LOG).wood(STRIPPED_WOOD);
        blockStateModelGenerator.registerSimpleCubeAll(LEAVES);
        //blockStateModelGenerator.registerTintableCross(SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerHangingSign(STRIPPED_LOG, HANGING_SIGN, WALL_HANGING_SIGN);
        var exampleFamily = new BlockFamily.Builder(PLANKS).button(BUTTON).fence(FENCE).fenceGate(FENCE_GATE).pressurePlate(PRESSURE_PLATE).sign(SIGN, WALL_SIGN).slab(SLAB).stairs(STAIRS).door(DOOR).trapdoor(TRAPDOOR).group("wooden").unlockCriterionName("has_planks").build();
        blockStateModelGenerator.registerCubeAllModelTexturePool(exampleFamily.getBaseBlock()).family(exampleFamily);
    }

    public void datagen_item_model(ItemModelGenerator itemModelGenerator)
    {
        itemModelGenerator.register(BOAT, Models.GENERATED);
        itemModelGenerator.register(CHEST_BOAT, Models.GENERATED);
    }

    /* *********************  Constructors  ********************* */
    public EC_Tree(String MOD_ID, String tree_ID_Suffix, String tree_Name, boolean  flowerLeaves, boolean  fallingLeaves, SaplingGenerator Sapling_Generator, MapColor LogColor, MapColor PlankColor, MapColor LeavesColor)
    {
        this.MOD_ID            = MOD_ID;
        this.tree_ID_Suffix    = tree_ID_Suffix;
        this.tree_Name         = tree_Name;
        this.flowerLeaves      = flowerLeaves;
        this.fallingLeaves     = fallingLeaves;
        this.Sapling_Generator = Sapling_Generator;
        this.LogColor          = LogColor;
        this.PlankColor        = PlankColor;
        this.LeavesColor       = LeavesColor;
        this.flammableLeaves   = true;
        this.flammableWood     = true;

        if(this.fallingLeaves)
        {
            FALLING_LEAVES_PARTICLE = FabricParticleTypes.simple();
            Registry.register(Registries.PARTICLE_TYPE, id(tree_ID_Suffix + "_leaves"), FALLING_LEAVES_PARTICLE);
        }
        else
            FALLING_LEAVES_PARTICLE = null;

        registerTree();
        //net.exoticcandy.world_gen_lib.Tree.list.Trees.trees.add(this);
    }

    public EC_Tree(String MOD_ID, String tree_ID_Suffix, String tree_Name, boolean  flowerLeaves, boolean  fallingLeaves, SaplingGenerator Sapling_Generator, MapColor LogColor, MapColor PlankColor, MapColor LeavesColor, boolean flammableLeaves, boolean flammableWood)
    {
        this.MOD_ID            = MOD_ID;
        this.tree_ID_Suffix    = tree_ID_Suffix;
        this.tree_Name         = tree_Name;
        this.flowerLeaves      = flowerLeaves;
        this.fallingLeaves     = fallingLeaves;
        this.Sapling_Generator = Sapling_Generator;
        this.LogColor          = LogColor;
        this.PlankColor        = PlankColor;
        this.LeavesColor       = LeavesColor;
        this.flammableLeaves   = flammableLeaves;
        this.flammableWood     = flammableWood;

        if(this.fallingLeaves)
        {
            FALLING_LEAVES_PARTICLE = FabricParticleTypes.simple();
            Registry.register(Registries.PARTICLE_TYPE, id(tree_ID_Suffix + "_leaves"), FALLING_LEAVES_PARTICLE);
        }
        else
            FALLING_LEAVES_PARTICLE = null;

        registerTree();
        //net.exoticcandy.world_gen_lib.Tree.list.Trees.trees.add(this);
    }
}

/* SAPLING = registerWithItem("echo_sapling", new SaplingBlock(Sapling_Generator, Blocks.OAK_SAPLING.getSettings()) {
            @Override
            protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
                return pState.is(DDTags.Blocks.ECHO_SOIL);
            }
   });*/
