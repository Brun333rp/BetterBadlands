package com.teamaurora.better_badlands.core.registry;

import com.google.common.collect.ImmutableSet;
import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.better_badlands.common.world.gen.feature.SaguaroCactusFeature;
import com.teamaurora.better_badlands.common.world.gen.feature.SmallDarkOakFeature;
import com.teamaurora.better_badlands.common.world.gen.foliageplacer.SmallDarkOakFoliagePlacer;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.FoliagePlacerType;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
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

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SMALL_DARK_OAK_TREE = FEATURES.register("small_dark_oak_tree", ()->new SmallDarkOakFeature(BaseTreeFeatureConfig.CODEC));

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> SAGUARO_CACTUS = FEATURES.register("saguaro_cactus", ()->new SaguaroCactusFeature(BaseTreeFeatureConfig.CODEC));

    public static final class BlockStates {
        public static final BlockState DARK_OAK_LOG = Blocks.DARK_OAK_LOG.getDefaultState();
        public static final BlockState DARK_OAK_LEAVES = Blocks.DARK_OAK_LEAVES.getDefaultState();
    }

    public static final class Configs {
        // Thanks to bageldotjpg for the dummy values here lol
        public static final BaseTreeFeatureConfig SMALL_DARK_OAK_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockStates.DARK_OAK_LOG), new SimpleBlockStateProvider(BlockStates.DARK_OAK_LEAVES), new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0), new StraightTrunkPlacer(0, 0, 0), new TwoLayerFeature(0, 0, 0))).setIgnoreVines().build();
    }

    public static final class Configured {
        public static final ConfiguredFeature<?, ?> SMALL_DARK_OAK_TREE = BetterBadlandsFeatures.SMALL_DARK_OAK_TREE.get().withConfiguration(Configs.SMALL_DARK_OAK_TREE_CONFIG).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(5, 0.1F, 1)));

        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SAGUARO_CACTUS = BetterBadlandsFeatures.SAGUARO_CACTUS.get().withConfiguration(Configs.SMALL_DARK_OAK_TREE_CONFIG); // this can use any config - it was originally a NoFeatureConfig but was changed to BaseTreeFeatureConfig to reuse sapling code
        public static final ConfiguredFeature<?, ?> SAGUARO_CACTUS_DECORATED = SAGUARO_CACTUS.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 3)));

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BetterBadlands.MODID, name), configuredFeature);
        }

        public static void registerConfiguredFeatures() {
            register("small_dark_oak_tree", SMALL_DARK_OAK_TREE);

            register("saguaro_cactus", SAGUARO_CACTUS);
            register("saguaro_cactus_decorated", SAGUARO_CACTUS_DECORATED);
        }
    }

    // Code originally by bageldotjpg, modified by Epic312
    @SubscribeEvent
    public static void onBiomeLoad(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();
        if (DataUtil.matchesKeys(biomeName, Biomes.WOODED_BADLANDS_PLATEAU, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU)) {
            List<Supplier<ConfiguredFeature<?, ?>>> features = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);

            if (event.getName() != null) {
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
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Configured.SMALL_DARK_OAK_TREE);
            }
        }
        // For some unknown reason this isn't removing the oak trees. But it looks better with a mixture of the two anyway so we're keeping it
        if (DataUtil.matchesKeys(biomeName, Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, Biomes.ERODED_BADLANDS, Biomes.MODIFIED_BADLANDS_PLATEAU, Biomes.MODIFIED_WOODED_BADLANDS_PLATEAU, Biomes.WOODED_BADLANDS_PLATEAU)) {
            List<Supplier<ConfiguredFeature<?, ?>>> features = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
            if (event.getName() != null) {
                List<Supplier<ConfiguredFeature<?, ?>>> toRemove = new ArrayList<>();
                List<ConfiguredFeature<?, ?>> toAdd = new ArrayList<>();
                for (Supplier<ConfiguredFeature<?, ?>> configuredFeatureSupplier : features) {
                    IFeatureConfig config = configuredFeatureSupplier.get().config;
                    if (config instanceof DecoratedFeatureConfig) {
                        ConfiguredFeature<?, ?> configuredFeature = ((DecoratedFeatureConfig) config).feature.get();
                        if (configuredFeature.config instanceof DecoratedFeatureConfig) {
                            ConfiguredFeature<?, ?> configuredFeature1 = ((DecoratedFeatureConfig) configuredFeature.config).feature.get();
                            if (configuredFeature1.config instanceof BlockClusterFeatureConfig) {
                                BlockClusterFeatureConfig bcConfig = (BlockClusterFeatureConfig) configuredFeature1.config;
                                if (bcConfig.stateProvider instanceof SimpleBlockStateProvider) {
                                    SimpleBlockStateProvider sbsProvider = (SimpleBlockStateProvider) bcConfig.stateProvider;
                                    if (sbsProvider.getBlockState(new Random(), new BlockPos(0, 0, 0)).getBlock() == Blocks.CACTUS) {
                                        toRemove.add(configuredFeatureSupplier);
                                    }
                                }
                            }
                        }
                    }
                }
                toRemove.forEach(features::remove);
                event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BetterBadlandsFeatures.Configured.SAGUARO_CACTUS_DECORATED);
            }
        }

    }
}
