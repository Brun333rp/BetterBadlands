package com.teamaurora.better_badlands.common.block;

import com.teamabnormals.abnormals_core.common.blocks.AbnormalsStairsBlock;
import com.teamabnormals.abnormals_core.core.utils.ItemStackUtils;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.NonNullList;

public class TerracottaStairsBlock extends AbnormalsStairsBlock {
    public TerracottaStairsBlock(BlockState state, Properties properties) {
        super(state, properties);
    }

    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (ItemStackUtils.isInGroup(this.asItem(), group)) {
            int targetIndex = ItemStackUtils.findIndexOfItem(Items.DIORITE_STAIRS, items);
            if (targetIndex != -1) {
                items.add(targetIndex + 1, new ItemStack(this));
            }
            super.fillItemGroup(group, items);
        }
    }
}
