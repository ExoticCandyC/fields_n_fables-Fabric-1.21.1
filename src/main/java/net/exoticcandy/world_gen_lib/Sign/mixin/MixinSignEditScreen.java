package net.exoticcandy.world_gen_lib.Sign.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.exoticcandy.world_gen_lib.Sign._sign;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.WoodType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SignEditScreen.class)
@Environment(EnvType.CLIENT)
public class MixinSignEditScreen {
    @WrapOperation(
            method = "renderSignBackground",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TexturedRenderLayers;getSignTextureId(Lnet/minecraft/block/WoodType;)Lnet/minecraft/client/util/SpriteIdentifier;")
    )
    @SuppressWarnings("unused")
    private SpriteIdentifier exoticCandyWood$editSignTextureId(WoodType type, Operation<SpriteIdentifier> original, DrawContext drawContext, BlockState state) {
        if (state.getBlock() instanceof _sign signBlock) {
            return new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, signBlock.getTexture());
        }

        return original.call(type);
    }
}