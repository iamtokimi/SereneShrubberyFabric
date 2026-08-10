package net.sereneshrubbery.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.sereneshrubbery.ModBlocks;
import net.sereneshrubbery.SereneShrubbery;
import net.sereneshrubbery.block.FlowerProperties;
import net.sereneshrubbery.data.BreedingTimerState;

import java.util.*;

public class ModHybridBreeding {

    private static final Map<Set<Block>, Block> HYBRID_RECIPES = new HashMap<>();
    private static final Map<Set<Block>, Block> HALLOWEEN_RECIPES = new HashMap<>();
    private static final Map<Set<Block>, Block> TRIPLE_HYBRID_RECIPES = new HashMap<>();
    private static final Map<Block, Block> SAME_FLOWER_RECIPES = new HashMap<>();

    private static final long MIN_BREEDING_TIME = 60_000;
    private static final long MAX_BREEDING_TIME = 600_000;

    public static void register() {
        initHybridRecipes();
        int totalRecipes = HYBRID_RECIPES.size() + HALLOWEEN_RECIPES.size() + TRIPLE_HYBRID_RECIPES.size() + SAME_FLOWER_RECIPES.size();
        SereneShrubbery.LOGGER.info("Registered {} hybrid breeding recipes", totalRecipes);
    }

    private static void initHybridRecipes() {
        addHybridRecipe(ModBlocks.PEACH_FOXGLOVE, ModBlocks.WHITE_FOXGLOVE, ModBlocks.SUNSET_FOXGLOVE);
        addHybridRecipe(ModBlocks.PURPLE_FOXGLOVE, ModBlocks.WHITE_FOXGLOVE, ModBlocks.LAVENDER_FOXGLOVE);
        addHybridRecipe(ModBlocks.PEACH_FOXGLOVE, ModBlocks.PURPLE_FOXGLOVE, ModBlocks.CANDY_MOUNTAIN_FOXGLOVE);

        addSameFlowerRecipe(ModBlocks.LUPINE_WHITE, ModBlocks.GOLDEN_LUPINE);
        addHybridRecipe(ModBlocks.LUPINE_WHITE, ModBlocks.PURPLE_LUPINE, ModBlocks.SKY_BLUE_LUPINE);
        addTripleHybridRecipe(ModBlocks.GOLDEN_LUPINE, ModBlocks.PURPLE_LUPINE, ModBlocks.LUPINE_PINK, ModBlocks.MANHATTAN_LIGHTS_LUPINE);

        addHybridRecipe(ModBlocks.RED_PANSIES, ModBlocks.YELLOW_PANSIES, ModBlocks.ORANGE_PANSIES);
        addHybridRecipe(ModBlocks.RED_PANSIES, ModBlocks.WHITE_PANSIES, ModBlocks.PINK_PANSIES);
        addHybridRecipe(ModBlocks.WHITE_PANSIES, ModBlocks.PURPLE_PANSIES, ModBlocks.BLUE_FROST_PANSIES);
        addTripleHybridRecipe(ModBlocks.ORANGE_PANSIES, ModBlocks.YELLOW_PANSIES, ModBlocks.PINK_PANSIES, ModBlocks.SUNRISE_PANSIES);
        addHybridRecipe(ModBlocks.PINK_PANSIES, ModBlocks.WHITE_PANSIES, ModBlocks.PANOLA_PINK_PANSIES);

        addHybridRecipe(ModBlocks.RED_HYDRANGEA, ModBlocks.WHITE_HYDRANGEA, ModBlocks.PINK_HYDRANGEA);
        addHybridRecipe(ModBlocks.HYDRANGEA, ModBlocks.WHITE_HYDRANGEA, ModBlocks.GREEN_HYDRANGEA);
        addHybridRecipe(ModBlocks.HYDRANGEA, ModBlocks.RED_HYDRANGEA, ModBlocks.PURPLE_HYDRANGEA);

        addHalloweenRecipe(ModBlocks.PURPLE_PANSIES, ModBlocks.ORANGE_PANSIES, ModBlocks.HALLOWEEN_PANSIES);
        addHalloweenRecipe(ModBlocks.PURPLE_FOXGLOVE, ModBlocks.SUNSET_FOXGLOVE, ModBlocks.HALLOWEEN_FOXGLOVE);
        addHalloweenRecipe(ModBlocks.PURPLE_HYDRANGEA, ModBlocks.RED_HYDRANGEA, ModBlocks.HALLOWEEN_HYDRANGEA);
    }

