package org.sgx.mojnovymod.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.sgx.mojnovymod.nbt.IPlayerEntityExt;
import org.sgx.mojnovymod.util.ModStats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class ModPlayerEntityMixin implements IPlayerEntityExt {
    @Unique public int ominRadiateValue = 0;

    @Inject(method="writeCustomDataToNbt", at=@At("RETURN"))
    public void writeCustomDataToNbtInject(NbtCompound nbt, CallbackInfo callbackInfo) {
        nbt.putInt(ModStats.OMIN_RADIATE, ominRadiateValue);
    }
    @Inject(method="readCustomDataFromNbt", at=@At("RETURN"))
    public void readCustomDataFromNbtInject(NbtCompound nbt, CallbackInfo callbackInfo) {
        ominRadiateValue = nbt.getInt(ModStats.OMIN_RADIATE);
    }

    @Override
    public void mojNovyMod$setStats(String id, int value) {
        if(id.equals(ModStats.OMIN_RADIATE)) ominRadiateValue = Math.min(Math.max(value, 0), ModStats.OMIN_RADIATE_MAX);
    }
    @Override
    public int mojNovyMod$getStats(String id) {
        if(id.equals(ModStats.OMIN_RADIATE)) return ominRadiateValue;
        return 0;
    }
}