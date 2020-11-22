package com.teamaurora.better_badlands.common.block;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class TerracottaLampBlock extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public TerracottaLampBlock(Properties builder) {
        super(builder);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getNearestLookingDirection().getOpposite());
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        Direction dir = state.get(TerracottaLampBlock.FACING);
        if (worldIn.isAirBlock(pos.offset(dir))) {
            worldIn.setBlockState(pos.offset(dir), BetterBadlandsBlocks.TERRACOTTA_LAMP_LIGHT_SOURCE.get().getDefaultState());
        }
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        Direction dir = state.get(TerracottaLampBlock.FACING);
        if (worldIn.isAirBlock(pos.offset(dir))) {
            worldIn.setBlockState(pos.offset(dir), BetterBadlandsBlocks.TERRACOTTA_LAMP_LIGHT_SOURCE.get().getDefaultState());
        }
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        Direction dir = state.get(TerracottaLampBlock.FACING);
        if (worldIn.getBlockState(pos.offset(dir)).getBlock() == BetterBadlandsBlocks.TERRACOTTA_LAMP_LIGHT_SOURCE.get()) {
            if (!MagicInvisibleLightBlock.isValidPositionFromDir(worldIn.getBlockState(pos.offset(dir)), worldIn, pos.offset(dir), dir)) {
                worldIn.setBlockState(pos.offset(dir), Blocks.AIR.getDefaultState());
            }
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    public static Direction getDir(BlockState state) {
        return state.get(TerracottaLampBlock.FACING);
    }
}
