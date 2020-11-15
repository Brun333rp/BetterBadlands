package com.teamaurora.better_badlands.core.registry;

import com.teamabnormals.abnormals_core.core.utils.RegistryHelper;
import com.teamaurora.better_badlands.common.particle.TwigParticle;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
@Mod.EventBusSubscriber(modid=BetterBadlands.MODID)
public class BetterBadlandsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BetterBadlands.MODID);
    public static final RegistryObject<BasicParticleType> TWIG = PARTICLES.register("twig", ()->new BasicParticleType(false));
    @SubscribeEvent
    public static void registerParticles(ParticleFactoryRegisterEvent event) {
        ParticleManager particleManagerIn = Minecraft.getInstance().particles;
        particleManagerIn.registerFactory(TWIG.get(), TwigParticle.Factory::new);
    }
}
