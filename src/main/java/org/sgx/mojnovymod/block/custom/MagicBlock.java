package org.sgx.mojnovymod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MagicBlock extends Block {
    public MagicBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        entity.setCustomName(Text.literal("Dinnerbone"));
        if(entity instanceof LivingEntity livingEntity)
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 1));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        player.damage(DamageSource.MAGIC, 4);
        for(int i = 0; i < new Random().nextInt(1, 100); i++) {
            byte[] bytes = new byte[new Random().nextInt(1, 10)];
            new Random().nextBytes(bytes);

            player.sendMessage(Text.literal(new String(bytes, StandardCharsets.UTF_8)));
        }

        super.onBreak(world, pos, state, player);
    }
}