    private static void addHybridRecipe(Block parent1, Block parent2, Block hybrid) {
        Set<Block> parents = new HashSet<>();
        parents.add(parent1);
        parents.add(parent2);
        HYBRID_RECIPES.put(parents, hybrid);
    }

    private static void addHalloweenRecipe(Block parent1, Block parent2, Block hybrid) {
        Set<Block> parents = new HashSet<>();
        parents.add(parent1);
        parents.add(parent2);
        HALLOWEEN_RECIPES.put(parents, hybrid);
    }

    private static void addTripleHybridRecipe(Block parent1, Block parent2, Block parent3, Block hybrid) {
        Set<Block> parents = new HashSet<>();
        parents.add(parent1);
        parents.add(parent2);
        parents.add(parent3);
        TRIPLE_HYBRID_RECIPES.put(parents, hybrid);
    }

    private static void addSameFlowerRecipe(Block parent, Block hybrid) {
        SAME_FLOWER_RECIPES.put(parent, hybrid);
    }

    private static String getPosKey(BlockPos pos) {
        return pos.getX() + "," + pos.getY() + "," + pos.getZ();
    }

    public static void tryBreedOnRandomTick(ServerWorld world, BlockPos pos, Block centerFlower) {
        if (!isPlayerPlacedFlower(world.getBlockState(pos))) {
            return;
        }

        BreedingResult potentialResult = findBreedingPartner(world, pos, centerFlower);

        BreedingTimerState timerState = BreedingTimerState.getServerState(world);
        String key = getPosKey(pos);

        if (potentialResult == null) {
            timerState.removeTimer(key);
            return;
        }

        long currentTime = System.currentTimeMillis();

        if (!timerState.hasTimer(key)) {
            timerState.setTimer(key, currentTime);
            return;
        }

        Long startTime = timerState.getTimer(key);
        if (startTime == null) {
            timerState.setTimer(key, currentTime);
            return;
        }

        long elapsedTime = currentTime - startTime;

        if (elapsedTime < MIN_BREEDING_TIME) {
            return;
        }

        float timeProgress = Math.min(1.0f, (float)(elapsedTime - MIN_BREEDING_TIME) / (MAX_BREEDING_TIME - MIN_BREEDING_TIME));
        float breedingChance = timeProgress;

        if (world.getRandom().nextFloat() > breedingChance) {
            return;
        }

        BlockPos offspringPos = findEmptyAdjacentSpace(world, pos);
        if (offspringPos == null) {
            return;
        }

        timerState.removeTimer(key);

        BlockState offspringState = potentialResult.offspring.getDefaultState();
        if (offspringState.contains(FlowerProperties.PLAYER_PLACED)) {
            offspringState = offspringState.with(FlowerProperties.PLAYER_PLACED, true);
        }
        world.setBlockState(offspringPos, offspringState);

        world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
            offspringPos.getX() + 0.5, offspringPos.getY() + 0.5, offspringPos.getZ() + 0.5,
            10, 0.3, 0.3, 0.3, 0.0);

