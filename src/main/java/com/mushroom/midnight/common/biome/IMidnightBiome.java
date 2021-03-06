package com.mushroom.midnight.common.biome;

import net.minecraft.world.biome.Biome;

public interface IMidnightBiome {
    float getRidgeWeight();

    float getDensityScale();

    static float getRidgeWeight(Biome biome) {
        return biome instanceof IMidnightBiome ? ((IMidnightBiome) biome).getRidgeWeight() : 0.0F;
    }

    static float getDensityScale(Biome biome) {
        return biome instanceof IMidnightBiome ? ((IMidnightBiome) biome).getDensityScale() : 1.0F;
    }
}
