package org.sgx.mojnovymod.gui.hud.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.sgx.mojnovymod.MojNovyMod;
import org.sgx.mojnovymod.nbt.IPlayerEntityExt;
import org.sgx.mojnovymod.util.ModStats;

public class StatsHudBar implements HudRenderCallback {
    private final Identifier emptySprite;
    private final Identifier fullSprite;

    private final int offset;

    private final boolean rightSide;
    private final boolean dynamicAlign;

    private final String stats;

    public StatsHudBar(String stats, String emptySprite, String fullSprite, int offset, boolean rightSide, boolean dynamicAlign) {
        this.stats = stats;
        this.emptySprite = new Identifier(MojNovyMod.MOD_ID, "textures/hud/" + emptySprite + ".png");
        this.fullSprite = new Identifier(MojNovyMod.MOD_ID, "textures/hud/" + fullSprite + ".png");
        this.offset = offset;
        this.rightSide = rightSide;
        this.dynamicAlign = dynamicAlign;
    }

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = client.player;

        if(player != null && !player.isCreative() && !player.isSpectator()) {
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

            for(int i = 0; i < ModStats.getMaxStatsValue(stats); i++) {
                RenderSystem.setShaderTexture(0, ((IPlayerEntityExt) player).mojNovyMod$getStats(stats) > i ? fullSprite : emptySprite);
                DrawableHelper.drawTexture(
                        matrixStack,
                        client.getWindow().getScaledWidth() / 2 - 91 + i * 8 + (rightSide ? 101 : 0),
                        client.getWindow().getScaledHeight() - (dynamicAlign && !rightSide ? (player.getArmor() == 0 ? 40 : 50) : 40) - 9 - offset * 10,
                        0.0f, 0.0f,
                        9, 9,
                        9, 9
                );
            }
        }
    }
}
