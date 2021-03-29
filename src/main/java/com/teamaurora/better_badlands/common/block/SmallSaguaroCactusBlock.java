package com.teamaurora.better_badlands.common.block;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsEffects;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.Random;

public class SmallSaguaroCactusBlock extends SixWayBlock implements IPlantable {
    protected static final VoxelShape COLLISION_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 15.0D, 15.0D);

    public SmallSaguaroCactusBlock(AbstractBlock.Properties properties) {
        super(0.25F, properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(NORTH, false).with(SOUTH, false).with(EAST, false).with(WEST, false).with(UP, false).with(DOWN, false));
    }

    public boolean canConnect(Block block) {
        return block.isIn(BlockTags.SAND) || block == this || block == BetterBadlandsBlocks.SAGUARO_CACTUS.get() || block == BetterBadlandsBlocks.SAGUARO_FLOWER.get();
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        /*BlockState connected = makeConnections(context.getWorld(), context.getPos());
        Direction placedOnDir = context.getFace().getOpposite();
        if (context.getWorld().getBlockState(context.getPos().offset(placedOnDir)).getBlock() == BetterBadlandsBlocks.SAGUARO_CACTUS.get()) {
            return connected.with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(placedOnDir), true);
        }
        return connected;*/
        BlockState connected = this.getDefaultState();
        Direction placedOnDir = context.getFace().getOpposite();
        BlockState faceState = context.getWorld().getBlockState(context.getPos().offset(placedOnDir));
        return canConnect(faceState.getBlock()) ? connected.with(SixWayBlock.FACING_TO_PROPERTY_MAP.get(placedOnDir), true) : null;
    }

    /*public BlockState makeConnections(IBlockReader blockReader, BlockPos pos) {
        Block block = blockReader.getBlockState(pos.down()).getBlock();
        Block block1 = blockReader.getBlockState(pos.up()).getBlock();
        Block block2 = blockReader.getBlockState(pos.north()).getBlock();
        Block block3 = blockReader.getBlockState(pos.east()).getBlock();
        Block block4 = blockReader.getBlockState(pos.south()).getBlock();
        Block block5 = blockReader.getBlockState(pos.west()).getBlock();
        return this.getDefaultState().with(DOWN, block == this || block.getDefaultState().isSolid()).with(UP, block1 == this).with(NORTH, block2 == this).with(EAST, block3 == this).with(SOUTH, block4 == this).with(WEST, block5 == this);
    }*/

    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent growing cactus from loading unloaded chunks with block update
        if (!state.isValidPosition(worldIn, pos)) {
            worldIn.destroyBlock(pos, true);
        }

    }

    /**
     * Performs a random tick on a block.
     */

    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return COLLISION_SHAPE;
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        BlockState newState = (stateIn.get(FACING_TO_PROPERTY_MAP.get(facing)) && !this.canConnect(facingState.getBlock())) ? stateIn : stateIn.with(FACING_TO_PROPERTY_MAP.get(facing), this.canConnect(facingState.getBlock()));
        if (!newState.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
            //worldIn.destroyBlock(currentPos, true);
        }

        return newState;
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        boolean flag = false;
        for (Direction dir : Direction.values()) {
            if (dir != Direction.UP && state.get(SixWayBlock.FACING_TO_PROPERTY_MAP.get(dir))) {
                Block block = worldIn.getBlockState(pos.offset(dir)).getBlock();
                if (canConnect(block)) flag = true;
            }
        }
        return flag;
    }

    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.attackEntityFrom(DamageSource.CACTUS, 1.0F);
        if (entityIn instanceof LivingEntity) {
            LivingEntity living = (LivingEntity) entityIn;
            living.addPotionEffect(new EffectInstance(BetterBadlandsEffects.SUCCUMBING.get(), 1200, 0, false, true, true));
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN));
    }

    public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return false;
    }

    @Override
    public net.minecraftforge.common.PlantType getPlantType(IBlockReader world, BlockPos pos) {
        return net.minecraftforge.common.PlantType.DESERT;
    }

    @Override
    public BlockState getPlant(IBlockReader world, BlockPos pos) {
        return getDefaultState();
    }

    @Nullable
    @Override
    public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, @Nullable MobEntity entity) {
        return PathNodeType.DAMAGE_CACTUS;
    }
}
