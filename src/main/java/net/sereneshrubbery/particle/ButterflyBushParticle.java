package net.sereneshrubbery.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
//? if <1.21.9 {
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
//?} else {
/*import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.texture.Sprite;
*///?}
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
//? if >=1.21 {
import net.minecraft.particle.SimpleParticleType;
//?} else {
/*import net.minecraft.particle.DefaultParticleType;
*///?}
//? if >=1.21.9 {
/*import net.minecraft.util.math.random.Random;
*///?}

@Environment(EnvType.CLIENT)
//? if <1.21.9 {
public class ButterflyBushParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;
    private final double startX;
    private final double startY;
    private final double startZ;

    protected ButterflyBushParticle(ClientWorld world, double x, double y, double z,
                                     double velocityX, double velocityY, double velocityZ,
                                     SpriteProvider spriteProvider) {
        super(world, x, y, z);
        this.spriteProvider = spriteProvider;
        this.startX = x;
        this.startY = y;
        this.startZ = z;

        this.maxAge = 80 + this.random.nextInt(40);
        this.scale = 0.15f + this.random.nextFloat() * 0.1f;
        this.gravityStrength = 0.0f;

        this.velocityX = velocityX + (this.random.nextDouble() - 0.5) * 0.02;
        this.velocityY = velocityY + this.random.nextDouble() * 0.02;
        this.velocityZ = velocityZ + (this.random.nextDouble() - 0.5) * 0.02;

        float[][] colors = {
            {1.0f, 0.3f, 0.3f},
            {1.0f, 0.6f, 0.2f},
            {1.0f, 1.0f, 0.3f},
            {0.5f, 1.0f, 0.3f},
            {0.3f, 1.0f, 0.5f},
            {0.3f, 1.0f, 1.0f},
            {0.3f, 0.6f, 1.0f},
            {0.6f, 0.3f, 1.0f},
            {1.0f, 0.3f, 1.0f},
            {1.0f, 0.5f, 0.7f},
        };
        int colorIndex = this.random.nextInt(colors.length);
        this.red = colors[colorIndex][0];
        this.green = colors[colorIndex][1];
        this.blue = colors[colorIndex][2];

        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;

        if (this.age++ >= this.maxAge) {
            this.markDead();
            return;
        }

        double time = this.age * 0.1;
        double flutter = Math.sin(time * 3) * 0.02;
        double drift = Math.cos(time * 2) * 0.01;

        this.velocityX += flutter;
        this.velocityZ += drift;

        this.velocityY = 0.01 + Math.sin(time) * 0.005;

        double distX = this.x - this.startX;
        double distZ = this.z - this.startZ;
        double maxDist = 2.0;

        if (Math.abs(distX) > maxDist) {
            this.velocityX -= Math.signum(distX) * 0.01;
        }
        if (Math.abs(distZ) > maxDist) {
            this.velocityZ -= Math.signum(distZ) * 0.01;
        }

        this.move(this.velocityX, this.velocityY, this.velocityZ);

        this.velocityX *= 0.98;
        this.velocityY *= 0.98;
        this.velocityZ *= 0.98;

        if (this.age > this.maxAge - 20) {
            this.alpha = (float)(this.maxAge - this.age) / 20.0f;
        }

        this.setSpriteForAge(this.spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    //? if >=1.21 {
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new ButterflyBushParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
    //?} else {
    /*public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ) {
            return new ButterflyBushParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider);
        }
    }
    *///?}
}
//?} else {
/*public class ButterflyBushParticle extends BillboardParticle {
    private final SpriteProvider spriteProvider;
    private final double startX;
    private final double startY;
    private final double startZ;
    private int age;
    private int maxAge;

    protected ButterflyBushParticle(ClientWorld world, double x, double y, double z,
                                     double velocityX, double velocityY, double velocityZ,
                                     SpriteProvider spriteProvider, Random random) {
        super(world, x, y, z, spriteProvider.getSprite(random));
        this.spriteProvider = spriteProvider;
        this.startX = x;
        this.startY = y;
        this.startZ = z;

        this.maxAge = 80 + random.nextInt(40);
        this.scale = 0.15f + random.nextFloat() * 0.1f;
        this.gravityStrength = 0.0f;

        this.velocityX = velocityX + (random.nextDouble() - 0.5) * 0.02;
        this.velocityY = velocityY + random.nextDouble() * 0.02;
        this.velocityZ = velocityZ + (random.nextDouble() - 0.5) * 0.02;

        float[][] colors = {
            {1.0f, 0.3f, 0.3f},   // Red
            {1.0f, 0.6f, 0.2f},   // Orange
            {1.0f, 1.0f, 0.3f},   // Yellow
            {0.5f, 1.0f, 0.3f},   // Lime
            {0.3f, 1.0f, 0.5f},   // Green
            {0.3f, 1.0f, 1.0f},   // Cyan
            {0.3f, 0.6f, 1.0f},   // Sky blue
            {0.6f, 0.3f, 1.0f},   // Purple
            {1.0f, 0.3f, 1.0f},   // Magenta
            {1.0f, 0.5f, 0.7f},   // Pink
        };
        int colorIndex = random.nextInt(colors.length);
        this.red = colors[colorIndex][0];
        this.green = colors[colorIndex][1];
        this.blue = colors[colorIndex][2];

        this.age = 0;
    }

    @Override
    public void tick() {
        this.lastX = this.x;
        this.lastY = this.y;
        this.lastZ = this.z;

        if (this.age++ >= this.maxAge) {
            this.markDead();
            return;
        }

        // Fluttering motion like a butterfly
        double time = this.age * 0.1;
        double flutter = Math.sin(time * 3) * 0.02;
        double drift = Math.cos(time * 2) * 0.01;

        this.velocityX += flutter;
        this.velocityZ += drift;

        // Slowly rise
        this.velocityY = 0.01 + Math.sin(time) * 0.005;

        // Don't go too far from start position
        double distX = this.x - this.startX;
        double distZ = this.z - this.startZ;
        double maxDist = 2.0;

        if (Math.abs(distX) > maxDist) {
            this.velocityX -= Math.signum(distX) * 0.01;
        }
        if (Math.abs(distZ) > maxDist) {
            this.velocityZ -= Math.signum(distZ) * 0.01;
        }

        // Move particle
        this.move(this.velocityX, this.velocityY, this.velocityZ);

        // Slow down over time
        this.velocityX *= 0.98;
        this.velocityY *= 0.98;
        this.velocityZ *= 0.98;

        // Fade out near end of life
        if (this.age > this.maxAge - 20) {
            this.alpha = (float)(this.maxAge - this.age) / 20.0f;
        }

        this.updateSprite(this.spriteProvider);
    }

    @Override
    protected BillboardParticle.RenderType getRenderType() {
        return BillboardParticle.RenderType.PARTICLE_ATLAS_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientWorld world,
                                       double x, double y, double z,
                                       double velocityX, double velocityY, double velocityZ,
                                       Random random) {
            return new ButterflyBushParticle(world, x, y, z, velocityX, velocityY, velocityZ, this.spriteProvider, random);
        }
    }
}
*///?}
