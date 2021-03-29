package com.teamaurora.better_badlands.common.block.trees;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsFeatures;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class SaguaroCactusTree extends Tree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return BetterBadlandsFeatures.Configured.SAGUARO_CACTUS;
    }
}
