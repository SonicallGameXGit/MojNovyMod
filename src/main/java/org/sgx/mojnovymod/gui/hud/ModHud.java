package org.sgx.mojnovymod.gui.hud;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.gui.hud.custom.StatsHudBar;
import org.sgx.mojnovymod.util.ModStats;

public class ModHud {
    public static final HudRenderCallback OMIN_RADIATE_BAR = registerHud(new StatsHudBar(
            ModStats.OMIN_RADIATE,
            "omin_radiate",
            "omin_radiate_full",
            0,
            false,
            true
    ));
    public static final HudRenderCallback OMIN_RADIATE_BAR2 = registerHud(new StatsHudBar(
            ModStats.OMIN_RADIATE,
            "omin_radiate_full",
            "omin_radiate",
            1,
            true,
            true
    ));

    private static HudRenderCallback registerHud(HudRenderCallback callback) {
        HudRenderCallback.EVENT.register(callback);
        return callback;
    }

    public static void register() {
        MojNovyMod.LOGGER.info("Registering hud for: " + MojNovyMod.MOD_ID);
    }
}