        world.playSound(null, offspringPos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.2f);
    }

    public static void onFlowerBroken(ServerWorld world, BlockPos pos) {
        BreedingTimerState timerState = BreedingTimerState.getServerState(world);
        String key = getPosKey(pos);
        timerState.removeTimer(key);
    }

    private static class BreedingResult {
        Block offspring;
        List<Block> parents;

        BreedingResult(Block offspring, Block... parents) {
            this.offspring = offspring;
            this.parents = Arrays.asList(parents);
        }
    }

    private static BreedingResult findBreedingPartner(World world, BlockPos pos, Block centerFlower) {
        BlockPos[] adjacentPositions = {
            pos.north(), pos.south(), pos.east(), pos.west(),
            pos.north().east(), pos.north().west(),
            pos.south().east(), pos.south().west()
        };

        List<Block> adjacentFlowers = new ArrayList<>();
        for (BlockPos adjPos : adjacentPositions) {
            BlockState adjacentState = world.getBlockState(adjPos);
            Block adjacentFlower = adjacentState.getBlock();
            if (isHybridizableFlower(adjacentFlower) && isPlayerPlacedFlower(adjacentState)) {
                adjacentFlowers.add(adjacentFlower);
            }
        }

        List<BreedingResult> validResults = new ArrayList<>();
        int lightLevel = world.getLightLevel(pos);

        Block sameFlowerHybrid = SAME_FLOWER_RECIPES.get(centerFlower);
        if (sameFlowerHybrid != null) {
            long matchCount = adjacentFlowers.stream().filter(f -> f == centerFlower).count();
            if (matchCount >= 1) {
                validResults.add(new BreedingResult(sameFlowerHybrid, centerFlower, centerFlower));
            }
        }

        for (Map.Entry<Set<Block>, Block> entry : TRIPLE_HYBRID_RECIPES.entrySet()) {
            Set<Block> requiredParents = entry.getKey();
            if (requiredParents.contains(centerFlower)) {
                Set<Block> otherRequired = new HashSet<>(requiredParents);
                otherRequired.remove(centerFlower);

                boolean hasAllOthers = true;
                for (Block required : otherRequired) {
                    if (!adjacentFlowers.contains(required)) {
                        hasAllOthers = false;
                        break;
                    }
                }

                if (hasAllOthers) {
                    Block[] parentsArray = requiredParents.toArray(new Block[0]);
                    validResults.add(new BreedingResult(entry.getValue(), parentsArray));
                }
            }
        }

        for (Block adjacentFlower : adjacentFlowers) {
            Set<Block> combination = new HashSet<>();
            combination.add(centerFlower);
            combination.add(adjacentFlower);

            Block halloweenHybrid = HALLOWEEN_RECIPES.get(combination);
            if (halloweenHybrid != null && lightLevel <= 4) {
                validResults.add(new BreedingResult(halloweenHybrid, centerFlower, adjacentFlower));
                continue;
            }

            Block hybrid = HYBRID_RECIPES.get(combination);
            if (hybrid != null) {
                validResults.add(new BreedingResult(hybrid, centerFlower, adjacentFlower));
            }
        }

        if (validResults.isEmpty()) {
            return null;
        }

        BreedingResult chosenResult = validResults.get(world.getRandom().nextInt(validResults.size()));

        if (world.getRandom().nextBoolean()) {
            return chosenResult;
        } else {
            Block parentCopy = chosenResult.parents.get(world.getRandom().nextInt(chosenResult.parents.size()));
            return new BreedingResult(parentCopy, chosenResult.parents.toArray(new Block[0]));
        }
    }

    public static boolean isHybridizableFlower(Block block) {
        return ModBlocks.getAllFlowers().contains(block);
    }

    public static boolean isPlayerPlacedFlower(BlockState state) {
        return state.contains(FlowerProperties.PLAYER_PLACED)
            && state.get(FlowerProperties.PLAYER_PLACED);
    }

    private static BlockPos findEmptyAdjacentSpace(World world, BlockPos centerPos) {
        BlockPos[] positions = {
            centerPos.north(), centerPos.south(), centerPos.east(), centerPos.west(),
            centerPos.north().east(), centerPos.north().west(),
            centerPos.south().east(), centerPos.south().west()
        };

        List<BlockPos> validPositions = new ArrayList<>();

        for (BlockPos checkPos : positions) {
            BlockState state = world.getBlockState(checkPos);
            BlockState below = world.getBlockState(checkPos.down());

            if (state.isAir() && isValidGround(below)) {
                validPositions.add(checkPos);
            }
        }

        if (validPositions.isEmpty()) {
            return null;
        }

        return validPositions.get(world.getRandom().nextInt(validPositions.size()));
    }

    private static boolean isValidGround(BlockState state) {
        Block block = state.getBlock();
        return block == Blocks.GRASS_BLOCK ||
               block == Blocks.DIRT ||
               block == Blocks.COARSE_DIRT ||
               block == Blocks.PODZOL ||
               block == Blocks.FARMLAND ||
               block == Blocks.ROOTED_DIRT ||
               block == Blocks.MOSS_BLOCK;
    }

}
