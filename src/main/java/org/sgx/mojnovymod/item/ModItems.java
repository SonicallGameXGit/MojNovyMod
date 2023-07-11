package org.sgx.mojnovymod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.block.ModBlocks;
import org.sgx.mojnovymod.item.custom.CupItem;
import org.sgx.mojnovymod.item.custom.FilledCupItem;
import org.sgx.mojnovymod.item.custom.MagicStickItem;
import org.sgx.mojnovymod.item.material.armor.NewArmorMaterial;
import org.sgx.mojnovymod.sound.ModSounds;

public class ModItems {
    public static final Item NEW_ITEM = registerItem("new_item", new Item(new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item ANOTHER_ITEM = registerItem("another_item", new Item(new Item.Settings()), ModItemGroups.ANOTHER_GROUP);
    public static final Item MAGIC_STICK = registerItem("magic_stick", new MagicStickItem(new Item.Settings().maxDamage(64)), ModItemGroups.NEW_GROUP);

    public static final Item NEW_HELMET = registerItem("new_helmet", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.FEET, new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item NEW_CHESTPLATE = registerItem("new_chestplate", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.LEGS, new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item NEW_LEGGINGS = registerItem("new_leggings", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.HEAD, new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item NEW_BOOTS = registerItem("new_boots", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.CHEST, new Item.Settings()), ModItemGroups.NEW_GROUP);

    public static final Item TEA_SEEDS = registerItem("tea_seeds", new AliasedBlockItem(ModBlocks.TEA_CROP, new Item.Settings()), ModItemGroups.FOOD_GROUP);
    public static final Item TEA = registerItem("tea", new Item(new Item.Settings().food(new FoodComponent.Builder().hunger(5).saturationModifier(4.0f).build())), ModItemGroups.FOOD_GROUP);

    public static final Item RAW_CUP = registerItem("raw_cup", new Item(new Item.Settings().maxCount(1)), ModItemGroups.FOOD_GROUP);
    public static final Item CUP = registerItem("cup", new CupItem(new Item.Settings().maxCount(1)), ModItemGroups.FOOD_GROUP);
    public static final Item CUP_WITH_WATER = registerItem("cup_of_water", new FilledCupItem(FilledCupItem.Type.WATER, new Item.Settings().food(new FoodComponent.Builder().snack().build()).maxCount(1)), ModItemGroups.FOOD_GROUP);
    public static final Item CUP_WITH_HOT_WATER = registerItem("cup_of_hot_water", new FilledCupItem(FilledCupItem.Type.HOT_WATER, new Item.Settings().food(new FoodComponent.Builder().snack().saturationModifier(1.3f).build()).maxCount(1)), ModItemGroups.FOOD_GROUP);
    public static final Item CUP_WITH_TEA = registerItem("cup_of_tea", new FilledCupItem(FilledCupItem.Type.TEA, new Item.Settings().food(new FoodComponent.Builder().saturationModifier(2.5f).hunger(1).build()).maxCount(1)), ModItemGroups.FOOD_GROUP);

    public static final Item OMIN = registerItem("omin", new DiscFragmentItem(new Item.Settings()), ModItemGroups.MUSIC_GROUP);
    public static final Item OMINAN_MUSIC_DISC = registerItem("ominan_music_disc", new MusicDiscItem(7, ModSounds.OMINAN_MUSIC_DISC, new Item.Settings().maxCount(1), 184), ModItemGroups.MUSIC_GROUP);

    public static Item registerItem(String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(MojNovyMod.MOD_ID, id), item);
    }
    public static Item registerItem(String id, Item item, ItemGroup group) {
        Item registeredItem = registerItem(id, item);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(registeredItem));

        return registeredItem;
    }
    public static void register() {
        MojNovyMod.LOGGER.info("Registering items for: " + MojNovyMod.MOD_ID);
    }
}