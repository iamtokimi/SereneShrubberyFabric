package net.sereneshrubbery;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModCreativeTab {
    public static void register() {
        Registry.register(Registries.ITEM_GROUP, SereneId.of("serene_shrubbery"),
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup.tabserene_shrubbery"))
                .icon(() -> new ItemStack(ModBlocks.BUTTERFLY_BUSH.asItem()))
                .entries((displayContext, entries) -> {
                    for (Block block : ModBlocks.getAllBlocks()) {
                        if (isFilledBloomBasket(block)) {
                            continue;
                        }
                        addBlockItem(entries, block);
                    }
                })
                .build()
        );
        SereneShrubbery.LOGGER.info("Registered creative tab");
    }

    private static boolean isFilledBloomBasket(Block block) {
        return block == ModBlocks.BLOOM_BASKET_RED_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_WHITE_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_YELLOW_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_ORANGE_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_PINK_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_PURPLE_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_FROST_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_SUNRISE_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_HALLOWEEN_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_PANOLA_PANSIES ||
               block == ModBlocks.BLOOM_BASKET_TWINFLOWERS ||
               block == ModBlocks.BLOOM_BASKET_BLUE_ORCHIDS ||
               block == ModBlocks.BLOOM_BASKET_ORANGE_CROWN_CACTI ||
               block == ModBlocks.BLOOM_BASKET_PINK_CROWN_CACTI;
    }

    private static void addBlockItem(ItemGroup.Entries entries, Block block) {
        var item = block.asItem();
        if (item != null && item != Items.AIR) {
            entries.add(new ItemStack(item, 1));
        } else {
            SereneShrubbery.LOGGER.warn("Block {} has no item!", Registries.BLOCK.getId(block));
        }
    }
}
