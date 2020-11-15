package com.teamaurora.better_badlands.datagen.providers;

import net.minecraft.block.Block;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

import static com.teamaurora.better_badlands.core.registry.BetterBadlandsBlocks.*;

public class ModBlockLootTableProvider extends BlockLootTables {

    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void registerLootTable(Block block, LootTable.Builder table) {
        super.registerLootTable(block, table);
        this.knownBlocks.add(block);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        // Overriding so we don't iterate though all the vanilla blocks
        return this.knownBlocks;
    }

    /**
     * Register loot tables here.
     */
    @Override
    protected void addTables() {
        // There are a lot of available methods for registering loot tables.
        // Look in BlockLootTables for references.
        this.registerDropSelfLootTable(KINDLING.get());
        this.registerDropSelfLootTable(KINDLING_SLAB.get());
        this.registerDropSelfLootTable(KINDLING_STAIRS.get());
        this.registerDropSelfLootTable(KINDLING_VERTICAL_SLAB.get());

        this.registerDropSelfLootTable(TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(BLACK_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(BLUE_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(BROWN_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(CYAN_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(GRAY_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(GREEN_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(LIGHT_BLUE_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(LIGHT_GRAY_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(LIME_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(MAGENTA_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(ORANGE_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(PINK_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(PURPLE_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(RED_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(WHITE_TERRACOTTA_LAMP.get());
        this.registerDropSelfLootTable(YELLOW_TERRACOTTA_LAMP.get());

        // Parse a new LootTable.Builder instance for empty loot table
        this.registerLootTable(TERRACOTTA_LAMP_LIGHT_SOURCE.get(), new LootTable.Builder());
    }
}
