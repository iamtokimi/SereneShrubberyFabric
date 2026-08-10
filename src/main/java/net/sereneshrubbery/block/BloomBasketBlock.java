package net.sereneshrubbery.block;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
//? if <1.21.4 {
import net.minecraft.state.property.DirectionProperty;
//?}
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.sereneshrubbery.ModBlocks;

public class BloomBasketBlock extends Block {
    //? if <1.21.4 {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    //?} else {
    /*public static final net.minecraft.state.property.EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
    *///?}
    protected static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 12.0, 15.0);

    private static final Map<Item, Block> PANSY_TO_BASKET = new HashMap<>();
    private static final Map<Block, Item> BASKET_TO_PANSY = new HashMap<>();

    public static void initMappings() {
        PANSY_TO_BASKET.put(ModBlocks.RED_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_RED_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.WHITE_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_WHITE_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.YELLOW_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_YELLOW_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.ORANGE_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_ORANGE_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.PINK_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_PINK_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.PURPLE_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_PURPLE_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.BLUE_FROST_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_FROST_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.SUNRISE_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_SUNRISE_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.HALLOWEEN_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_HALLOWEEN_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.PANOLA_PINK_PANSIES.asItem(), ModBlocks.BLOOM_BASKET_PANOLA_PANSIES);
        PANSY_TO_BASKET.put(ModBlocks.TWINFLOWER.asItem(), ModBlocks.BLOOM_BASKET_TWINFLOWERS);
        PANSY_TO_BASKET.put(Blocks.BLUE_ORCHID.asItem(), ModBlocks.BLOOM_BASKET_BLUE_ORCHIDS);
        PANSY_TO_BASKET.put(ModBlocks.ORANGE_CROWN_CACTUS.asItem(), ModBlocks.BLOOM_BASKET_ORANGE_CROWN_CACTI);
        PANSY_TO_BASKET.put(ModBlocks.PINK_CROWN_CACTUS.asItem(), ModBlocks.BLOOM_BASKET_PINK_CROWN_CACTI);

        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_RED_PANSIES, ModBlocks.RED_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_WHITE_PANSIES, ModBlocks.WHITE_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_YELLOW_PANSIES, ModBlocks.YELLOW_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_ORANGE_PANSIES, ModBlocks.ORANGE_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_PINK_PANSIES, ModBlocks.PINK_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_PURPLE_PANSIES, ModBlocks.PURPLE_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_FROST_PANSIES, ModBlocks.BLUE_FROST_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_SUNRISE_PANSIES, ModBlocks.SUNRISE_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_HALLOWEEN_PANSIES, ModBlocks.HALLOWEEN_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_PANOLA_PANSIES, ModBlocks.PANOLA_PINK_PANSIES.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_TWINFLOWERS, ModBlocks.TWINFLOWER.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_BLUE_ORCHIDS, Blocks.BLUE_ORCHID.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_ORANGE_CROWN_CACTI, ModBlocks.ORANGE_CROWN_CACTUS.asItem());
        BASKET_TO_PANSY.put(ModBlocks.BLOOM_BASKET_PINK_CROWN_CACTI, ModBlocks.PINK_CROWN_CACTUS.asItem());
    }

    //? if >=1.21 {
    public BloomBasketBlock(Settings settings) {
        super(settings
            .sounds(BlockSoundGroup.WOOD)
            .nonOpaque()
            .strength(0.5f)
            //? if >=1.21.2 {
            /*.dropsNothing()
            *///?}
        );
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }
    //?} else {
    /*public BloomBasketBlock() {
        super(Settings.copy(Blocks.FLOWER_POT)
            .sounds(BlockSoundGroup.WOOD)
            .nonOpaque()
            .strength(0.5f));
        setDefaultState(getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }
    *///?}

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    //? if <1.21 {
    /*@Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return handleInteraction(state, world, pos, player, hand);
    }
    *///?} else {
    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return handleInteraction(state, world, pos, player, player.getActiveHand());
    }
    //?}

    private ActionResult handleInteraction(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        Block currentBlock = state.getBlock();
        ItemStack heldStack = player.getStackInHand(hand);

        if (currentBlock == ModBlocks.BLOOM_BASKET) {
            Block filledBasket = PANSY_TO_BASKET.get(heldStack.getItem());
            if (filledBasket != null) {
                //? if <1.21.4 {
                if (!world.isClient) {
                //?} else {
                /*if (!world.isClient()) {
                *///?}
                    Direction facing = state.get(FACING);
                    world.setBlockState(pos, filledBasket.getDefaultState().with(FACING, facing));
                    if (!player.isCreative()) {
                        heldStack.decrement(1);
                    }
                }
                world.playSound(player, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }

        Item pansyItem = BASKET_TO_PANSY.get(currentBlock);
        if (pansyItem != null) {
            //? if <1.21.4 {
            if (!world.isClient) {
            //?} else {
            /*if (!world.isClient()) {
            *///?}
                if (player.isSneaking()) {
                    world.removeBlock(pos, false);
                    ItemStack basketStack = new ItemStack(currentBlock.asItem());
                    if (!player.giveItemStack(basketStack)) {
                        player.dropItem(basketStack, false);
                    }
                } else {
                    Direction facing = state.get(FACING);
                    world.setBlockState(pos, ModBlocks.BLOOM_BASKET.getDefaultState().with(FACING, facing));
                    if (!player.isCreative()) {
                        ItemStack pansyStack = new ItemStack(pansyItem);
                        if (!player.giveItemStack(pansyStack)) {
                            player.dropItem(pansyStack, false);
                        }
                    }
                }
            }
            world.playSound(player, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        //? if <1.21.4 {
        if (!world.isClient && !player.isCreative()) {
        //?} else {
        /*if (!world.isClient() && !player.isCreative()) {
        *///?}
            Item pansyItem = BASKET_TO_PANSY.get(state.getBlock());
            if (pansyItem != null) {
                dropStack(world, pos, new ItemStack(ModBlocks.BLOOM_BASKET.asItem()));
                dropStack(world, pos, new ItemStack(pansyItem));
            } else {
                dropStack(world, pos, new ItemStack(this.asItem()));
            }
        }
    }
}
