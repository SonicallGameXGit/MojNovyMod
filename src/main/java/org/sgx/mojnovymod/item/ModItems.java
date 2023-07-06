package org.sgx.mojnovymod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.item.custom.MagicStickItem;
import org.sgx.mojnovymod.item.material.armor.NewArmorMaterial;

public class ModItems {
    public static final Item NEW_ITEM = registerItem("new_item", new Item(new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item ANOTHER_ITEM = registerItem("another_item", new Item(new Item.Settings()), ModItemGroups.ANOTHER_GROUP);
    public static final Item MAGIC_STICK = registerItem("magic_stick", new MagicStickItem(new Item.Settings().maxDamage(64)), ModItemGroups.NEW_GROUP);

    public static final Item NEW_HELMET = registerItem("new_helmet", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.FEET, new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item NEW_CHESTPLATE = registerItem("new_chestplate", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.LEGS, new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item NEW_LEGGINGS = registerItem("new_leggings", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.HEAD, new Item.Settings()), ModItemGroups.NEW_GROUP);
    public static final Item NEW_BOOTS = registerItem("new_boots", new ArmorItem(new NewArmorMaterial(), EquipmentSlot.CHEST, new Item.Settings()), ModItemGroups.NEW_GROUP);

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