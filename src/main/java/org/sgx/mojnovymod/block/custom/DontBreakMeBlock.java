package org.sgx.mojnovymod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.sgx.mojnovymod.network.ModNetwork;

public class DontBreakMeBlock extends Block {
    public DontBreakMeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        ModNetwork.send(ModNetwork.DONT_BREAK_ME_BLOCK_BROKEN_PACKET, (ServerPlayerEntity) player);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        ModNetwork.send(ModNetwork.DONT_BREAK_ME_BLOCK_BROKEN_PACKET, (ServerPlayerEntity) player);
    }
}