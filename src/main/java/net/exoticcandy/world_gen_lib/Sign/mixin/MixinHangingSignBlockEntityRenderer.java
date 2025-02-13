package net.exoticcandy.world_gen_lib.Sign.mixin;

import net.exoticcandy.world_gen_lib.Sign._sign;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.block.entity.HangingSignBlockEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HangingSignBlockEntityRenderer.class)
@Environment(EnvType.CLIENT)
public abstract class MixinHangingSignBlockEntityRenderer extends MixinSignBlockEntityRenderer {
    @Inject(method = "getTextureId", at = @At("HEAD"), cancellable = true)
    private void exoticCandyWood$getHangingSignTextureId(CallbackInfoReturnable<SpriteIdentifier> cir) {
        if (this.ec_world_gen_lib$renderedBlockEntity != null) {
            if (this.ec_world_gen_lib$renderedBlockEntity.getCachedState().getBlock() instanceof _sign signBlock) {
                cir.setReturnValue(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, signBlock.getTexture()));
            }
        }
    }
}