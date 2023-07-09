package org.sgx.mojnovymod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.sgx.mojnovymod.item.ModItems;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MagicBlock extends Block {
    public static final BooleanProperty GLOW = BooleanProperty.of("glow");
    public static final BooleanProperty TYPE = BooleanProperty.of("type");

    public MagicBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(GLOW);
        builder.add(TYPE);
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

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(hand == Hand.MAIN_HAND) {
            if(player.getMainHandStack().isOf(ModItems.NEW_BOOTS)) {
                world.setBlockState(pos, state.cycle(TYPE));
                world.playSoundAtBlockCenter(pos, SoundEvents.ENTITY_VEX_DEATH, SoundCategory.BLOCKS, 1.0f, new Random().nextFloat(0.5f, 1.5f), true);
            } else world.setBlockState(pos, state.cycle(GLOW));
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }
}