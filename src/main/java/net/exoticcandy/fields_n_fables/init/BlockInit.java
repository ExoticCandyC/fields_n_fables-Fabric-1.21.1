package net.exoticcandy.fields_n_fables.init;

import net.exoticcandy.fields_n_fables.FieldsFables;
import net.exoticcandy.world_gen_lib.Tree.EC_Tree;
import net.exoticcandy.world_gen_lib.Tree.list.Trees;
import net.minecraft.block.*;
import net.minecraft.block.MapColor;

import java.util.Optional;

public class BlockInit {
    // https://github.com/KyaniteMods/DeeperAndDarker/blob/fabric-1.21/src/main/java/com/kyanite/deeperdarker/content/DDBlocks.java

    public static void load_generic()
    {
        Trees.register_new_tree(FieldsFables.MOD_ID, "wisteria", "Wisteria",
                new SaplingGenerator("wisteria", Optional.empty(),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey("echo")),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey("echo_bees_005"))),
                MapColor.GRAY, MapColor.PURPLE, MapColor.PALE_PURPLE);
        Trees.register_new_tree(FieldsFables.MOD_ID, "echo"    , "Echo"    ,
                new SaplingGenerator("echo", Optional.empty(),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey("echo")),
                        Optional.of(EC_Tree.ConfiguredFeatureRegisterKey("echo_bees_005"))),
                MapColor.GRAY, MapColor.PURPLE, MapColor.PALE_PURPLE);

        for(EC_Tree tree : Trees.trees)
        {
            tree.loadGeneric();
        }
        //Wisteria_Tree.loadGeneric();
    }

    public static void load_client_side()
    {
        for(EC_Tree tree : Trees.trees)
        {
            tree.loadClient();
        }
        //Wisteria_Tree.loadClient();
    }

    public static void load_data_generator()
    {

    }
}
