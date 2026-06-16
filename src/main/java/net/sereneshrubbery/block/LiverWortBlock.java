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
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class LiverWortBlock extends PlantBlock implements Fertilizable {
    //? if >=1.21 {
    public static final MapCodec<LiverWortBlock> CODEC = createCodec(LiverWortBlock::new);
    //?}
    protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);

    //? if >=1.21 {
    public LiverWortBlock(Settings settings) {
        super(settings
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly()
            //? if >=1.21.2 {
            /*.dropsNothing()
            *///?}
        );
    }
    //?} else {
    /*public LiverWortBlock() {
        super(Settings.copy(Blocks.POPPY)
            .sounds(BlockSoundGroup.GRASS)
            .noCollision()
            .breakInstantly());
    }
    *///?}

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
