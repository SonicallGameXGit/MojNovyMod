package org.sgx.mojnovymod.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.block.custom.MagicBlock;
import org.sgx.mojnovymod.item.ModItemGroups;
import org.sgx.mojnovymod.item.ModItems;

public class ModBlocks {
    public static final Block NEW_BLOCK = registerBlock("new_block", new Block(FabricBlockSettings.of(Material.METAL).strength(3.0f).requiresTool().luminance(6)), ModItemGroups.NEW_GROUP);
    public static final Block ANOTHER_BLOCK = registerBlock("another_block", new Block(FabricBlockSettings.of(Material.AMETHYST).strength(5.0f).requiresTool().luminance(9)), ModItemGroups.ANOTHER_GROUP);
    public static final Block MAGIC_BLOCK = registerBlock("magic_block", new MagicBlock(FabricBlockSettings.of(Material.AMETHYST).strength(5.0f).requiresTool().luminance(9)), ModItemGroups.ANOTHER_GROUP);

    private static Block registerBlock(String id, Block block) {
        ModItems.registerItem(id, new BlockItem(block, new Item.Settings()));
        return Registry.register(Registries.BLOCK, new Identifier(MojNovyMod.MOD_ID, id), block);
    }
    private static Block registerBlock(String id, Block block, ItemGroup group) {
        ModItems.registerItem(id, new BlockItem(block, new Item.Settings()), group);
        return Registry.register(Registries.BLOCK, new Identifier(MojNovyMod.MOD_ID, id), block);
    }
    public static void register() {
        MojNovyMod.LOGGER.info("Registering blocks for: " + MojNovyMod.MOD_ID);
    }
}