package com.teamaurora.better_badlands.client;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientRegister {

    public static void registerClient(final FMLClientSetupEvent event) {
        setupRenderLayer();
    }

    private static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.DEAD_BUSH_THATCH.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.DEAD_BUSH_THATCH_SLAB.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.DEAD_BUSH_THATCH_STAIRS.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.DEAD_BUSH_THATCH_VERTICAL_SLAB.get(), RenderType.getCutoutMipped());
    }
}
