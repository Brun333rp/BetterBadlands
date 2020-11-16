package com.teamaurora.better_badlands.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.*;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraftforge.common.IPlantable;

import java.util.*;

public class SmallDarkOakFeature extends Feature<BaseTreeFeatureConfig> {
    public SmallDarkOakFeature(Codec<BaseTreeFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean func_230362_a_(ISeedReader worldIn, StructureManager manager, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
        int height = rand.nextInt(3) + 3;
        int splitHeight = rand.nextInt(height-2) + 2;

        int randDir = rand.nextInt(4);
        Direction direction = Direction.byHorizontalIndex(randDir);

        boolean flag = true;

        if (position.getY() >= 1 && position.getY() + height + 2 <= worldIn.getHeight()) {
            for (BlockPos pos : BlockPos.getAllInBoxMutable(position.add(-1, 1, -1), position.add(1, height, 1))) {
                if (!isAirOrLeaves(worldIn, pos)) {
                    flag = false;
                    break;
                }
            }
            if (!isAirOrLeaves(worldIn, position)) flag = false;

            if (!flag) {
                return false;
            }
            else if (isValidGround(worldIn, position.down())) {
                List<BlockPos> logPos = new ArrayList<>();
                List<BlockPos> leafPos = new ArrayList<>();

                setDirtAt(worldIn, position.down());

                for (BlockPos blockPos : BlockPos.getAllInBoxMutable(position, position.up(splitHeight))) {
                    placeLogAt(worldIn, blockPos, rand, config, logPos);
                }
                for (BlockPos blockPos : BlockPos.getAllInBoxMutable(position.offset(direction).up(splitHeight), position.offset(direction).up(height))) {
                    placeLogAt(worldIn, blockPos, rand, config, logPos);
                }
                placeLeavesAt(worldIn, position.offset(direction).up(height), direction, rand, config, leafPos);

                Set<BlockPos> decSet = Sets.newHashSet();
                MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getNewBoundingBox();

                if (!config.decorators.isEmpty()) {
                    logPos.sort(Comparator.comparingInt(Vector3i::getY));
                    leafPos.sort(Comparator.comparingInt(Vector3i::getY));
                    config.decorators.forEach((decorator)->{
                        decorator.func_225576_a_(worldIn, rand, logPos, leafPos, decSet, mutableBoundingBox);
                    });
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void placeLeavesAt(ISeedReader worldIn, BlockPos pos, Direction dir, Random rand, BaseTreeFeatureConfig config, List<BlockPos> leaf) {
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,-1,-1), pos.add(1, 1, 1))) {
            placeLeafAt(worldIn, blockPos, rand, config, leaf);
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-2,0,-1),pos.add(-2,0,1))) {
            placeLeafAt(worldIn, blockPos, rand, config, leaf);
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(2,0,-1),pos.add(2,0,1))) {
            placeLeafAt(worldIn, blockPos, rand, config, leaf);
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,0,-2),pos.add(1,0,-2))) {
            placeLeafAt(worldIn, blockPos, rand, config, leaf);
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,0,2),pos.add(1,0,2))) {
            placeLeafAt(worldIn, blockPos, rand, config, leaf);
        }
        placeLeafAt(worldIn, pos.add(-2,1,0), rand, config, leaf);
        placeLeafAt(worldIn, pos.add(2,1,0), rand, config, leaf);
        placeLeafAt(worldIn, pos.add(0,1,-2), rand, config, leaf);
        placeLeafAt(worldIn, pos.add(0,1,2), rand, config, leaf);
        placeLeafAt(worldIn, pos.up(2), rand, config, leaf);

        placeLeafAt(worldIn, pos.offset(dir, 3), rand, config, leaf);
        placeRandomLeafAt(worldIn, pos.up(2).offset(dir), rand, config, leaf);

        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,0,-1).offset(dir,2), pos.add(1,0,1).offset(dir,2))) {
            placeRandomLeafAt(worldIn, blockPos, rand, config, leaf);
        }
        placeRandomLeafAt(worldIn, pos.down().offset(dir,-2),rand,config,leaf);
    }

    private void placeLogAt(IWorldWriter worldIn, BlockPos pos, Random rand, BaseTreeFeatureConfig config, List<BlockPos> log) {
        this.setLogState(worldIn, pos, config.trunkProvider.getBlockState(rand, pos));
        log.add(pos);
    }

    private void placeRandomLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config, List<BlockPos> leaf) {
        if (rand.nextBoolean()) {
            placeLeafAt(world, pos, rand, config, leaf);
        }
    }

    private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config, List<BlockPos> leaf) {
        if (isAirOrLeaves(world, pos)) {
            this.setLogState(world, pos, config.leavesProvider.getBlockState(rand, pos).with(LeavesBlock.DISTANCE, 1));
            leaf.add(pos);
        }
    }

    protected final void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state, 18);
    }

    public static boolean isAir(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        if (!(worldIn instanceof IBlockReader)) {
            return worldIn.hasBlockState(pos, BlockState::isAir);
        }
        else {
            return worldIn.hasBlockState(pos, state -> state.isAir((IBlockReader) worldIn, pos));
        }
    }

    public static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        if (worldIn instanceof IWorldReader) {
            return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((IWorldReader) worldIn, pos));
        }
        return worldIn.hasBlockState(pos, (state) -> state.isAir() || state.isIn(BlockTags.LEAVES));
    }

    public static void setDirtAt(IWorld worldIn, BlockPos pos) {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block == Blocks.GRASS_BLOCK || block == Blocks.FARMLAND) {
            worldIn.setBlockState(pos, Blocks.DIRT.getDefaultState(), 16);
        }
    }

    public static boolean isValidGround(IWorld world, BlockPos pos) {
        return world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (IPlantable) Blocks.DARK_OAK_SAPLING);
    }
}
