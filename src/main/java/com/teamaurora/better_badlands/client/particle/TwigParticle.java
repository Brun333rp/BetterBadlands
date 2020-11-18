package com.teamaurora.better_badlands.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class TwigParticle extends SpriteTexturedParticle {

    public TwigParticle(ClientWorld clientWorld, double x, double y, double z) {
        super(clientWorld, x, y, z);
        this.motionX *= 0.8F;
        this.motionY *= 0.8F;
        this.motionZ *= 0.8F;
        this.motionY = this.rand.nextFloat() * 0.15F + 0.05F;
        this.particleScale *= this.rand.nextFloat() * 2.0F + 0.2F;
        this.maxAge = (int)(16.0D / (Math.random() * 0.8D + 0.2D));
    }

    public TwigParticle(ClientWorld clientWorld, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(clientWorld, x, y, z, xSpeed, ySpeed, zSpeed);
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public int getBrightnessForRender(float partialTick) {
        int i = super.getBrightnessForRender(partialTick);
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

        if (0.75 < f && (rand.nextInt(15) ==0 )) {
            this.world.addParticle(ParticleTypes.SMOKE, this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ);
        }

        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.motionY -= 0.03D;
            this.move(this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.999F;
            this.motionY *= 0.999F;
            this.motionZ *= 0.999F;
            if (this.onGround) {
                this.motionX *= 0.7F;
                this.motionZ *= 0.7F;
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite animatedSprite) {
            this.spriteSet = animatedSprite;
        }

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            TwigParticle twigparticle = new TwigParticle(worldIn, x, y, z);
            twigparticle.selectSpriteRandomly(this.spriteSet);

            return twigparticle;
        }
    }
}
