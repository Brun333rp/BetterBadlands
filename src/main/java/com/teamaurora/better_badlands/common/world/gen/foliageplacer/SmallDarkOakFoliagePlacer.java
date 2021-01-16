package com.teamaurora.better_badlands.common.world.gen.foliageplacer;

import com.mojang.datafixers.Products;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationBaseReader;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class SmallDarkOakFoliagePlacer extends FoliagePlacer {
    public static final Codec<SmallDarkOakFoliagePlacer> field_236745_a_ = RecordCodecBuilder.create((p_236746_0_) -> {
        return func_242830_b(p_236746_0_).apply(p_236746_0_, SmallDarkOakFoliagePlacer::new);
    }); // CODEC
    // TODO: fix this bit

    public SmallDarkOakFoliagePlacer(FeatureSpread p_i241997_1_, FeatureSpread p_i241997_2_) {
        super(p_i241997_1_, p_i241997_2_);
    }

    protected FoliagePlacerType<?> func_230371_a_() {
        return FoliagePlacerType.ACACIA;
    } // getType()

    protected void func_230372_a_(IWorldGenerationReader worldIn, Random rand, BaseTreeFeatureConfig config, int trunkHeight, FoliagePlacer.Foliage treeNode, int foliageHeight, int radius, Set<BlockPos> leaf, int offset, MutableBoundingBox box) {
        BlockPos pos = treeNode.func_236763_a_().up(offset);

        Direction dir = Direction.byHorizontalIndex(rand.nextInt(4));

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

    private void placeRandomLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config, Set<BlockPos> leaf) {
        if (rand.nextBoolean()) {
            placeLeafAt(world, pos, rand, config, leaf);
        }
    }

    private void placeLeafAt(IWorldGenerationReader world, BlockPos pos, Random rand, BaseTreeFeatureConfig config, Set<BlockPos> leaf) {
        if (isAirOrLeaves(world, pos)) {
            this.setLogState(world, pos, config.leavesProvider.getBlockState(rand, pos).with(LeavesBlock.DISTANCE, 1));
            leaf.add(pos);
        }
    }

    public static boolean isAirOrLeaves(IWorldGenerationBaseReader worldIn, BlockPos pos) {
        if (worldIn instanceof IWorldReader) {
            return worldIn.hasBlockState(pos, state -> state.canBeReplacedByLeaves((IWorldReader) worldIn, pos));
        }
        return worldIn.hasBlockState(pos, (state) -> state.isAir() || state.isIn(BlockTags.LEAVES));
    }

    protected final void setLogState(IWorldWriter worldIn, BlockPos pos, BlockState state) {
        worldIn.setBlockState(pos, state, 18);
    }

    public int func_230374_a_(Random random, int trunkHeight, BaseTreeFeatureConfig config) {
        return 4;
    }

    protected boolean func_230373_a_(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return y != 0 || !giantTrunk || dx != -radius && dx < radius || dz != -radius && dz < radius ? super.func_230375_b_(random, dx, y, dz, radius, giantTrunk) : true;
    }

    protected boolean func_230375_b_(Random random, int baseHeight, int dx, int y, int dz, boolean giantTrunk) {
        if (dx == -1 && !giantTrunk) {
            return baseHeight == dz && y == dz;
        } else if (dx == 1) {
            return baseHeight + y > dz * 2 - 2;
        } else {
            return false;
        }
    }
}
