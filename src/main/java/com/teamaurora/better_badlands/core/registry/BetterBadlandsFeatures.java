package com.teamaurora.better_badlands.core.registry;

import com.google.common.collect.ImmutableSet;
import com.teamaurora.better_badlands.common.world.biome.BetterBadlandsBiomeFeatures;
import com.teamaurora.better_badlands.common.world.gen.feature.SmallDarkOakFeature;
import com.teamaurora.better_badlands.common.world.gen.foliageplacer.SmallDarkOakFoliagePlacer;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = BetterBadlands.MODID)
public class BetterBadlandsFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BetterBadlands.MODID);
    //public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, BetterBadlands.MODID);
    //public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(ForgeRegistries.)

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SMALL_DARK_OAK_TREE = FEATURES.register("small_dark_oak_tree", ()->new SmallDarkOakFeature(BaseTreeFeatureConfig.CODEC));

    //public static final RegistryObject<FoliagePlacerType<SmallDarkOakFoliagePlacer>> SMALL_DARK_OAK_FOL = FOLIAGE_PLACER_TYPES.register("small_dark_oak_foliage_placer", ()->new FoliagePlacerType<SmallDarkOakFoliagePlacer>(SmallDarkOakFoliagePlacer.field_236745_a_));


    public static void generateFeatures() {
        //ForgeRegistries.BIOMES.getValues().forEach(BetterBadlandsFeatures::generate);
    }

    // Code originally by bageldotjpg, modified by Epic312
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        List<Supplier<ConfiguredFeature<?, ?>>> features = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

        if (event.getName() != null && (ImmutableSet.of(
                Biomes.WOODED_BADLANDS_PLATEAU.getLocation(),
                Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU.getLocation()
        ).contains(event.getName()))) {
            List<Supplier<ConfiguredFeature<?, ?>>> toRemove = new ArrayList<>();
            for (Supplier<ConfiguredFeature<?, ?>> configuredFeatureSupplier : features) {
                IFeatureConfig config = configuredFeatureSupplier.get().config;
                if (config instanceof DecoratedFeatureConfig) {
                    ConfiguredFeature<?, ?> configuredFeature = ((DecoratedFeatureConfig) config).feature.get();
                    if (configuredFeature.feature == Feature.TREE) {
                        if (configuredFeature.config instanceof BaseTreeFeatureConfig) {
                            //if (((BaseTreeFeatureConfig) configuredFeature.config).trunkProvider.getBlockState(new Random(), new BlockPos(0, 0, 0)).getBlock() == Blocks.OAK_LOG) {
                                toRemove.add(configuredFeatureSupplier);
                            //}
                        }
                    }
                }
            }
            toRemove.forEach(features::remove);
            event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, SMALL_DARK_OAK_TREE.get().withConfiguration(BetterBadlandsBiomeFeatures.SMALL_DARK_OAK_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(5, 0.1F, 1))));
        }
        // For some unknown reason this isn't removing the oak trees. But it looks better with a mixture of the two anyway so we're keeping it
    }
}
