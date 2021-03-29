package com.teamaurora.better_badlands.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.teamaurora.better_badlands.common.block.*;
import com.teamaurora.better_badlands.common.block.trees.SaguaroCactusTree;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("deprecation")
@Mod.EventBusSubscriber(modid = BetterBadlands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterBadlandsBlocks {
    public static final BlockSubRegistryHelper HELPER = BetterBadlands.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> KINDLING = HELPER.createBlock("kindling", () -> new KindlingBlock(AbstractBlock.Properties.from(Blocks.HAY_BLOCK).notSolid().harvestTool(ToolType.HOE)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> KINDLING_SLAB = HELPER.createBlock("kindling_slab", () -> new KindlingSlabBlock(AbstractBlock.Properties.from(Blocks.HAY_BLOCK).notSolid().harvestTool(ToolType.HOE)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> KINDLING_STAIRS = HELPER.createBlock("kindling_stairs", () -> new KindlingStairsBlock(KINDLING.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.HAY_BLOCK).notSolid().harvestTool(ToolType.HOE)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> KINDLING_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "kindling_vertical_slab", () -> new KindlingVerticalSlabBlock(AbstractBlock.Properties.from(KINDLING.get()).notSolid()), ItemGroup.BUILDING_BLOCKS);

    // brother may i have L A M P
    public static final RegistryObject<Block> TERRACOTTA_LAMP_LIGHT_SOURCE = HELPER.createBlock("light_source", ()->new MagicInvisibleLightBlock(AbstractBlock.Properties.create(Material.AIR).setLightLevel((a)->{return 13;})), null);

    public static final RegistryObject<Block> TERRACOTTA_LAMP = HELPER.createBlock("terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> BLACK_TERRACOTTA_LAMP = HELPER.createBlock("black_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> RED_TERRACOTTA_LAMP = HELPER.createBlock("red_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> GREEN_TERRACOTTA_LAMP = HELPER.createBlock("green_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> BROWN_TERRACOTTA_LAMP = HELPER.createBlock("brown_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> BLUE_TERRACOTTA_LAMP = HELPER.createBlock("blue_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> PURPLE_TERRACOTTA_LAMP = HELPER.createBlock("purple_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> CYAN_TERRACOTTA_LAMP = HELPER.createBlock("cyan_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> LIGHT_GRAY_TERRACOTTA_LAMP = HELPER.createBlock("light_gray_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> GRAY_TERRACOTTA_LAMP = HELPER.createBlock("gray_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> PINK_TERRACOTTA_LAMP = HELPER.createBlock("pink_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> LIME_TERRACOTTA_LAMP = HELPER.createBlock("lime_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> YELLOW_TERRACOTTA_LAMP = HELPER.createBlock("yellow_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> LIGHT_BLUE_TERRACOTTA_LAMP = HELPER.createBlock("light_blue_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> MAGENTA_TERRACOTTA_LAMP = HELPER.createBlock("magenta_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> ORANGE_TERRACOTTA_LAMP = HELPER.createBlock("orange_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> WHITE_TERRACOTTA_LAMP = HELPER.createBlock("white_terracotta_lamp", ()->new TerracottaLampBlock(AbstractBlock.Properties.from(Blocks.TERRACOTTA)), ItemGroup.DECORATIONS);

    // saguaro cactus
    public static final RegistryObject<Block> SAGUARO_CACTUS = HELPER.createBlock("saguaro_cactus", ()->new SaguaroCactusBlock(Block.Properties.from(Blocks.CACTUS)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> SMALL_SAGUARO_CACTUS = HELPER.createBlock("small_saguaro_cactus", ()->new SmallSaguaroCactusBlock(AbstractBlock.Properties.from(Blocks.CACTUS)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> SAGUARO_SPROUT = HELPER.createBlockNoItem("saguaro_sprout", ()->new SaguaroSproutBlock(new SaguaroCactusTree(), AbstractBlock.Properties.from(Blocks.OAK_SAPLING).sound(SoundType.CLOTH)));
    public static final RegistryObject<Block> SAGUARO_FLOWER = HELPER.createBlockNoItem("saguaro_flower", ()->new SaguaroFlowerBlock(AbstractBlock.Properties.from(Blocks.OXEYE_DAISY)));
    public static final RegistryObject<Block> POTTED_SAGUARO_SPROUT = HELPER.createBlockNoItem("potted_saguaro_sprout", ()->new FlowerPotBlock(SAGUARO_SPROUT.get(), Block.Properties.from(Blocks.POTTED_ALLIUM)));


}
