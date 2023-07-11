package org.sgx.mojnovymod.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.block.ModBlocks;
import org.sgx.mojnovymod.item.ModItems;

public class ModVillagers {
    public static final PointOfInterestType MAGIC_POI = registerPoi("magic_poi", ModBlocks.MAGIC_BLOCK);
    public static final VillagerProfession MAGICIAN = registerProfession(
            "magician",
            SoundEvents.ENTITY_VILLAGER_WORK_CLERIC,
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), new Identifier(MojNovyMod.MOD_ID, "magic_poi"))
    );

    private static VillagerProfession registerProfession(String id, SoundEvent sounds, RegistryKey<PointOfInterestType> type) {
        return Registry.register(
                Registries.VILLAGER_PROFESSION,
                new Identifier(MojNovyMod.MOD_ID, id),
                VillagerProfessionBuilder
                        .create()
                        .id(new Identifier(MojNovyMod.MOD_ID))
                        .workstation(type)
                        .workSound(sounds)
                        .build()
        );
    }

    private static PointOfInterestType registerPoi(String id, Block block) {
        return PointOfInterestHelper.register(
                new Identifier(MojNovyMod.MOD_ID, id),
                2,
                1,
                ImmutableSet.copyOf(block.getStateManager().getStates())
        );
    }

    private static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(MAGICIAN, 1, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 2),
                    new ItemStack(ModItems.NEW_HELMET, 3),
                    3,
                    5,
                    0.05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 105),
                    new ItemStack(ModItems.NEW_CHESTPLATE, 0),
                    3,
                    5,
                    0.05f
            )));
        });
        TradeOfferHelper.registerVillagerOffers(MAGICIAN, 2, factories -> {
            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 33),
                    new ItemStack(ModItems.NEW_LEGGINGS, 1),
                    3,
                    5,
                    0.05f
            )));
            factories.add(((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(ModItems.NEW_BOOTS, 1),
                    3,
                    5,
                    0.05f
            )));
        });
    }

    public static void register() {
        registerTrades();
        MojNovyMod.LOGGER.info("Registering villagers for: " + MojNovyMod.MOD_ID);
    }
}