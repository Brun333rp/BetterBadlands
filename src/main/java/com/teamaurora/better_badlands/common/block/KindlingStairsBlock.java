package com.teamaurora.better_badlands.common.block;

import com.teamabnormals.abnormals_core.common.blocks.thatch.ThatchStairsBlock;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.state.*;
import net.minecraft.state.properties.Half;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

@SuppressWarnings("deprecated")
public class KindlingStairsBlock extends ThatchStairsBlock implements IKindling {

    public static final DirectionProperty FACING = StairsBlock.FACING;
    public static final EnumProperty<Half> HALF = StairsBlock.HALF;
    public static final EnumProperty<StairsShape> SHAPE = StairsBlock.SHAPE;
    public static final BooleanProperty WATERLOGGED = StairsBlock.WATERLOGGED;

    public KindlingStairsBlock(BlockState state, Properties properties) {
        super(state, properties);
        this.setDefaultState(this.getDefaultState().with(BURN_DISTANCE, 0).with(IS_BURNED, false).with(FACING, Direction.NORTH).with(HALF, Half.BOTTOM).with(SHAPE, StairsShape.STRAIGHT).with(WATERLOGGED, false));
    }
    @Override
    public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) {
        this.onProjectileCollisionI(worldIn, state, hit, projectile);
        super.onProjectileCollision(worldIn, state, hit, projectile);
    }
    @Override
    public boolean ticksRandomly(BlockState state) {
        return false;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BURN_DISTANCE, FACING, HALF, SHAPE, WATERLOGGED, IS_BURNED);
    }

    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        this.animateTickI(stateIn, worldIn, pos, rand);
        super.animateTick(stateIn, worldIn, pos, rand);
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        this.onBlockAddedI(state, worldIn, pos, oldState, isMoving);
        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        this.neighborChangedI(state, worldIn, pos, blockIn, fromPos, isMoving);
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
    }

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        this.tickI(state, worldIn, pos, rand);
        super.tick(state, worldIn, pos, rand);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return this.onBlockActivatedI(state, worldIn, pos, player, handIn, hit);
    }
}
