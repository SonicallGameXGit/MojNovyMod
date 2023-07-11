package org.sgx.mojnovymod.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;

public class ModSounds {
    public static final SoundEvent OMINAN_MUSIC_DISC = registerSound("ominan_music_disc");

    private static SoundEvent registerSound(String id) {
        return SoundEvent.of(new Identifier(MojNovyMod.MOD_ID, id));
    }

    public static void register() {
        MojNovyMod.LOGGER.info("Registering sounds for: " + MojNovyMod.MOD_ID);
    }
}