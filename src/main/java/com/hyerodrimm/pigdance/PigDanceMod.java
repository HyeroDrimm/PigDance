package com.hyerodrimm.pigdance;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PigDanceMod implements ModInitializer {
	public static final String MOD_ID = "pigdance";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PigDanceMod.LOGGER.info("Initialize for " + PigDanceMod.MOD_ID);
	}
}