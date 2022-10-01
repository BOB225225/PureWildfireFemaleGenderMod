package com.wildfire.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;

public interface PlayerEntityAttackCallback {
    Event<com.wildfire.callback.PlayerEntityAttackCallback> EVENT = EventFactory.createArrayBacked(PlayerEntityAttackCallback.class,
            (listeners) -> (target) -> {
                for (PlayerEntityAttackCallback listener : listeners) {
                    ActionResult result = listener.interact(target);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }
                return ActionResult.PASS;
            });

    ActionResult interact(Entity target);
}
