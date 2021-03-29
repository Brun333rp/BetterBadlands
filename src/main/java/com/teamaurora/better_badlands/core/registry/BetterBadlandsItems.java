package com.teamaurora.better_badlands.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.better_badlands.common.item.SaguaroFlowerItem;
import com.teamaurora.better_badlands.core.BetterBadlands;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterBadlands.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterBadlandsItems {
    public static final ItemSubRegistryHelper HELPER = BetterBadlands.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> SAGUARO_FLOWER = HELPER.createItem("saguaro_flower", ()->new SaguaroFlowerItem(new Item.Properties().group(ItemGroup.MISC)));
}
