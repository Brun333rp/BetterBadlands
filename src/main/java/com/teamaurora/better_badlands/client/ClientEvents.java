package com.teamaurora.better_badlands.client;

import com.teamaurora.better_badlands.common.particle.TwigParticle;
import com.teamaurora.better_badlands.core.BetterBadlands;
import com.teamaurora.better_badlands.core.registry.BetterBadlandsParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = BetterBadlands.MODID)
public class ClientEvents {

    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        ParticleManager particleManagerIn = Minecraft.getInstance().particles;
        particleManagerIn.registerFactory(BetterBadlandsParticles.TWIG.get(), TwigParticle.Factory::new);
    }
}
