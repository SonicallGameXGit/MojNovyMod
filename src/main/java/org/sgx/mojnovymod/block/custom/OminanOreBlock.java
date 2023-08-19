package org.sgx.mojnovymod.block.custom;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import org.sgx.mojnovymod.item.ModItems;
import org.sgx.mojnovymod.nbt.IPlayerEntityExt;
import org.sgx.mojnovymod.network.ModNetwork;
import org.sgx.mojnovymod.util.ModStats;

import java.util.Random;

public class OminanOreBlock extends Block {
    public OminanOreBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);

        IPlayerEntityExt playerExt = (IPlayerEntityExt) player;
        playerExt.mojNovyMod$setStats(ModStats.OMIN_RADIATE, playerExt.mojNovyMod$getStats(ModStats.OMIN_RADIATE) + 1);

        sendClientUpdatePacket(player);
    }

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient()) return ActionResult.PASS;
        if(player.getStackInHand(hand).isOf(Items.GOLDEN_SWORD)) {
            world.breakBlock(pos, false);
            dropStack(world, pos, new ItemStack(ModItems.OMIN));

            IPlayerEntityExt playerExt = (IPlayerEntityExt) player;
            playerExt.mojNovyMod$setStats(ModStats.OMIN_RADIATE, playerExt.mojNovyMod$getStats(ModStats.OMIN_RADIATE) + 3);

            sendClientUpdatePacket(player);
        } else {
            world.createExplosion(player, DamageSource.FREEZE, new ExplosionBehavior(), pos.getX(), pos.getY(), pos.getZ(), new Random().nextFloat(3.0f, 5.5f), false, World.ExplosionSourceType.BLOCK);

            IPlayerEntityExt playerExt = (IPlayerEntityExt) player;
            playerExt.mojNovyMod$setStats(ModStats.OMIN_RADIATE, playerExt.mojNovyMod$getStats(ModStats.OMIN_RADIATE) + 1);

            sendClientUpdatePacket(player);
        }

        return ActionResult.PASS;
    }

    private static void sendClientUpdatePacket(PlayerEntity player) {
        PacketByteBuf data = PacketByteBufs.create();
        data.writeString(ModStats.OMIN_RADIATE);
        data.writeInt(((IPlayerEntityExt) player).mojNovyMod$getStats(ModStats.OMIN_RADIATE));

        ModNetwork.send(ModNetwork.STATS_CLIENT_UPDATE_PACKET, (ServerPlayerEntity) player, data);
    }
}