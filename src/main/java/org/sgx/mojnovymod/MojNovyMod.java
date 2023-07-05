package org.sgx.mojnovymod;

import net.fabricmc.api.ModInitializer;
import org.sgx.mojnovymod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MojNovyMod implements ModInitializer {
    public static final String MOD_ID = "mojnovymod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.register();
    }
}