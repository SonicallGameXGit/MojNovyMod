package org.sgx.mojnovymod.network.packet;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;

public class ClientPacket {
    public final Identifier ID;
    public final ServerPlayNetworking.PlayChannelHandler HANDLER;

    public ClientPacket(String id, ServerPlayNetworking.PlayChannelHandler handler) {
        ID = new Identifier(MojNovyMod.MOD_ID, id);
        HANDLER = handler;
    }
}