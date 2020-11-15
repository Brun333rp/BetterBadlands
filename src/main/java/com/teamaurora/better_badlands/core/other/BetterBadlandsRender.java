package com.teamaurora.better_badlands.core.other;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class BetterBadlandsRender {
    public static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING_SLAB.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING_STAIRS.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING_VERTICAL_SLAB.get(), RenderType.getCutoutMipped());
    }
}
