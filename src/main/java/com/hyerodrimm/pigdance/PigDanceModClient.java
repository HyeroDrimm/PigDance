package com.hyerodrimm.pigdance;

import net.fabricmc.api.ClientModInitializer;

public class PigDanceModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PigDanceMod.LOGGER.info("Initialize client for " + PigDanceMod.MOD_ID);
    }
}
