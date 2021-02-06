package com.teamaurora.better_badlands.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.mojang.serialization.Codec;
import net.minecraft.block.*;
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

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

@SuppressWarnings("deprecation")
public class SmallDarkOakFeature extends Feature<BaseTreeFeatureConfig> {
    public SmallDarkOakFeature(Codec<BaseTreeFeatureConfig> config) {
        super(config);
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
        int height = rand.nextInt(3) + 3;
        int splitHeight = rand.nextInt(height-2) + 2;

        int randDir = rand.nextInt(4);
        Direction direction = Direction.byHorizontalIndex(randDir);


        if (position.getY() >= 1 && position.getY() + height + 2 <= worldIn.getHeight()) {
            if (TreeUtil.isValidGround(worldIn, position.down(), (SaplingBlock) Blocks.DARK_OAK_SAPLING)) {
                List<BlockPos> logs = new ArrayList<>();
                List<BlockPos> leaves = new ArrayList<>();

                for (BlockPos blockPos : BlockPos.getAllInBoxMutable(position, position.up(splitHeight))) {
                    logs.add(new BlockPos(blockPos));
                }
                for (BlockPos blockPos : BlockPos.getAllInBoxMutable(position.offset(direction).up(splitHeight), position.offset(direction).up(height))) {
                    logs.add(new BlockPos(blockPos));
                }
                placeLeavesAt(leaves, position.offset(direction).up(height), direction, rand);


                List<BlockPos> leavesClean = cleanLeavesArray(leaves, logs);

                boolean flag = true;
                for (BlockPos log : logs) {
                    if (!TreeUtil.isAirOrLeaves(worldIn, log)) {
                        flag = false;
                    }
                }
                if (!flag) return false;

                TreeUtil.setDirtAt(worldIn, position.down());

                for (BlockPos log : logs) {
                    TreeUtil.placeLogAt(worldIn, log, rand, config);
                }
                for (BlockPos leaf : leavesClean) {
                    TreeUtil.placeLeafAt(worldIn, leaf, rand, config);
                }

                Set<BlockPos> decSet = Sets.newHashSet();
                MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getNewBoundingBox();

                if (!config.decorators.isEmpty()) {
                    logs.sort(Comparator.comparingInt(Vector3i::getY));
                    leaves.sort(Comparator.comparingInt(Vector3i::getY));
                    config.decorators.forEach((decorator) -> decorator.func_225576_a_(worldIn, rand, logs, leaves, decSet, mutableBoundingBox));
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private void placeLeavesAt(List<BlockPos> leaf, BlockPos pos, Direction dir, Random rand) {
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,-1,-1), pos.add(1, 1, 1))) {
            leaf.add(new BlockPos(blockPos));
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-2,0,-1),pos.add(-2,0,1))) {
            leaf.add(new BlockPos(blockPos));
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(2,0,-1),pos.add(2,0,1))) {
            leaf.add(new BlockPos(blockPos));
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,0,-2),pos.add(1,0,-2))) {
            leaf.add(new BlockPos(blockPos));
        }
        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,0,2),pos.add(1,0,2))) {
            leaf.add(new BlockPos(blockPos));
        }
        leaf.add(pos.add(-2,1,0));
        leaf.add(pos.add(2,1,0));
        leaf.add(pos.add(0,1,-2));
        leaf.add(pos.add(0,1,2));
        leaf.add(pos.up(2));

        leaf.add(pos.offset(dir, 3));
        placeRandomLeafAt(pos.up(2).offset(dir), rand, leaf);

        for (BlockPos blockPos : BlockPos.getAllInBoxMutable(pos.add(-1,0,-1).offset(dir,2), pos.add(1,0,1).offset(dir,2))) {
            placeRandomLeafAt(new BlockPos(blockPos), rand, leaf);
        }
        placeRandomLeafAt(pos.down().offset(dir,-2),rand,leaf);
    }

    private void placeRandomLeafAt(BlockPos pos, Random rand, List<BlockPos> leaf) {
        if (rand.nextBoolean()) {
            leaf.add(pos);
        }
    }

    private List<BlockPos> cleanLeavesArray(List<BlockPos> leaves, List<BlockPos> logs) {
        List<BlockPos> newLeaves = new ArrayList<>();
        for (BlockPos leaf : leaves) {
            if (!logs.contains(leaf)) {
                newLeaves.add(leaf);
            }
        }
        return newLeaves;
    }
}