package org.sgx.mojnovymod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup NEW_GROUP = FabricItemGroup.builder(new Identifier(MojNovyMod.MOD_ID, "new_group"))
            .icon(() -> new ItemStack(ModItems.NEW_ITEM))
            .build();
    public static final ItemGroup ANOTHER_GROUP = FabricItemGroup.builder(new Identifier(MojNovyMod.MOD_ID, "another_group"))
            .icon(() -> new ItemStack(ModItems.ANOTHER_ITEM))
            .build();
    public static final ItemGroup FOOD_GROUP = FabricItemGroup.builder(new Identifier(MojNovyMod.MOD_ID, "food_group"))
            .icon(() -> new ItemStack(ModItems.TEA))
            .build();
    public static final ItemGroup ORE_GROUP = FabricItemGroup.builder(new Identifier(MojNovyMod.MOD_ID, "ore_group"))
            .icon(() -> new ItemStack(ModBlocks.DEEPSLATE_OMINAN_ORE))
            .build();
    public static final ItemGroup MUSIC_GROUP = FabricItemGroup.builder(new Identifier(MojNovyMod.MOD_ID, "music_group"))
            .icon(() -> new ItemStack(ModItems.OMINAN_MUSIC_DISC))
            .build();
}