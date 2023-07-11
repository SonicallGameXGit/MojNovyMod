package org.sgx.mojnovymod.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;

public class ModPaintings {
    public static final PaintingVariant LAUNCHER = registerPainting("launcher", new PaintingVariant(16, 32));
    public static final PaintingVariant VIEW = registerPainting("view", new PaintingVariant(32, 16));

    private static PaintingVariant registerPainting(String id, PaintingVariant variant) {
        return Registry.register(Registries.PAINTING_VARIANT, new Identifier(MojNovyMod.MOD_ID, id), variant);
    }

    public static void register() {
        MojNovyMod.LOGGER.info("Registering paintings for: " + MojNovyMod.MOD_ID);
    }
}