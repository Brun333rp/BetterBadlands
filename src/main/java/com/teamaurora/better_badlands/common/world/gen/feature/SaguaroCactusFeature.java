package com.teamaurora.better_badlands.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.mojang.serialization.Codec;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.block.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

import java.util.*;

public class SaguaroCactusFeature extends Feature<BaseTreeFeatureConfig> {
    public SaguaroCactusFeature(Codec<BaseTreeFeatureConfig> config) {
        super(config);
    }

    private int randBetween(int low, int high, Random rand) {
        return low + rand.nextInt(high - low + 1);
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
        int height = rand.nextInt(3) + 4;
        Direction dir = Direction.byHorizontalIndex(rand.nextInt(4));
        boolean back = rand.nextBoolean();
        if (position.getY() <= 0 || position.getY() + height + 1 > worldIn.getHeight()) {
            return false;
        }
        Block downward = worldIn.getBlockState(position.down()).getBlock();
        if (!downward.isIn(BlockTags.SAND)) {
            return false;
        }

        for (BlockPos pos : BlockPos.getAllInBoxMutable(position.up().offset(dir), position.up(height).offset(dir, back ? -1 : 0))) {
            if (!TreeUtil.isAirOrLeaves(worldIn, pos)) return false;
        }

        for (int i = 0; i <= height; i++) {
            TreeUtil.setForcedState(worldIn, position.up(i), BetterBadlandsBlocks.SAGUARO_CACTUS.get().getDefaultState());
        }
        TreeUtil.setForcedState(worldIn, position.up(height+1), BetterBadlandsBlocks.SAGUARO_FLOWER.get().getDefaultState());
        int height1, height2, depth1, depth2;
        depth1 = 1 + rand.nextInt(height - 3);
        depth2 = 1 + rand.nextInt(height - 3);
        height1 = randBetween(depth1 + 1, height - 1, rand);
        height2 = randBetween(depth2 + 1, height - 1, rand);
        for (int i = depth1; i <= height1; i++) {
            BlockState sag1 = BetterBadlandsBlocks.SMALL_SAGUARO_CACTUS.get().getDefaultState();
            if (i == depth1) {
                sag1 = sag1.with(SixWayBlock.UP, true).with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(dir.getOpposite()), true);
            } else if (i == height1) {
                if (rand.nextBoolean()) {
                    sag1 = sag1.with(SixWayBlock.DOWN, true).with(SixWayBlock.UP, true);
                    TreeUtil.setForcedState(worldIn, position.offset(dir).up(i+1), BetterBadlandsBlocks.SAGUARO_FLOWER.get().getDefaultState());
                } else {
                    sag1 = sag1.with(SixWayBlock.DOWN, true);
                }
            } else {
                sag1 = sag1.with(SixWayBlock.UP, true).with(SixWayBlock.DOWN, true);
            }
            TreeUtil.setForcedState(worldIn, position.offset(dir).up(i), sag1);
        }
        if (back) {
            for (int i = depth2; i <= height2; i++) {
                BlockState sag2 = BetterBadlandsBlocks.SMALL_SAGUARO_CACTUS.get().getDefaultState();
                if (i == depth2) {
                    sag2 = sag2.with(SixWayBlock.UP, true).with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(dir), true);
                } else if (i == height2) {
                    if (rand.nextBoolean()) {
                        sag2 = sag2.with(SixWayBlock.DOWN, true).with(SixWayBlock.UP, true);
                        TreeUtil.setForcedState(worldIn, position.offset(dir.getOpposite()).up(i+1), BetterBadlandsBlocks.SAGUARO_FLOWER.get().getDefaultState());
                    } else {
                        sag2 = sag2.with(SixWayBlock.DOWN, true);
                    }
                } else {
                    sag2 = sag2.with(SixWayBlock.UP, true).with(SixWayBlock.DOWN, true);
                }
                TreeUtil.setForcedState(worldIn, position.offset(dir.getOpposite()).up(i), sag2);
            }
        }

        return true;
    }
}
