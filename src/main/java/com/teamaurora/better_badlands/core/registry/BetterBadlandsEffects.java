package com.teamaurora.better_badlands.core.registry;

import com.teamaurora.better_badlands.common.potion.SuccumbingEffect;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.item.Items;
import net.minecraft.potion.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterBadlands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterBadlandsEffects {
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, BetterBadlands.MODID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, BetterBadlands.MODID);

    public static RegistryObject<Effect> SUCCUMBING = EFFECTS.register("succumbing", SuccumbingEffect::new);

    public static final RegistryObject<Potion> SUCCUMBING_NORMAL = POTIONS.register("succumbing", ()->new Potion(new EffectInstance(SUCCUMBING.get(), 900)));
    public static final RegistryObject<Potion> SUCCUMBING_LONG = POTIONS.register("succumbing_long", ()->new Potion(new EffectInstance(SUCCUMBING.get(), 1800)));
    public static final RegistryObject<Potion> SUCCUMBING_STRONG = POTIONS.register("succumbing_strong", ()->new Potion(new EffectInstance(SUCCUMBING.get(), 432, 1)));

    public static void registerBrewingRecipes() {
        PotionBrewing.addMix(Potions.AWKWARD, BetterBadlandsItems.SAGUARO_FLOWER.get(), SUCCUMBING_NORMAL.get());
        PotionBrewing.addMix(SUCCUMBING_NORMAL.get(), Items.GLOWSTONE_DUST, SUCCUMBING_STRONG.get());
        PotionBrewing.addMix(SUCCUMBING_NORMAL.get(), Items.REDSTONE, SUCCUMBING_LONG.get());
    }
}
