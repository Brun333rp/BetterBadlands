package com.teamaurora.better_badlands.common.block;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

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

    default int getDistFromBlockstate(BlockState state) {
        if (state.getBlock() instanceof BrittleThatchBlock) {
            return state.get(BrittleThatchBlock.BURN_DISTANCE);
        } else if (state.getBlock() instanceof BrittleThatchStairsBlock) {
            return state.get(BrittleThatchStairsBlock.BURN_DISTANCE);
        } else if (state.getBlock() instanceof BrittleThatchVerticalSlabBlock) {
            return state.get(BrittleThatchVerticalSlabBlock.BURN_DISTANCE);
        } else if (state.getBlock() instanceof BrittleThatchSlabBlock) {
            return state.get(BrittleThatchSlabBlock.BURN_DISTANCE);
        } else {
            return 0;
        }
    }

    default IntegerProperty getDistProperty(BlockState state) {
        if (state.getBlock() instanceof BrittleThatchBlock) {
            return BrittleThatchBlock.BURN_DISTANCE;
        } else if (state.getBlock() instanceof BrittleThatchStairsBlock) {
            return BrittleThatchStairsBlock.BURN_DISTANCE;
        } else if (state.getBlock() instanceof BrittleThatchVerticalSlabBlock) {
            return BrittleThatchVerticalSlabBlock.BURN_DISTANCE;
        } else {
            return BrittleThatchSlabBlock.BURN_DISTANCE;
        }
    }

    default int getAgeFromBlockstate(BlockState state) {
        if (state.getBlock() instanceof BrittleThatchBlock) {
            return state.get(BrittleThatchBlock.BURN_TIMER);
        } else if (state.getBlock() instanceof BrittleThatchStairsBlock) {
            return state.get(BrittleThatchStairsBlock.BURN_TIMER);
        } else if (state.getBlock() instanceof BrittleThatchVerticalSlabBlock) {
            return state.get(BrittleThatchVerticalSlabBlock.BURN_TIMER);
        } else if (state.getBlock() instanceof BrittleThatchSlabBlock) {
            return state.get(BrittleThatchSlabBlock.BURN_TIMER);
        } else {
            return 0;
        }
    }

    default IntegerProperty getAgeProperty(BlockState state) {
        if (state.getBlock() instanceof BrittleThatchBlock) {
            return BrittleThatchBlock.BURN_TIMER;
        } else if (state.getBlock() instanceof BrittleThatchStairsBlock) {
            return BrittleThatchStairsBlock.BURN_TIMER;
        } else if (state.getBlock() instanceof BrittleThatchVerticalSlabBlock) {
            return BrittleThatchVerticalSlabBlock.BURN_TIMER;
        } else {
            return BrittleThatchSlabBlock.BURN_TIMER;
        }
    }

    default void tickI(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        int dist = getDistFromBlockstate(state);
        if (dist > 0) {
            int age = getAgeFromBlockstate(state) + 1;
            if (dist < 21) {
                for (Direction dir : Direction.values()) {
                    BlockState stateo = worldIn.getBlockState(pos.offset(dir));
                    if (stateo.getBlock() instanceof IBrittleThatch) {
                        if (getDistFromBlockstate(stateo) == 0) {
                            worldIn.setBlockState(pos.offset(dir), stateo.with(getDistProperty(stateo), dist + 1));
                        }
                    }
                }
            }
            if (age >= (dist - 1) * 6) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            } else {
                worldIn.setBlockState(pos, state.with(getAgeProperty(state), age));
            }
        }
    }

    default ActionResultType onBlockActivatedI(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        Item item = player.getHeldItem(handIn).getItem();
        if (item == Items.FLINT_AND_STEEL || item == Items.FIRE_CHARGE) {
            worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.PLAYERS, 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
            worldIn.setBlockState(pos, state.with(getDistProperty(state),1));
            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }
        return ActionResultType.PASS;
    }
}
