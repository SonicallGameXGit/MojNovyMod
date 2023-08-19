package org.sgx.mojnovymod.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.PacketByteBuf;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.client.MojNovyModClient;
import org.sgx.mojnovymod.nbt.IPlayerEntityExt;
import org.sgx.mojnovymod.network.ModNetwork;

public class ModTickController {
    public static final ClientTickEvents.EndTick OMIN_RADIATE_TICKER = registerTicker((ClientTickEvents.EndTick) client -> {
        ClientPlayerEntity player = client.player;
        if(player != null) {
            IPlayerEntityExt playerExt = (IPlayerEntityExt) player;

            if(Math.random() * 100.0 <= 0.3) ModNetwork.send(ModNetwork.OMIN_RADIATE_DECREASE_PACKET);
            if(!player.hasStatusEffect(StatusEffects.NAUSEA) && playerExt.mojNovyMod$getStats(ModStats.OMIN_RADIATE) >= ModStats.OMIN_RADIATE_MAX)
                ModNetwork.send(ModNetwork.OMIN_RADIATE_NAUSEA_PACKET);

            if(player.clientWorld != MojNovyModClient.lastClientWorld) {
                PacketByteBuf data = PacketByteBufs.create();
                data.writeString(ModStats.OMIN_RADIATE);

                ModNetwork.send(ModNetwork.STATS_CLIENT_ASK_UPDATE_PACKET, data);

                MojNovyModClient.lastClientWorld = player.clientWorld;
            }
        }
    });

    private static ServerTickEvents.EndTick registerTicker(ServerTickEvents.EndTick ticker) {
        ServerTickEvents.END_SERVER_TICK.register(ticker);
        return ticker;
    }
    private static ServerTickEvents.EndWorldTick registerTicker(ServerTickEvents.EndWorldTick ticker) {
        ServerTickEvents.END_WORLD_TICK.register(ticker);
        return ticker;
    }
    private static ClientTickEvents.EndTick registerTicker(ClientTickEvents.EndTick ticker) {
        ClientTickEvents.END_CLIENT_TICK.register(ticker);
        return ticker;
    }
    private static ClientTickEvents.EndWorldTick registerTicker(ClientTickEvents.EndWorldTick ticker) {
        ClientTickEvents.END_WORLD_TICK.register(ticker);
        return ticker;
    }

    public static void register() {
        MojNovyMod.LOGGER.info("Registering tick controller for: " + MojNovyMod.MOD_ID);
    }
}