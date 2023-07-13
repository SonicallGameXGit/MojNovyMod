package org.sgx.mojnovymod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.sgx.mojnovymod.block.ModBlocks;
import org.sgx.mojnovymod.network.ModNetwork;

public class MojNovyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEA_CROP, RenderLayer.getCutout());
        ModNetwork.register();
    }
}