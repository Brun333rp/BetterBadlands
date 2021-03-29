package com.teamaurora.better_badlands.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.teamaurora.better_badlands.core.other.BetterBadlandsCompat;
import com.teamaurora.better_badlands.core.other.BetterBadlandsEvents;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsEffects;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsFeatures;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsParticles;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
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
        BetterBadlandsEffects.EFFECTS.register(eventBus);
        BetterBadlandsEffects.POTIONS.register(eventBus);

        eventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new BetterBadlandsEvents());
        event.enqueueWork(() -> {
            BetterBadlandsFeatures.Configured.registerConfiguredFeatures();
            BetterBadlandsCompat.registerCompostables();

            BetterBadlandsEffects.registerBrewingRecipes();
        });
    }
}
