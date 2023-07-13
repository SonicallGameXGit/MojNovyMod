package org.sgx.mojnovymod.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.network.ModNetwork;

public class ModKeyBindings {
    public static final KeyBinding MAGIC_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + MojNovyMod.MOD_ID + ".magic_keybinding",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_M,
            "key.category." + MojNovyMod.MOD_ID + ".magic_category"
    ));

    private static void registerKeyBindings() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(MAGIC_KEYBINDING.wasPressed() && client.player != null) {
                client.player.sendMessage(Text.literal("Тыкнул значит в магию"));
                ModNetwork.send(ModNetwork.MAGIC_KEY_PACKET);
            }
        });
    }

    public static void register() {
        registerKeyBindings();
        MojNovyMod.LOGGER.info("Registering key bindings for: " + MojNovyMod.MOD_ID);
    }
}