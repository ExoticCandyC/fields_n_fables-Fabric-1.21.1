package net.exoticcandy.fields_n_fables.init;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.intprovider.WeightedListIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.registry.Registerable;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.registry.RegistryKeys;


import java.util.List;
import java.util.Optional;

/*
    Tags:
    define logs (all 4 logs/woods) (both blocks and items)
    logs that burn (both blocks and items)
    leaves (both blocks and items)
    and all other tags for all other block types (both blocks and items)
    add loot table
    add lang file

    Recipes
 */

public class BlockInit {
    // https://github.com/KyaniteMods/DeeperAndDarker/blob/fabric-1.21/src/main/java/com/kyanite/deeperdarker/content/DDBlocks.java

    /* *************************************** */
    /* *************************************** */
    /* *************************************** */
    /* *************************************** */
    /* *************************************** */

    public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIG_WISTERIA          = EC_Tree.ConfiguredFeatureRegisterKey("echo");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIG_WISTERIA_BEES_005 = EC_Tree.ConfiguredFeatureRegisterKey("echo_bees_005");
    public static final SaplingGenerator WISTERIA_SAPLING_GENERATOR = new SaplingGenerator("echo", Optional.empty(), Optional.of(CONFIG_WISTERIA), Optional.of(CONFIG_WISTERIA_BEES_005));

    public static final EC_Tree Wisteria_Tree = new EC_Tree(FieldsFables.MOD_ID, "wisteria", "Wisteria", WISTERIA_SAPLING_GENERATOR, MapColor.GRAY, MapColor.PURPLE, MapColor.PALE_PURPLE);


    /* *************************************** */
    /* *************************************** */
    /* *************************************** */
    /* *************************************** */
    /* *************************************** */

    private static final BlockSetType ECHO_SET = new BlockSetType(FieldsFables.id("echo").toString(), true, true, true, BlockSetType.OAK.pressurePlateSensitivity(), BlockSoundGroup.WOOD, SoundEvents.BLOCK_WOODEN_DOOR_CLOSE, SoundEvents.BLOCK_WOODEN_DOOR_OPEN, SoundEvents.BLOCK_WOODEN_TRAPDOOR_CLOSE, SoundEvents.BLOCK_WOODEN_TRAPDOOR_OPEN, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_WOODEN_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF, SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON);
    public static final WoodType ECHO = new WoodTypeBuilder().soundGroup(BlockSoundGroup.WOOD).hangingSignSoundGroup(BlockSoundGroup.HANGING_SIGN).fenceGateCloseSound(SoundEvents.BLOCK_FENCE_GATE_CLOSE).fenceGateOpenSound(SoundEvents.BLOCK_FENCE_GATE_OPEN).register(FieldsFables.id("echo"), ECHO_SET);

