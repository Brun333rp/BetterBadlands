package com.teamaurora.better_badlands.core.registry;

import com.teamaurora.better_badlands.common.world.biome.BetterBadlandsBiomeFeatures;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterBadlands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterBadlandsFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BetterBadlands.MODID);

    //public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SMALL_DARK_OAK_TREE = FEATURES.register("small_dark_oak_tree", ()->new SmallDarkOakFeature(BaseTreeFeatureConfig.field_236676_a_));

    public static void generateFeatures() {
        ForgeRegistries.BIOMES.getValues().forEach(BetterBadlandsFeatures::generate);
    }

    public static void generate(Biome biome) {
        /*if (biome.getRegistryName().toString().equals("minecraft:wooded_badlands_plateau") || biome.getRegistryName().toString().equals("minecraft:modified_wooded_badlands_plateau") || biome == Biomes.DARK_FOREST || biome == Biomes.DARK_FOREST_HILLS) {
            BetterBadlandsBiomeFeatures.replaceOakTrees(biome);
        }
        if (biome.getRegistryName().toString().equals("minecraft:wooded_badlands_plateau") || biome.getRegistryName().toString().equals("minecraft:modified_wooded_badlands_plateau")) {
            BetterBadlandsBiomeFeatures.addSparseDarkOakTrees(biome);
        }*/
        // TODO: worldgen shit
    }
}
