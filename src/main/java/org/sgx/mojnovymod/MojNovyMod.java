package org.sgx.mojnovymod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.network.PacketByteBuf;
import org.sgx.mojnovymod.block.ModBlocks;
import org.sgx.mojnovymod.item.ModItems;
import org.sgx.mojnovymod.network.ModNetwork;
import org.sgx.mojnovymod.painting.ModPaintings;
import org.sgx.mojnovymod.sound.ModSounds;
import org.sgx.mojnovymod.util.ModKeyBindings;
import org.sgx.mojnovymod.util.ModLootTables;
import org.sgx.mojnovymod.util.ModStats;
import org.sgx.mojnovymod.util.ModTickController;
import org.sgx.mojnovymod.villager.ModVillagers;
import org.sgx.mojnovymod.worldgen.ModFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MojNovyMod implements ModInitializer {
    public static final String MOD_ID = "mojnovymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();
        ModBlocks.register();
        ModVillagers.register();
        ModPaintings.register();
        ModFeatures.register();
        ModSounds.register();
        ModLootTables.register();
        ModKeyBindings.register();
        ModNetwork.register();
        ModTickController.register();

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            PacketByteBuf data = PacketByteBufs.create();
            data.writeString(ModStats.OMIN_RADIATE);

            ModNetwork.send(ModNetwork.STATS_CLIENT_ASK_UPDATE_PACKET, data);
        });
    }
}