package com.teamaurora.better_badlands.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IBrittleThatch {
    default void onBlockAddedI(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        boolean flag = false;
        for (Direction dir : Direction.values()) {
            if (worldIn.getBlockState(pos.offset(dir)).getBlock() == Blocks.LAVA) {
                flag = true;
            }
        }
        if (flag) {
            worldIn.destroyBlock(pos, false);
        }
        //super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    default void neighborChangedI(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        boolean flag = false;
        for (Direction dir : Direction.values()) {
            if (worldIn.getBlockState(pos.offset(dir)).getBlock() == Blocks.LAVA) {
                flag = true;
            }
        }
        if (flag) {
            worldIn.destroyBlock(pos, false);
        }
        //super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }
}
