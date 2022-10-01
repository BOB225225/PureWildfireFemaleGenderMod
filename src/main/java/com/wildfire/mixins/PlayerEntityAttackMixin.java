package com.wildfire.mixins;


import com.wildfire.callback.PlayerEntityAttackCallback;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntity.class)
public class PlayerEntityAttackMixin {

    @Inject(method = "attack", at = @At(value = "HEAD", target = "Lnet/minecraft/entity/player/PlayerEntity;attack(Lnet/minecraft/entity/Entity;)V"), cancellable = true)
    protected void attack(Entity target, CallbackInfo ci) {

        ActionResult result = PlayerEntityAttackCallback.EVENT.invoker().interact(target);

        if(result == ActionResult.FAIL) {
            ci.cancel();
        }
    }

}
