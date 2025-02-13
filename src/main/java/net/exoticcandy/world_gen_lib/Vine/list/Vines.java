package net.exoticcandy.world_gen_lib.Vine.list;

import net.exoticcandy.world_gen_lib.Vine.EC_Vine;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class Vines
{
    public static List<EC_Vine> vines = new ArrayList<>();

    public static void  register_new_vine(String MOD_ID, String vine_ID, String VineName, Direction direction, boolean flammable, boolean isSideVine, MapColor VineColor, BlockSoundGroup SoundGroup, int light_level)
    {
        vines.add(new EC_Vine(MOD_ID, vine_ID, VineName, direction, flammable, isSideVine, VineColor, SoundGroup, light_level));
    }
}
