package com.teamaurora.better_badlands.common.block;

import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsSaplingBlock;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class SaguaroSproutBlock extends AbnormalsSaplingBlock {
    public SaguaroSproutBlock(Tree tree, Properties properties) {
        super(tree, properties);
    }

    public ItemStack getItem(IBlockReader worldIn, BlockPos pos, BlockState state) {
        return new ItemStack(BetterBadlandsItems.SAGUARO_FLOWER.get());
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        Block below = worldIn.getBlockState(pos.down()).getBlock();
        return below.isIn(BlockTags.SAND);
    }
}
