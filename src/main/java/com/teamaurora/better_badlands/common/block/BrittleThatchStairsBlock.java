package com.teamaurora.better_badlands.common.block;

import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchStairsBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

@SuppressWarnings("deprecated")
public class BrittleThatchStairsBlock extends ThatchStairsBlock implements IBrittleThatch {
    public BrittleThatchStairsBlock (BlockState state, Properties properties) {
        super(state, properties);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        this.onBlockAddedI(state, worldIn, pos, oldState, isMoving);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.neighborChangedI(state, worldIn, pos, blockIn, fromPos, isMoving);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }
}
