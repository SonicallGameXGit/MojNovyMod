package org.sgx.mojnovymod.item.material.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.sgx.mojnovymod.item.ModItems;

public class NewArmorMaterial implements ArmorMaterial {
    private static final int[] BASE_DURABILITY = new int[] { 13, 15, 16, 11 };
    private static final int[] PROTECTION_VALUES = new int[] { 2, 3, 4, 2 };

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()];
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_VALUES[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 3;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.NEW_ITEM);
    }

    @Override
    public String getName() {
        return "new_armor";
    }

    @Override
    public float getToughness() {
        return 2.0f;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.4f;
    }
}