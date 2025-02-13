package net.exoticcandy.world_gen_lib.Sign;

import net.minecraft.block.HangingSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;

public class EC_HangingSignBlock extends HangingSignBlock implements _hanging_sign {
    private final Identifier texture;
    private final Identifier guiTexture;

    public EC_HangingSignBlock(Identifier texture, Identifier guiTexture, WoodType woodType, Settings settings) {
        super(woodType, _blockSettingsLock.lock(settings));
        this.texture = texture;
        this.guiTexture = guiTexture;
    }

    public EC_HangingSignBlock(Identifier texture, Identifier guiTexture, Settings settings) {
        this(texture, guiTexture, WoodType.OAK, settings);
    }

    @Override
    public Identifier getTexture() {
        return texture;
    }

    @Override
    public Identifier getGuiTexture() {
        return guiTexture;
    }
}