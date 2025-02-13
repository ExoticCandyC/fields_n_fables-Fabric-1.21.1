package net.exoticcandy.world_gen_lib.FlowerPetals.list;

import net.exoticcandy.world_gen_lib.FlowerPetals.EC_Petal;
import net.minecraft.block.MapColor;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;

import java.util.ArrayList;
import java.util.List;

public class Petals
{
    public static List<EC_Petal> petals = new ArrayList<>();

    public static void register_new_petal(String MOD_ID, String petal_ID, String PetalName, boolean flammable, boolean placeOnSolid, boolean useSegments, Item ConversionDye, MapColor PetalColor, BlockSoundGroup SoundGroup, int light_level)
    {
        petals.add(new EC_Petal(MOD_ID, petal_ID, PetalName, flammable, placeOnSolid, useSegments, ConversionDye, PetalColor, SoundGroup, light_level));
    }
}
