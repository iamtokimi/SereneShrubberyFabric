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
import net.minecraft.entity.Entity;
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
//? if >=1.21.9 {
/*import net.minecraft.entity.EntityCollisionHandler;
*///?}
import org.jetbrains.annotations.Nullable;

public class CrownCactusBlock extends PlantBlock implements Fertilizable {
    //? if >=1.21 {
    public static final MapCodec<CrownCactusBlock> CODEC = createCodec(CrownCactusBlock::new);
    //?}
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 10.0, 14.0);

    //? if >=1.21 {
    public CrownCactusBlock(Settings settings) {
        super(settings
            .sounds(BlockSoundGroup.WOOL)
            .strength(0.2f)
            //? if >=1.21.2 {
            /*.dropsNothing()
            *///?}
        );
    }
    //?} else {
    /*public CrownCactusBlock() {
        super(Settings.copy(Blocks.CACTUS)
            .sounds(BlockSoundGroup.WOOL)
            .strength(0.2f));
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
    //? if >=1.21.2 {
    /*protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    *///?} else {
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    //?}
        return SHAPE;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.DIRT) ||
               floor.isIn(BlockTags.SAND) ||
               floor.isOf(Blocks.FARMLAND) ||
               floor.isOf(Blocks.TERRACOTTA);
    }

    //? if <1.21.2 {
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(world.getDamageSources().cactus(), 1.0F);
    }
    //?} elif >=1.21.2 && <1.21.4 {
    /*@Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.damage(world.getDamageSources().cactus(), 1.0F);
    }
    *///?} elif >=1.21.4 && <1.21.9 {
    /*@Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (world instanceof ServerWorld serverWorld) {
            entity.damage(serverWorld, world.getDamageSources().cactus(), 1.0F);
        }
    }
    *///?} else {
    /*@Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity, EntityCollisionHandler handler, boolean intersects) {
        if (world instanceof ServerWorld serverWorld) {
            entity.damage(serverWorld, world.getDamageSources().cactus(), 1.0F);
        }
    }
    *///?}

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
