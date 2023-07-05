package org.sgx.mojnovymod.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.sgx.mojnovymod.MojNovyMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MojNovyMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void aVoid(CallbackInfo info) {
        MojNovyMod.LOGGER.info("Nafiga ono nado, my s vamy i uznayem :)");
    }
}