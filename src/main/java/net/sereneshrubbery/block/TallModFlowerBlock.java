package net.sereneshrubbery.block;

//? if >=1.21 {
import com.mojang.serialization.MapCodec;
//?}
//? if >=1.21 {
import net.minecraft.block.AbstractBlock;
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
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.sereneshrubbery.event.ModHybridBreeding;
import org.jetbrains.annotations.Nullable;

public class TallModFlowerBlock extends PlantBlock implements Fertilizable {
    //? if >=1.21 {
    public static final MapCodec<TallModFlowerBlock> CODEC = createCodec(TallModFlowerBlock::new);
    //?}
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 32.0, 14.0);

    //? if >=1.21 {
    public TallModFlowerBlock(Settings settings) {
        super(settings
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly()
            .ticksRandomly()
            //? if >=1.21.2 {
            /*.dropsNothing()
            *///?}
        );
        setDefaultState(getStateManager().getDefaultState().with(FlowerProperties.PLAYER_PLACED, false));
    }
    //?} else {
    /*public TallModFlowerBlock() {
        super(Settings.copy(Blocks.POPPY)
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly()
            .ticksRandomly());
        setDefaultState(getStateManager().getDefaultState().with(FlowerProperties.PLAYER_PLACED, false));
    }
    *///?}

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FlowerProperties.PLAYER_PLACED);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(FlowerProperties.PLAYER_PLACED, true);
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
        return floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);
    }

    @Override
    //? if >=1.21.2 {
    /*protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    *///?} else {
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
    //?}
        ModHybridBreeding.tryBreedOnRandomTick(world, pos, state.getBlock());
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
            dropStack(world, pos, new ItemStack(this.asItem()));
        }
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
        dropStack(world, pos, new ItemStack(this.asItem()));

        world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
            pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
            10, 0.3, 0.3, 0.3, 0.0);
    }
}
