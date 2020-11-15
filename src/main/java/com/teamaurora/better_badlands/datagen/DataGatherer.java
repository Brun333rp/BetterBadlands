package com.teamaurora.better_badlands.datagen;

import com.teamaurora.better_badlands.core.BetterBadlands;
import com.teamaurora.better_badlands.datagen.providers.ModLootTableProvider;
import com.teamaurora.better_badlands.datagen.providers.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = BetterBadlands.MODID)
public class DataGatherer {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();

        dataGenerator.addProvider(new ModRecipeProvider(dataGenerator));
        dataGenerator.addProvider(new ModLootTableProvider(dataGenerator));
    }
}