    public static final Block ECHO_LOG = registerWithItem("echo_log", new PillarBlock(Blocks.OAK_LOG.getSettings().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.LIGHT_GRAY : MapColor.PURPLE)));
    public static final Block ECHO_WOOD = registerWithItem("echo_wood", new PillarBlock(Blocks.OAK_WOOD.getSettings().mapColor(MapColor.PURPLE)));
    public static final Block STRIPPED_ECHO_LOG = registerWithItem("stripped_echo_log", new PillarBlock(Blocks.STRIPPED_OAK_LOG.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block STRIPPED_ECHO_WOOD = registerWithItem("stripped_echo_wood", new PillarBlock(Blocks.STRIPPED_OAK_WOOD.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_PLANKS = registerWithItem("echo_planks", new Block(Blocks.OAK_PLANKS.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_STAIRS = registerWithItem("echo_stairs", new StairsBlock(ECHO_PLANKS.getDefaultState(), Blocks.OAK_STAIRS.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_SLAB = registerWithItem("echo_slab", new SlabBlock(Blocks.OAK_SLAB.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_FENCE = registerWithItem("echo_fence", new FenceBlock(Blocks.OAK_FENCE.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_FENCE_GATE = registerWithItem("echo_fence_gate", new FenceGateBlock(ECHO, Blocks.OAK_FENCE_GATE.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_DOOR = registerWithItem("echo_door", new DoorBlock(ECHO_SET, Blocks.OAK_DOOR.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_TRAPDOOR = registerWithItem("echo_trapdoor", new TrapdoorBlock(ECHO_SET, Blocks.OAK_TRAPDOOR.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_PRESSURE_PLATE = registerWithItem("echo_pressure_plate", new PressurePlateBlock(ECHO_SET, Blocks.OAK_PRESSURE_PLATE.getSettings().mapColor(MapColor.LIGHT_GRAY)));
    public static final Block ECHO_BUTTON = registerWithItem("echo_button", new ButtonBlock(ECHO_SET, 30, Blocks.OAK_BUTTON.getSettings()));
    public static final Block ECHO_LEAVES = registerWithItem("echo_leaves", new LeavesBlock(Blocks.OAK_LEAVES.getSettings().mapColor(MapColor.PURPLE)));


    /* Add the TreeGrower */
    private static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, FieldsFables.id(name));
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIG_ECHO          = registerKey("echo");
    public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIG_ECHO_BEES_005 = registerKey("echo_bees_005");

    public static final SaplingGenerator ECHO_SAPLING_GENERATOR = new SaplingGenerator("echo", Optional.empty(), Optional.of(CONFIG_ECHO), Optional.of(CONFIG_ECHO_BEES_005));
    public static final Block ECHO_SAPLING = registerWithItem("echo_sapling", new SaplingBlock(ECHO_SAPLING_GENERATOR, Blocks.OAK_SAPLING.getSettings()));

    public static final Block POTTED_ECHO_SAPLING = registerWithoutItem("potted_echo_sapling", createFlowerPot(ECHO_SAPLING));

    private static FlowerPotBlock createFlowerPot(Block block, FeatureFlag... featureFlags) {
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



    public static final Identifier ECHO_SIGN_TEXTURE = FieldsFables.id("entity/signs/echo");
    public static final Identifier ECHO_HANGING_SIGN_TEXTURE = FieldsFables.id("entity/signs/hanging/echo");
    public static final Identifier ECHO_HANGING_GUI_SIGN_TEXTURE = FieldsFables.id("textures/gui/hanging_signs/echo");

    public static final TerraformSignBlock ECHO_SIGN = registerWithoutItem("echo_sign",
            new TerraformSignBlock(ECHO_SIGN_TEXTURE,
                    AbstractBlock.Settings.create()
                            .mapColor(ECHO_PLANKS.getDefaultMapColor())
                            .solid()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollision()
                            .strength(1.0F)
                            .burnable()));

    public static final TerraformWallSignBlock ECHO_WALL_SIGN = register("echo_wall_sign",
            new TerraformWallSignBlock(ECHO_SIGN_TEXTURE,
                    AbstractBlock.Settings.create()
                            .mapColor(ECHO_PLANKS.getDefaultMapColor())
                            .solid()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollision()
                            .strength(1.0F)
                            .burnable()));

    public static final TerraformHangingSignBlock ECHO_HANGING_SIGN = register("echo_hanging_sign",
            new TerraformHangingSignBlock(ECHO_HANGING_SIGN_TEXTURE, ECHO_HANGING_GUI_SIGN_TEXTURE,
                    AbstractBlock.Settings.create()
                            .mapColor(ECHO_PLANKS.getDefaultMapColor())
                            .solid()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollision()
                            .strength(1.0F)
                            .burnable()));

    public static final TerraformWallHangingSignBlock ECHO_WALL_HANGING_SIGN = register("echo_wall_hanging_sign",
            new TerraformWallHangingSignBlock(ECHO_HANGING_SIGN_TEXTURE, ECHO_HANGING_GUI_SIGN_TEXTURE,
                    AbstractBlock.Settings.create()
                            .mapColor(ECHO_PLANKS.getDefaultMapColor())
                            .solid()
                            .instrument(NoteBlockInstrument.BASS)
                            .noCollision()
                            .strength(1.0F)
                            .burnable()));

    public static final SignItem ECHO_SIGN_ITEM = register_item("echo_sign",
            new SignItem(new Item.Settings().maxCount(16), BlockInit.ECHO_SIGN, BlockInit.ECHO_WALL_SIGN));

    public static final HangingSignItem ECHO_HANGING_SIGN_ITEM = register_item("echo_hanging_sign",
            new HangingSignItem(BlockInit.ECHO_HANGING_SIGN, BlockInit.ECHO_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));

    public static final Identifier ECHO_BOAT_ID = FieldsFables.id("echo_boat");
    public static final Identifier ECHO_CHEST_BOAT_ID = FieldsFables.id("echo_chest_boat");
    public static final RegistryKey<TerraformBoatType> ECHO_BOAT_KEY = TerraformBoatTypeRegistry.createKey(ECHO_BOAT_ID);

    public static final Item ECHO_BOAT =
            TerraformBoatItemHelper.registerBoatItem(ECHO_BOAT_ID, ECHO_BOAT_KEY, false);

    public static final Item ECHO_CHEST_BOAT =
            TerraformBoatItemHelper.registerBoatItem(ECHO_CHEST_BOAT_ID, ECHO_BOAT_KEY, true);

    public static TerraformBoatType ECHO_BOAT_TYPE = register_boatType(ECHO_BOAT_KEY, new TerraformBoatType.Builder()
                                                                        .item(ECHO_BOAT)
                                                                        .chestItem(ECHO_CHEST_BOAT)
                                                                        .planks(BlockInit.ECHO_PLANKS.asItem())
                                                                        .build());




    public static TerraformBoatType register_boatType(RegistryKey<TerraformBoatType> key, TerraformBoatType type) {
        return Registry.register(TerraformBoatTypeRegistry.INSTANCE, key, type);
    }

    public static <T extends Item> T register_item(String name, T item) {
        return Registry.register(Registries.ITEM, FieldsFables.id(name), item);
    }













    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, FieldsFables.id(name), block);
    }

    private static <T extends Block> T registerWithoutItem(String name, T block) {
        return Registry.register(Registries.BLOCK, FieldsFables.id(name), block);
    }

    public static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        register_item(name, new BlockItem(registered, settings));
        return registered;
    }

    public static <T extends Block> T registerWithItem(String name, T block) {
        return registerWithItem(name, block, new Item.Settings());
    }

    public static void load_generic()
    {
        StrippableBlockRegistry.register(ECHO_LOG, STRIPPED_ECHO_LOG);
        StrippableBlockRegistry.register(ECHO_WOOD, STRIPPED_ECHO_WOOD);

        FlammableBlockRegistry.getDefaultInstance().add(ECHO_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ECHO_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ECHO_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ECHO_FENCE_GATE, 5, 20);

        Wisteria_Tree.loadGeneric();
    }

    public static void load_client_side()
    {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), POTTED_ECHO_SAPLING, ECHO_DOOR, ECHO_SAPLING, ECHO_LEAVES, ECHO_TRAPDOOR);
        TerraformBoatClientHelper.registerModelLayers(ECHO_BOAT_ID, false);

        Wisteria_Tree.loadClient();
    }

    public static void load_data_generator()
    {

    }
}
