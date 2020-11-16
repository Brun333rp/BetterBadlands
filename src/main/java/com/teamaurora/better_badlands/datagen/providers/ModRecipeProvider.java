package com.teamaurora.better_badlands.datagen.providers;

import com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.common.crafting.ConditionalRecipe;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks.*;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    /**
     * Registers all recipes to the given consumer.
     */
    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        this.terracottaLampRecipe(TERRACOTTA_LAMP, Blocks.TERRACOTTA, consumer);
        this.terracottaLampRecipe(BLACK_TERRACOTTA_LAMP, Blocks.BLACK_TERRACOTTA, consumer);
        this.terracottaLampRecipe(BLUE_TERRACOTTA_LAMP, Blocks.BLUE_TERRACOTTA, consumer);
        this.terracottaLampRecipe(BROWN_TERRACOTTA_LAMP, Blocks.BROWN_TERRACOTTA, consumer);
        this.terracottaLampRecipe(CYAN_TERRACOTTA_LAMP, Blocks.CYAN_TERRACOTTA, consumer);
        this.terracottaLampRecipe(GRAY_TERRACOTTA_LAMP, Blocks.GRAY_TERRACOTTA, consumer);
        this.terracottaLampRecipe(GREEN_TERRACOTTA_LAMP, Blocks.GREEN_TERRACOTTA, consumer);
        this.terracottaLampRecipe(LIGHT_BLUE_TERRACOTTA_LAMP, Blocks.LIGHT_BLUE_TERRACOTTA, consumer);
        this.terracottaLampRecipe(LIGHT_GRAY_TERRACOTTA_LAMP, Blocks.LIGHT_GRAY_TERRACOTTA, consumer);
        this.terracottaLampRecipe(LIME_TERRACOTTA_LAMP, Blocks.LIME_TERRACOTTA, consumer);
        this.terracottaLampRecipe(MAGENTA_TERRACOTTA_LAMP, Blocks.MAGENTA_TERRACOTTA, consumer);
        this.terracottaLampRecipe(ORANGE_TERRACOTTA_LAMP, Blocks.ORANGE_TERRACOTTA, consumer);
        this.terracottaLampRecipe(PINK_TERRACOTTA_LAMP, Blocks.PINK_TERRACOTTA, consumer);
        this.terracottaLampRecipe(PURPLE_TERRACOTTA_LAMP, Blocks.PURPLE_TERRACOTTA, consumer);
        this.terracottaLampRecipe(RED_TERRACOTTA_LAMP, Blocks.RED_TERRACOTTA, consumer);
        this.terracottaLampRecipe(WHITE_TERRACOTTA_LAMP, Blocks.WHITE_TERRACOTTA, consumer);
        this.terracottaLampRecipe(YELLOW_TERRACOTTA_LAMP, Blocks.YELLOW_TERRACOTTA, consumer);

        this.shapedRecipeWithCriterion(KINDLING.get(), Blocks.DEAD_BUSH, 1)
                .patternLine("##")
                .patternLine("##")
                .key('#', Blocks.DEAD_BUSH)
                .build(consumer);

        this.shapedRecipeWithCriterion(KINDLING_SLAB.get(), KINDLING.get(), 6)
                .patternLine("###")
                .key('#', KINDLING.get())
                .build(consumer);

        this.shapedRecipeWithCriterion(KINDLING_STAIRS.get(), KINDLING.get(), 4)
                .patternLine("  #")
                .patternLine(" ##")
                .patternLine("###")
                .key('#', KINDLING.get())
                .build(consumer);

        this.shapedRecipeWithCriterion(KINDLING_VERTICAL_SLAB.get(), KINDLING_SLAB.get(), 3)
                .patternLine("#")
                .patternLine("#")
                .patternLine("#")
                .key('#', KINDLING_SLAB.get())
                .build(consumer);
    }

    private ShapedRecipeBuilder shapedRecipeWithCriterion(IItemProvider result, IItemProvider criterionItem, int count) {
        String criterionName = Objects.requireNonNull(result.asItem().getRegistryName()).getPath();
        return new ShapedRecipeBuilder(result, count).addCriterion("has_" + criterionName, hasItem(criterionItem));
    }

    private void terracottaLampRecipe(Supplier<Block> result, IItemProvider terracottaType, Consumer<IFinishedRecipe> consumer) {
        this.shapedRecipeWithCriterion(result.get(), Blocks.LANTERN, 1)
                .setGroup("terracotta_lamp")
                .patternLine(" # ")
                .patternLine("#X#")
                .patternLine(" # ")
                .key('X', Blocks.LANTERN)
                .key('#', terracottaType)
                .build(consumer);
    }
}
