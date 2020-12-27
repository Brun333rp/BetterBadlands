package com.teamaurora.better_badlands.common.world.biome;

import com.minecraftabnormals.abnormals_core.common.world.modification.BiomeFeatureModifier;
import com.minecraftabnormals.abnormals_core.common.world.modification.BiomeModificationPredicates;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BetterBadlandsBiomeFeatures {
    public static BlockState DARK_OAK_LOG = Blocks.DARK_OAK_LOG.getDefaultState();
    public static BlockState DARK_OAK_LEAVES = Blocks.DARK_OAK_LEAVES.getDefaultState();

    public static final BaseTreeFeatureConfig SMALL_DARK_OAK_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(DARK_OAK_LOG), new SimpleBlockStateProvider(DARK_OAK_LEAVES), null, null, null)).setIgnoreVines().build();
}
