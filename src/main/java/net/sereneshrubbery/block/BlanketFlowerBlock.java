package net.sereneshrubbery.block;

//? if >=1.21 {
import com.mojang.serialization.MapCodec;
//?}
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
//? if <1.21.4 {
import net.minecraft.state.property.DirectionProperty;
//?}
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BlanketFlowerBlock extends PlantBlock implements Fertilizable {
    public static final int MIN_FLOWER_AMOUNT = 1;
    public static final int MAX_FLOWER_AMOUNT = 4;
    public static final IntProperty FLOWER_AMOUNT = IntProperty.of("flower_amount", MIN_FLOWER_AMOUNT, MAX_FLOWER_AMOUNT);

    //? if <1.21.4 {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    //?} else {
    /*public static final net.minecraft.state.property.EnumProperty<Direction> FACING = Properties.HORIZONTAL_FACING;
     *///?}

    //? if >=1.21 {
    public static final MapCodec<BlanketFlowerBlock> CODEC = createCodec(BlanketFlowerBlock::new);
    //?}

    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 14.0, 14.0);

    //? if >=1.21 {
    public BlanketFlowerBlock(Settings settings) {
        super(settings
                        .sounds(BlockSoundGroup.GRASS)
                        .noCollision()
                        .breakInstantly()
                //? if >=1.21.2 {
                /*.dropsNothing()
                 *///?}
        );
        setDefaultState(getStateManager().getDefaultState().with(FLOWER_AMOUNT, 1).with(FACING, Direction.NORTH));
    }
    //?} else {
    /*public BlanketFlowerBlock() {
        super(Settings.copy(Blocks.POPPY)
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly());
        setDefaultState(getStateManager().getDefaultState().with(FLOWER_AMOUNT, 1).with(FACING, Direction.NORTH));
    }
    *///?}

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FLOWER_AMOUNT, FACING);
    }

    @Override
            //? if >=1.21.2 {
    /*protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
     *///?} else {
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //?}
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) || floor.isIn(BlockTags.SAND) || floor.isOf(Blocks.FARMLAND);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.shouldCancelInteraction()
                && context.getStack().isOf(this.asItem())
                && state.get(FLOWER_AMOUNT) < MAX_FLOWER_AMOUNT
                || super.canReplace(state, context);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState existingState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (existingState.isOf(this)) {
            return existingState.with(FLOWER_AMOUNT,
                    Math.min(MAX_FLOWER_AMOUNT, existingState.get(FLOWER_AMOUNT) + 1));
        }
        return getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

    @Override
            //? if >=1.20.2 {
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        //?} else {
        /*public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
         *///?}
        return true;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        int currentAmount = state.get(FLOWER_AMOUNT);
        if (currentAmount < MAX_FLOWER_AMOUNT) {
            world.setBlockState(pos, state.with(FLOWER_AMOUNT, currentAmount + 1), Block.NOTIFY_LISTENERS);
        } else {
            dropStack(world, pos, new ItemStack(this.asItem()));
        }
    }

    //? if >=1.21 {
    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }
    //?}

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
        //? if <1.21.4 {
        if (!world.isClient && !player.isCreative()) {
            //?} else {
            /*if (!world.isClient() && !player.isCreative()) {
             *///?}
            int amount = state.get(FLOWER_AMOUNT);
            dropStack(world, pos, new ItemStack(this.asItem(), amount));
        }
    }
}