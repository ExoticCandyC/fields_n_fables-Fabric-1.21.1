package net.exoticcandy.world_gen_lib.Sign;

import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class EC_WallSignBlock extends WallSignBlock implements _sign
{
    private final Identifier texture;

    public EC_WallSignBlock(Identifier texture, WoodType woodType, Settings settings)
    {
        super(woodType, _blockSettingsLock.lock(settings));
        this.texture = texture;
    }

    public EC_WallSignBlock(Identifier texture, Settings settings)
    {
        this(texture, WoodType.OAK, settings);
    }

    @Override
    public Identifier getTexture()
    {
        return texture;
    }
}