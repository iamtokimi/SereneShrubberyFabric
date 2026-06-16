package net.sereneshrubbery.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.sereneshrubbery.SereneShrubbery;

public class ModAdvancementEvents {

    //? if >=1.21 {
    private static final TagKey<net.minecraft.block.Block> WILD_FLOWERS_TAG =
        TagKey.of(RegistryKeys.BLOCK, Identifier.of(SereneShrubbery.MOD_ID, "wild_flowers"));
    private static final TagKey<net.minecraft.block.Block> HYBRID_FLOWERS_TAG =
        TagKey.of(RegistryKeys.BLOCK, Identifier.of(SereneShrubbery.MOD_ID, "hybrid_flowers"));
    //?} else {
    /*private static final TagKey<net.minecraft.block.Block> WILD_FLOWERS_TAG =
        TagKey.of(RegistryKeys.BLOCK, new Identifier(SereneShrubbery.MOD_ID, "wild_flowers"));
    private static final TagKey<net.minecraft.block.Block> HYBRID_FLOWERS_TAG =
        TagKey.of(RegistryKeys.BLOCK, new Identifier(SereneShrubbery.MOD_ID, "hybrid_flowers"));
    *///?}

    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            //? if <1.21.4 {
            if (world.isClient) return;
            //?} else {
            /*if (world.isClient()) return;
            *///?}
            if (!(player instanceof ServerPlayerEntity serverPlayer)) {
                return;
            }
            if (!(world instanceof ServerWorld serverWorld)) {
                return;
            }

            if (state.isIn(WILD_FLOWERS_TAG)) {
                grantAdvancement(serverPlayer, serverWorld, "gardeners_path");
            }
            if (state.isIn(HYBRID_FLOWERS_TAG)) {
                grantAdvancement(serverPlayer, serverWorld, "hybrid_flora");
            }
        });
    }

    private static void grantAdvancement(ServerPlayerEntity player, ServerWorld serverWorld, String advancementName) {
        //? if >=1.21 {
        Identifier id = Identifier.of(SereneShrubbery.MOD_ID, advancementName);
        //?} else {
        /*Identifier id = new Identifier(SereneShrubbery.MOD_ID, advancementName);
        *///?}

        try {
            var server = serverWorld.getServer();
            var advancementEntry = server.getAdvancementLoader().get(id);
            if (advancementEntry != null) {
                AdvancementProgress progress = player.getAdvancementTracker().getProgress(advancementEntry);
                if (!progress.isDone()) {
                    for (String criterion : progress.getUnobtainedCriteria()) {
                        player.getAdvancementTracker().grantCriterion(advancementEntry, criterion);
                    }
                }
            }
        } catch (Exception e) {
            SereneShrubbery.LOGGER.debug("Could not grant advancement {}: {}", advancementName, e.getMessage());
        }
    }
}
