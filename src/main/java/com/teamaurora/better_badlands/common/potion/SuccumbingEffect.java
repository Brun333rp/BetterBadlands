package com.teamaurora.better_badlands.common.potion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;

public class SuccumbingEffect extends Effect {
    public SuccumbingEffect() {
        super(EffectType.HARMFUL, 5999141);
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
    }

    @Override
    public void affectEntity(Entity source, Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}
