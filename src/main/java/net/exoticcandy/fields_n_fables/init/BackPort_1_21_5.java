package net.exoticcandy.fields_n_fables.init;

import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.world_gen_lib.FlowerPetals.list.Petals;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.exoticcandy.world_gen_lib.Vine.list.Vines;
import net.minecraft.block.MapColor;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class BackPort_1_21_5
{
    public static void load_generic()
    {
        Trees.register_new_tree("minecraft", "pale_oak", "Pale Oak", false, true,
                new SaplingGenerator("pale_oak", Optional.of(EC_Tree.ConfiguredFeatureRegisterKey("minecraft", "pale_oak_from_dark_oak")),
                        Optional.empty(),
                        Optional.empty()),
                MapColor.TERRACOTTA_LIGHT_GRAY, MapColor.LIGHT_GRAY, MapColor.LIGHT_GRAY);
        Vines.register_new_vine("minecraft", "pale_hanging_moss", "Pale Hanging Moss", Direction.DOWN, true, false, MapColor.LIGHT_GRAY, BlockSoundGroup.MOSS_BLOCK, 0);
        Petals.register_new_petal("minecraft", "wildflowers", "Wild Flowers", true, false, Items.YELLOW_DYE, MapColor.YELLOW, BlockSoundGroup.PINK_PETALS, 0);

        /*
        Fix ID: pale_hanging_moss_tip
        make the segment count for addition of leaf litter
         */
    }

    public static void load_client_side()
    {

    }
}
