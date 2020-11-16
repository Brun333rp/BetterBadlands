package com.teamaurora.better_badlands.core.registry;

import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class BetterBadlandsParticles {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, BetterBadlands.MODID);

    public static final RegistryObject<BasicParticleType> TWIG = PARTICLES.register("twig", ()-> new BasicParticleType(false));
}
