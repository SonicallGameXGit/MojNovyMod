package org.sgx.mojnovymod.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.WeatherCheckLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.item.ModItems;

public class ModLootTables {
    public static final Identifier GRASS_ID = new Identifier("minecraft", "blocks/grass");
    public static final Identifier STRONGHOLD_LIBRARY_ID = new Identifier("minecraft", "chests/stronghold_library");

    private static void registerLootTables() {
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(id.equals(GRASS_ID)) {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0f, 3.0f))
                                .conditionally(WeatherCheckLootCondition.create().raining(true))
                                .conditionally(RandomChanceLootCondition.builder(0.1f))
                                .with(ItemEntry.builder(ModItems.TEA_SEEDS))
                );
            }
            if(id.equals(STRONGHOLD_LIBRARY_ID)) {
                tableBuilder.pool(
                        LootPool.builder()
                                .rolls(UniformLootNumberProvider.create(1.0f, 16.0f))
                                .conditionally(RandomChanceLootCondition.builder(0.75f))
                                .with(ItemEntry.builder(ModItems.OMINAN_MUSIC_DISC))
                );
            }
        }));
    }

    public static void register() {
        registerLootTables();
        MojNovyMod.LOGGER.info("Registering loot tables for: " + MojNovyMod.MOD_ID);
    }
}