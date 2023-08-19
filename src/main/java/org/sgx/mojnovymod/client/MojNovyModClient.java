package org.sgx.mojnovymod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.world.ClientWorld;
import org.sgx.mojnovymod.block.ModBlocks;
import org.sgx.mojnovymod.gui.hud.ModHud;
import org.sgx.mojnovymod.network.ModNetwork;

public class MojNovyModClient implements ClientModInitializer {
    public static ClientWorld lastClientWorld = null;

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEA_CROP, RenderLayer.getCutout());

        ModNetwork.register();
        ModHud.register();
    }
}