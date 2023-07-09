package org.sgx.mojnovymod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ArrowEntity.class)
public class MojNovyMixin {
    @Inject(at = @At("HEAD"), method = "onHit")
    private void explodeOnHit(LivingEntity target, CallbackInfo info) {
        target.getWorld().createExplosion(target, target.getX(), target.getY(), target.getZ(), new Random().nextFloat(3.5f, 10.0f), World.ExplosionSourceType.TNT);
    }
}