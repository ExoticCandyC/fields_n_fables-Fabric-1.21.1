package net.exoticcandy.fields_n_fables.init;

import com.mojang.serialization.MapCodec;
import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.world_gen_lib.FlowerPetals.EC_Petal;
import net.exoticcandy.world_gen_lib.FlowerPetals.list.Petals;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.exoticcandy.world_gen_lib.Vine.EC_Vine;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
import net.minecraft.block.*;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class BlockInit {
    // https://github.com/KyaniteMods/DeeperAndDarker/blob/fabric-1.21/src/main/java/com/kyanite/deeperdarker/content/DDBlocks.java


    private static <T extends Block> T register(String name, T block)
    {
        return Registry.register(Registries.BLOCK, Identifier.of(FieldsFables.MOD_ID, name), block);
    }

    private static <T extends Item> T register_item(String name, T item)
    {
        return Registry.register(Registries.ITEM, Identifier.of(FieldsFables.MOD_ID, name), item);
    }

    private static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings)
    {
        T registered = register(name, block);
        register_item(name, new BlockItem(registered, settings));
        return registered;
    }

    private static <T extends Block> T registerWithItem(String name, T block)
    {
        return registerWithItem(name, block, new Item.Settings());
    }

    private static <T extends Block> T registerMineCraft(String name, T block)
    {
        return Registry.register(Registries.BLOCK, Identifier.of("minecraft", name), block);
    }

    private static <T extends Item> T register_itemMineCraft(String name, T item)
    {
        return Registry.register(Registries.ITEM, Identifier.of("minecraft", name), item);
    }

    private static <T extends Block> T registerWithItemMineCraft(String name, T block, Item.Settings settings)
    {
        T registered = registerMineCraft(name, block);
        register_itemMineCraft(name, new BlockItem(registered, settings));
        return registered;
    }

    private static <T extends Block> T registerWithItemMineCraft(String name, T block)
    {
        return registerWithItemMineCraft(name, block, new Item.Settings());
    }

    /*
    public static final Block WILDFLOWER  = registerWithItemMineCraft("wildflowers", new FlowerbedBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_PURPLE).noCollision().sounds(BlockSoundGroup.PINK_PETALS).pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block LEAF_LITTER  = registerWithItemMineCraft("leaf_litter", new FlowerbedBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).noCollision().sounds(BlockSoundGroup.AZALEA_LEAVES).pistonBehavior(PistonBehavior.DESTROY)) { @Override protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) { BlockPos blockPos = pos.down(); return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, Direction.UP); } } );
    */

    public static class EC_SimpleParticleType extends SimpleParticleType {
        private final MapCodec<net.minecraft.particle.SimpleParticleType> codec = MapCodec.unit(this::getType);
        private final PacketCodec<RegistryByteBuf, net.minecraft.particle.SimpleParticleType> packetCodec = PacketCodec.unit(this);

        public EC_SimpleParticleType(boolean alwaysShow) {
            super(alwaysShow);
        }

        public net.minecraft.particle.SimpleParticleType getType() {
            return this;
        }

        public MapCodec<net.minecraft.particle.SimpleParticleType> getCodec() {
            return this.codec;
        }

        public PacketCodec<RegistryByteBuf, net.minecraft.particle.SimpleParticleType> getPacketCodec() {
            return this.packetCodec;
        }
    }

    private static EC_SimpleParticleType register_particle(String name, boolean alwaysShow)
    {
        return (EC_SimpleParticleType)Registry.register(Registries.PARTICLE_TYPE, FieldsFables.id(name), new EC_SimpleParticleType(alwaysShow));
    }

    public static final Block MOSS_BLOCK = registerWithItem("moss_block", new MossBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK).pistonBehavior(PistonBehavior.DESTROY)));

    public static void load_generic()
    {
        //Registry.register(Registries.PARTICLE_TYPE, FieldsFables.id("wisteria_leaves"), WISTERIA_LEAVES);
        /* Wisteria Tree and its decorations */
        Trees.register_new_tree(FieldsFables.MOD_ID, "wisteria", "Wisteria", true, true,
                new SaplingGenerator("wisteria",
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey(FieldsFables.MOD_ID, "mega_wisteria")),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey(FieldsFables.MOD_ID,"wisteria")),
                        Optional.empty()),
                MapColor.GRAY, MapColor.PURPLE, MapColor.PALE_PURPLE);
        Vines.register_new_vine(FieldsFables.MOD_ID, "wisteria_vines", "Wisteria Flowers", Direction.DOWN, true, true, MapColor.PURPLE, BlockSoundGroup.CHERRY_LEAVES, 7);
        Petals.register_new_petal(FieldsFables.MOD_ID, "wisteria_petals", "Wisteria Petals", true, false, Items.PURPLE_DYE, MapColor.PURPLE, BlockSoundGroup.PINK_PETALS, 0);

        Trees.register_new_tree(FieldsFables.MOD_ID, "echo"    , "Echo"    , false, false,
                new SaplingGenerator("echo", Optional.empty(),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey(FieldsFables.MOD_ID,"echo")),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey(FieldsFables.MOD_ID,"echo_bees_005"))),
                MapColor.GRAY, MapColor.PURPLE, MapColor.PALE_PURPLE);



        for(EC_Tree  tree  :  Trees.trees )  tree.loadGeneric();
        for(EC_Vine  vines :  Vines.vines ) vines.loadGeneric();
        for(EC_Petal petal : Petals.petals) petal.loadGeneric();
    }

    public static void load_client_side()
    {
        //BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), WILDFLOWER, LEAF_LITTER);

        for(EC_Tree tree   :  Trees.trees )  tree.loadClient();
        for(EC_Vine vines  :  Vines.vines ) vines.loadClient();
        for(EC_Petal petal : Petals.petals) petal.loadClient();


        // For this example, we will use the end rod particle behaviour.
        //ParticleFactoryRegistry.getInstance().register(WISTERIA_LEAVES, net.minecraft.client.particle.EndRodParticle.Factory::new);
        //ParticleFactoryRegistry.getInstance().register(WISTERIA_LEAVES, WisteriaLeavesParticle.Factory::new);

    }

    public static void load_data_generator()
    {

    }
}
