package org.sgx.mojnovymod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.sgx.mojnovymod.item.ModItems;

import java.util.Random;

public class OminanOreBlock extends Block {
    public OminanOreBlock(Settings settings) {
        super(settings);
    }

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getStackInHand(hand).isOf(Items.GOLDEN_SWORD)) {
            world.breakBlock(pos, false);
            dropStack(world, pos, new ItemStack(ModItems.OMIN));
        } else world.createExplosion(player, DamageSource.FREEZE, new ExplosionBehavior(), pos.getX(), pos.getY(), pos.getZ(), new Random().nextFloat(3.0f, 5.5f), false, World.ExplosionSourceType.BLOCK);

        return ActionResult.PASS;
    }
}