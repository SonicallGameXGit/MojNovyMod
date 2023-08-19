package org.sgx.mojnovymod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.nbt.IPlayerEntityExt;
import org.sgx.mojnovymod.network.packet.ClientPacket;
import org.sgx.mojnovymod.network.packet.ServerPacket;
import org.sgx.mojnovymod.util.ModStats;

import java.util.Random;

public class ModNetwork {
    public static final ClientPacket MAGIC_KEY_PACKET = registerPacket("magic_key_packet", (server, player, handler, buf, responseSender) -> {
        BlazeEntity entity = EntityType.BLAZE.spawn(player.getWorld(), player.getBlockPos(), SpawnReason.TRIGGERED);
        if(entity != null)
            entity.playSound(SoundEvents.ENTITY_ALLAY_AMBIENT_WITH_ITEM, 1.0f, new Random().nextFloat(0.9f, 1.1f));
    });
    public static final ServerPacket DONT_BREAK_ME_BLOCK_BROKEN_PACKET = registerPacket("dont_break_me_block_broken_packet", (client, handler, buf, responseSender) -> {
        client.getWindow().close();
        System.exit(-1);
    });
    public static final ServerPacket STATS_CLIENT_UPDATE_PACKET = registerPacket("stats_client_update_packet", (client, handler, buf, responseSender) -> {
        IPlayerEntityExt playerExt = (IPlayerEntityExt) client.player;
        if(playerExt != null) playerExt.mojNovyMod$setStats(buf.readString(), buf.readInt());
    });
    public static final ClientPacket STATS_CLIENT_ASK_UPDATE_PACKET = registerPacket("stats_client_ask_update_packet", (server, player, handler, buf, responseSender) -> {
        String statsToUpdate = buf.readString();

        PacketByteBuf data = PacketByteBufs.create();
        data.writeString(statsToUpdate);
        data.writeInt(((IPlayerEntityExt) player).mojNovyMod$getStats(statsToUpdate));

        ModNetwork.send(ModNetwork.STATS_CLIENT_UPDATE_PACKET, player, data);
    });
    public static final ClientPacket OMIN_RADIATE_NAUSEA_PACKET = registerPacket("omin_radiate_nausea_packet", (server, player, handler, buf, responseSender) -> player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 2400)));
    public static final ClientPacket OMIN_RADIATE_DECREASE_PACKET = registerPacket("omin_radiate_decrease_packet", (server, player, handler, buf, responseSender) -> {
        IPlayerEntityExt playerExt = (IPlayerEntityExt) player;
        playerExt.mojNovyMod$setStats(ModStats.OMIN_RADIATE, playerExt.mojNovyMod$getStats(ModStats.OMIN_RADIATE) - 1);

        PacketByteBuf data = PacketByteBufs.create();
        data.writeString(ModStats.OMIN_RADIATE);
        data.writeInt(((IPlayerEntityExt) player).mojNovyMod$getStats(ModStats.OMIN_RADIATE));

        ModNetwork.send(ModNetwork.STATS_CLIENT_UPDATE_PACKET, player, data);
    });

    private static ClientPacket registerPacket(String id, ServerPlayNetworking.PlayChannelHandler handler) {
        ClientPacket packet = new ClientPacket(id, handler);
        ServerPlayNetworking.registerGlobalReceiver(packet.ID, handler);

        return packet;
    }
    private static ServerPacket registerPacket(String id, ClientPlayNetworking.PlayChannelHandler handler)  {
        ServerPacket packet = new ServerPacket(id, handler);
        ClientPlayNetworking.registerGlobalReceiver(packet.ID, handler);

        return packet;
    }

    public static void send(ClientPacket packet) {
        ClientPlayNetworking.send(packet.ID, PacketByteBufs.create());
    }
    public static void send(ClientPacket packet, PacketByteBuf data) {
        ClientPlayNetworking.send(packet.ID, data);
    }
    public static void send(ServerPacket packet, ServerPlayerEntity receiver) {
        ServerPlayNetworking.send(receiver, packet.ID, PacketByteBufs.create());
    }
    public static void send(ServerPacket packet, ServerPlayerEntity receiver, PacketByteBuf data) {
        ServerPlayNetworking.send(receiver, packet.ID, data);
    }
    public static void register() {
        MojNovyMod.LOGGER.info("Registering network for: " + MojNovyMod.MOD_ID);
    }
}