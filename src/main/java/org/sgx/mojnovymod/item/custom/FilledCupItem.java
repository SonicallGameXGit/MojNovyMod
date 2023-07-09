package org.sgx.mojnovymod.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoneyBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.sgx.mojnovymod.item.ModItems;

public class FilledCupItem extends HoneyBottleItem {
    private final Type TYPE;

    public FilledCupItem(Type type, Settings settings) {
        super(settings);
        TYPE = type;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY);

        if(hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            if(world.canPlayerModifyAt(user, blockPos) && world.getFluidState(blockPos).isIn(FluidTags.WATER)) {
                world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                world.emitGameEvent(user, GameEvent.FLUID_PLACE, blockPos);
                if(!user.getAbilities().creativeMode)
                    user.setStackInHand(hand, new ItemStack(ModItems.CUP));

                return TypedActionResult.success(itemStack);
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if(user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        if(!world.isClient) {
            switch(TYPE) {
                case WATER -> user.removeStatusEffect(StatusEffects.POISON);
                case HOT_WATER -> user.damage(DamageSource.LAVA, 6.0f);
                case TEA -> {
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 140, 2));
                    user.removeStatusEffect(StatusEffects.POISON);
                }
            }
        }
        if(stack.isEmpty() && !(user instanceof PlayerEntity) || !((PlayerEntity) user).getAbilities().creativeMode)
            return new ItemStack(ModItems.CUP);

        return stack;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }
    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public enum Type {
        WATER, HOT_WATER, TEA
    }
}