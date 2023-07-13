package org.sgx.mojnovymod.network.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;

public class ServerPacket {
    public final Identifier ID;
    public final ClientPlayNetworking.PlayChannelHandler HANDLER;

    public ServerPacket(String id, ClientPlayNetworking.PlayChannelHandler handler) {
        ID = new Identifier(MojNovyMod.MOD_ID, id);
        HANDLER = handler;
    }
}