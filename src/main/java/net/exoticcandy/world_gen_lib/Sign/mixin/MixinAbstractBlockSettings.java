package net.exoticcandy.world_gen_lib.Sign.mixin;

import net.exoticcandy.world_gen_lib.Sign._blockSettingsLock;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.Settings.class)
public class MixinAbstractBlockSettings implements _blockSettingsLock {
    @Unique
    private boolean ec_world_gen_lib$locked = false;

    @Inject(method = "sounds", at = @At("HEAD"), cancellable = true)
    private void exoticCandyWood$preventSoundsOverride(CallbackInfoReturnable<AbstractBlock.Settings> cir) {
        if (this.ec_world_gen_lib$locked) {
            //noinspection ConstantConditions
            cir.setReturnValue((AbstractBlock.Settings) (Object) this);
            this.ec_world_gen_lib$locked = false;
        }
    }

    @Override
    public void lock() {
        this.ec_world_gen_lib$locked = true;
    }
}