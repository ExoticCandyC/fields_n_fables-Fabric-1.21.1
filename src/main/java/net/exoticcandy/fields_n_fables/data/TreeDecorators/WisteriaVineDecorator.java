package net.exoticcandy.fields_n_fables.data.TreeDecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class WisteriaVineDecorator extends TreeDecorator {
    public static final MapCodec<WisteriaVineDecorator> codec = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(WisteriaVineDecorator::new, (treeDecorator) -> treeDecorator.probability);
    private final float probability;

    public static final TreeDecoratorType<WisteriaVineDecorator> TYPE;

    public static void load()
    {

    }

    static {
        // Create the type using our codec
        TYPE = new TreeDecoratorType<>(codec);
        // Register it in the built-in registry using your mod id and a unique name.
        Registry.register(Registries.TREE_DECORATOR_TYPE, FieldsFables.id("wisteria_vine_decorator"), TYPE);
    }

    protected TreeDecoratorType<?> getType() { return TYPE; /*TreeDecoratorType.LEAVE_VINE;*/ }

    public WisteriaVineDecorator(float probability) {
        this.probability = probability;
    }

    public void generate(TreeDecorator.Generator generator) {
        Random random = generator.getRandom();
        generator.getLeavesPositions().forEach((pos) -> {
            if (random.nextFloat() < this.probability) {
                BlockPos blockPos = pos.west();
                if (generator.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.EAST, generator);
                }
            }

            if (random.nextFloat() < this.probability) {
                BlockPos blockPos = pos.east();
                if (generator.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.WEST, generator);
                }
            }

            if (random.nextFloat() < this.probability) {
                BlockPos blockPos = pos.north();
                if (generator.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.SOUTH, generator);
                }
            }

            if (random.nextFloat() < this.probability) {
                BlockPos blockPos = pos.south();
                if (generator.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.NORTH, generator);
                }
            }

        });
    }

    public static void ec_replaceWithVine(BlockPos pos, BooleanProperty faceProperty, TreeDecorator.Generator generator)
    {
        generator.replace(pos, (BlockState) Vines.vines.getFirst().VINE_STEM.getDefaultState().with(faceProperty, true));
    }

    private static void placeVines(BlockPos pos, BooleanProperty faceProperty, TreeDecorator.Generator generator)
    {
        ec_replaceWithVine(pos, faceProperty, generator);
        int i = 4;

        for(BlockPos var4 = pos.down(); generator.isAir(var4) && i > 0; --i)
        {
            ec_replaceWithVine(var4, faceProperty, generator);
            var4 = var4.down();
        }

    }
}
