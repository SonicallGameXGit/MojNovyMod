package org.sgx.mojnovymod.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.sgx.mojnovymod.MojNovyMod;

import java.util.function.Predicate;

public class ModFeatures {
    public static final RegistryKey<PlacedFeature> OVERWORLD_OMINAN_ORE = registerOreFeature(
            "overworld_ominan_ore",
            RegistryKeys.PLACED_FEATURE,
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Feature.UNDERGROUND_ORES
    );
    public static final RegistryKey<PlacedFeature> NETHER_OMINAN_ORE = registerOreFeature(
            "nether_ominan_ore",
            RegistryKeys.PLACED_FEATURE,
            BiomeSelectors.foundInTheNether(),
            GenerationStep.Feature.UNDERGROUND_ORES
    );
    public static final RegistryKey<PlacedFeature> END_OMINAN_ORE = registerOreFeature(
            "end_ominan_ore",
            RegistryKeys.PLACED_FEATURE,
            BiomeSelectors.foundInTheEnd(),
            GenerationStep.Feature.UNDERGROUND_ORES
    );

    private static RegistryKey<PlacedFeature> registerOreFeature(String id, RegistryKey<Registry<PlacedFeature>> registryKey, Predicate<BiomeSelectionContext> biomeSelectionContext, GenerationStep.Feature feature) {
        RegistryKey<PlacedFeature> result = RegistryKey.of(registryKey, new Identifier(MojNovyMod.MOD_ID, id));
        BiomeModifications.addFeature(biomeSelectionContext, feature, result);

        return result;
    }

    public static void register() {
        MojNovyMod.LOGGER.info("Registering features for: " + MojNovyMod.MOD_ID);
    }
}