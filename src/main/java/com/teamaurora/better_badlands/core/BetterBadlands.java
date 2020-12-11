package com.teamaurora.better_badlands.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.teamaurora.better_badlands.core.other.BetterBadlandsData;
import com.teamaurora.better_badlands.core.other.BetterBadlandsEvents;
import com.teamaurora.better_badlands.client.ClientRegister;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsFeatures;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsParticles;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.teamaurora.better_badlands.core.BetterBadlands.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MODID)
@SuppressWarnings("deprecation")
public class BetterBadlands
{
    public static final String MODID = "better_badlands";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public BetterBadlands() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.register(eventBus);

        BetterBadlandsFeatures.FEATURES.register(eventBus);
        BetterBadlandsParticles.PARTICLES.register(eventBus);

        eventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new BetterBadlandsEvents());
        DeferredWorkQueue.runLater(() -> {
            BetterBadlandsData.registerFlammables();
            BetterBadlandsFeatures.generateFeatures();
        });
    }
}
