package com.teamaurora.better_badlands.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsItems;

public class BetterBadlandsCompat {
    public static void registerCompostables() {
        DataUtil.registerCompostable(BetterBadlandsBlocks.SAGUARO_CACTUS.get(), 0.5F);
        DataUtil.registerCompostable(BetterBadlandsBlocks.SMALL_SAGUARO_CACTUS.get(), 0.5F);
        DataUtil.registerCompostable(BetterBadlandsItems.SAGUARO_FLOWER.get(), 0.5F);

    }
}
