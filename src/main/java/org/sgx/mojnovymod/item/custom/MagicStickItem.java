package org.sgx.mojnovymod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.List;

public class MagicStickItem extends Item {
    public MagicStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        if(player != null && context.getHand() == Hand.MAIN_HAND) {
            Vec3d blockPosition = context.getBlockPos().toCenterPos();
            Vector3f hitNormal = context.getSide().getUnitVector();
            blockPosition.add(hitNormal.x(), hitNormal.y(), hitNormal.z());

            player.setPosition(blockPosition);
            player.getItemCooldownManager().set(this, 20);
        }

        return ActionResult.PASS;
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        Text oldEntityName = entity.getCustomName();
        if(hand == Hand.MAIN_HAND && (oldEntityName == null || !oldEntityName.getString().equals("Dinnerbone"))) {
            entity.setCustomName(Text.literal("Dinnerbone"));

            Text entityName = entity.getCustomName();
            if(entityName != null && entityName.getString().equals("Dinnerbone"))
                stack.damage(1, user, player -> player.sendToolBreakStatus(hand));
        }

        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasControlDown())
            tooltip.add(Text.literal("ABOBA Control").formatted(Formatting.AQUA));
        else if(Screen.hasAltDown())
            tooltip.add(Text.literal("ABOBA Alt").formatted(Formatting.RED));
        else if(Screen.hasShiftDown())
            tooltip.add(Text.literal("ABOBA Shift").formatted(Formatting.BLUE));
        else tooltip.add(Text.literal("ABOBA Nothing").formatted(Formatting.GREEN));
    }
}