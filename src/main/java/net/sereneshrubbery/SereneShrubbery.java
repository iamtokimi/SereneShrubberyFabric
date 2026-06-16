package net.sereneshrubbery;

import net.fabricmc.api.ModInitializer;
import net.sereneshrubbery.block.BloomBasketBlock;
import net.sereneshrubbery.event.ModAdvancementEvents;
import net.sereneshrubbery.event.ModHybridBreeding;
import net.sereneshrubbery.particle.ModParticleTypes;
import net.sereneshrubbery.worldgen.ModBiomeModifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SereneShrubbery implements ModInitializer {
    public static final String MOD_ID = "serene_shrubbery";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.register();
        ModItems.register();
        BloomBasketBlock.initMappings();
        ModCreativeTab.register();
        ModBiomeModifications.register();
        ModComposting.register();
        ModParticleTypes.register();
        ModHybridBreeding.register();
        ModAdvancementEvents.register();
    }
}