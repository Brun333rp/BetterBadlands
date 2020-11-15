package com.teamaurora.better_badlands.common.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TwigParticle extends SpriteTexturedParticle {
    protected TwigParticle(ClientWorld p_i232447_1_, double p_i232447_2_, double p_i232447_4_, double p_i232447_6_) {
        super(p_i232447_1_, p_i232447_2_, p_i232447_4_, p_i232447_6_);
        this.motionX *= (double)0.8F;
        this.motionY *= (double)0.8F;
        this.motionZ *= (double)0.8F;
        this.motionY = (double)(this.rand.nextFloat() * 0.4F + 0.05F);
        this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
        this.maxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
    }

    protected TwigParticle(ClientWorld p_i232448_1_, double p_i232448_2_, double p_i232448_4_, double p_i232448_6_, double p_i232448_8_, double p_i232448_10_, double p_i232448_12_) {
        super(p_i232448_1_, p_i232448_2_, p_i232448_4_, p_i232448_6_, p_i232448_8_, p_i232448_10_, p_i232448_12_);
    }

    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getBrightnessForRender(float partialTick) {
        int i = super.getBrightnessForRender(partialTick);
        int j = 240;
        int k = i >> 16 & 255;
        return 240 | k << 16;
    }

    public float getScale(float scaleFactor) {
        //float f = ((float)this.age + scaleFactor) / (float)this.maxAge;
        return this.particleScale;
    }

    public void tick() {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        float f = (float)this.age / (float)this.maxAge;
        if (0.75 < f && (rand.nextInt(15)==0)) {
            this.world.addParticle(ParticleTypes.SMOKE, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.motionY -= 0.03D;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)0.999F;
            this.motionY *= (double)0.999F;
            this.motionZ *= (double)0.999F;
            if (this.onGround) {
                this.motionX *= (double)0.7F;
                this.motionZ *= (double)0.7F;
            }

        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite p_i50495_1_) {
            this.spriteSet = p_i50495_1_;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            TwigParticle twigparticle = new TwigParticle(worldIn, x, y, z);
            twigparticle.selectSpriteRandomly(this.spriteSet);
            return twigparticle;
        }
    }
}
