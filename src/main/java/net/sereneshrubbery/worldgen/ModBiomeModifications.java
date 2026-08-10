package net.sereneshrubbery.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.sereneshrubbery.SereneId;
import net.sereneshrubbery.SereneShrubbery;

public class ModBiomeModifications {
    private static final RegistryKey<PlacedFeature> BLANKETFLOWER = registerPlacedFeature("blanketflower");
    private static final RegistryKey<PlacedFeature> BLUE_LIVERWORT = registerPlacedFeature("blue_liverwort");
    private static final RegistryKey<PlacedFeature> BUTTERFLY_BUSH = registerPlacedFeature("butterfly_bush");
    private static final RegistryKey<PlacedFeature> WHITE_BUTTERFLY_BUSH = registerPlacedFeature("white_butterfly_bush");
    private static final RegistryKey<PlacedFeature> PINK_BUTTERFLY_BUSH = registerPlacedFeature("pink_butterfly_bush");
    private static final RegistryKey<PlacedFeature> INDIGO_BUTTERFLY_BUSH = registerPlacedFeature("indigo_butterfly_bush");
    private static final RegistryKey<PlacedFeature> FIREWEED = registerPlacedFeature("fireweed");
    private static final RegistryKey<PlacedFeature> HYDRANGEA = registerPlacedFeature("hydrangea");
    private static final RegistryKey<PlacedFeature> LUPINE_PINK = registerPlacedFeature("lupine_pink");
    private static final RegistryKey<PlacedFeature> LUPINE_WHITE = registerPlacedFeature("lupine_white");
    private static final RegistryKey<PlacedFeature> ORANGE_CROWN_CACTUS = registerPlacedFeature("orange_crown_cactus");
    private static final RegistryKey<PlacedFeature> PEACH_FOXGLOVE = registerPlacedFeature("peach_foxglove");
    private static final RegistryKey<PlacedFeature> PINK_CROWN_CACTUS = registerPlacedFeature("pink_crown_cactus");
    private static final RegistryKey<PlacedFeature> PURPLE_FOXGLOVE = registerPlacedFeature("purple_foxglove");
    private static final RegistryKey<PlacedFeature> PURPLE_LIVERWORT = registerPlacedFeature("purple_liverwort");
    private static final RegistryKey<PlacedFeature> PURPLE_LUPINE = registerPlacedFeature("purple_lupine");
    private static final RegistryKey<PlacedFeature> PURPLE_PANSIES = registerPlacedFeature("purple_pansies");
    private static final RegistryKey<PlacedFeature> RED_HYDRANGEA = registerPlacedFeature("red_hydrangea");
    private static final RegistryKey<PlacedFeature> RED_PANSIES = registerPlacedFeature("red_pansies");
    private static final RegistryKey<PlacedFeature> TWINFLOWER = registerPlacedFeature("twinflower");
    private static final RegistryKey<PlacedFeature> WHITE_FOXGLOVE = registerPlacedFeature("white_foxglove");
    private static final RegistryKey<PlacedFeature> WHITE_HYDRANGEA = registerPlacedFeature("white_hydrangea");
    private static final RegistryKey<PlacedFeature> WHITE_LIVERWORT = registerPlacedFeature("white_liverwort");
    private static final RegistryKey<PlacedFeature> WHITE_PANSIES = registerPlacedFeature("white_pansies");
    private static final RegistryKey<PlacedFeature> YELLOW_PANSIES = registerPlacedFeature("yellow_pansies");

    private static RegistryKey<PlacedFeature> registerPlacedFeature(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, SereneId.of(name));
    }

    public static void register() {
        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BEACH, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA),
            GenerationStep.Feature.VEGETAL_DECORATION,
            BLANKETFLOWER
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            BLUE_LIVERWORT
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW, BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            BUTTERFLY_BUSH
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.SNOWY_PLAINS, BiomeKeys.BIRCH_FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            WHITE_BUTTERFLY_BUSH
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW, BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PINK_BUTTERFLY_BUSH
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.FLOWER_FOREST, BiomeKeys.MEADOW, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.SNOWY_PLAINS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            INDIGO_BUTTERFLY_BUSH
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.CHERRY_GROVE, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.MEADOW, BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            FIREWEED
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BEACH, BiomeKeys.FLOWER_FOREST, BiomeKeys.RIVER),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HYDRANGEA
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.CHERRY_GROVE, BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.TAIGA, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            LUPINE_PINK
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            LUPINE_WHITE
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BADLANDS, BiomeKeys.DESERT, BiomeKeys.ERODED_BADLANDS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.WOODED_BADLANDS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            ORANGE_CROWN_CACTUS
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.WINDSWEPT_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PEACH_FOXGLOVE
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BADLANDS, BiomeKeys.DESERT, BiomeKeys.ERODED_BADLANDS, BiomeKeys.SAVANNA, BiomeKeys.SAVANNA_PLATEAU, BiomeKeys.WINDSWEPT_SAVANNA, BiomeKeys.WOODED_BADLANDS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PINK_CROWN_CACTUS
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.SNOWY_TAIGA, BiomeKeys.STONY_PEAKS, BiomeKeys.TAIGA, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PURPLE_FOXGLOVE
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PURPLE_LIVERWORT
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.SNOWY_PLAINS, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PURPLE_LUPINE
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.MEADOW, BiomeKeys.PLAINS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            PURPLE_PANSIES
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BEACH, BiomeKeys.FLOWER_FOREST, BiomeKeys.RIVER),
            GenerationStep.Feature.VEGETAL_DECORATION,
            RED_HYDRANGEA
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.PLAINS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            RED_PANSIES
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.CHERRY_GROVE, BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            TWINFLOWER
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.SNOWY_TAIGA, BiomeKeys.TAIGA, BiomeKeys.WINDSWEPT_FOREST, BiomeKeys.WINDSWEPT_HILLS, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            WHITE_FOXGLOVE
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BAMBOO_JUNGLE, BiomeKeys.BEACH, BiomeKeys.FLOWER_FOREST, BiomeKeys.RIVER),
            GenerationStep.Feature.VEGETAL_DECORATION,
            WHITE_HYDRANGEA
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.CHERRY_GROVE, BiomeKeys.DARK_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST),
            GenerationStep.Feature.VEGETAL_DECORATION,
            WHITE_LIVERWORT
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.MEADOW, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.PLAINS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            WHITE_PANSIES
        );

        BiomeModifications.addFeature(
            BiomeSelectors.includeByKey(BiomeKeys.BIRCH_FOREST, BiomeKeys.FLOWER_FOREST, BiomeKeys.FOREST, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, BiomeKeys.PLAINS, BiomeKeys.SUNFLOWER_PLAINS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            YELLOW_PANSIES
        );

        SereneShrubbery.LOGGER.info("Registered biome modifications for 25 features");
    }
}
