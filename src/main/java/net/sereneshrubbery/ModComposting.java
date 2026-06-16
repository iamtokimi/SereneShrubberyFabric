package net.sereneshrubbery;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;

public class ModComposting {
    private static final float FLOWER_COMPOST_CHANCE = 0.65f;
    private static final float TALL_FLOWER_COMPOST_CHANCE = 0.65f;

    public static void register() {
        CompostingChanceRegistry registry = CompostingChanceRegistry.INSTANCE;

        registry.add(ModBlocks.RED_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.WHITE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.YELLOW_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.ORANGE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PINK_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BLUE_FROST_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PANOLA_PINK_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.SUNRISE_PANSIES, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.HALLOWEEN_PANSIES, FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PINK_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.RED_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.WHITE_HYDRANGEA, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.HALLOWEEN_HYDRANGEA, FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.WHITE_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PEACH_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.SUNSET_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.HALLOWEEN_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.CANDY_MOUNTAIN_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.LAVENDER_FOXGLOVE, TALL_FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.LUPINE_PINK, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.LUPINE_WHITE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_LUPINE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.GOLDEN_LUPINE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.SKY_BLUE_LUPINE, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.MANHATTAN_LIGHTS_LUPINE, FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.BLUE_LIVERWORT, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PURPLE_LIVERWORT, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.WHITE_LIVERWORT, FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.BLANKETFLOWER, FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.FIREWEED, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.BUTTERFLY_BUSH, TALL_FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.TWINFLOWER, FLOWER_COMPOST_CHANCE);

        registry.add(ModBlocks.ORANGE_CROWN_CACTUS, FLOWER_COMPOST_CHANCE);
        registry.add(ModBlocks.PINK_CROWN_CACTUS, FLOWER_COMPOST_CHANCE);

        SereneShrubbery.LOGGER.info("Registered composting for all flowers");
    }
}
