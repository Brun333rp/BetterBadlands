package com.teamaurora.better_badlands.client;

import com.teamaurora.better_badlands.core.BetterBadlands;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = BetterBadlands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {

    @SubscribeEvent
    public static void registerClient(final FMLClientSetupEvent event) {
        setupRenderLayer();
    }

    private static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING_SLAB.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING_STAIRS.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BetterBadlandsBlocks.KINDLING_VERTICAL_SLAB.get(), RenderType.getCutoutMipped());
    }
}
