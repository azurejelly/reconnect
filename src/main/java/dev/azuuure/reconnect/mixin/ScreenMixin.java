package dev.azuuure.reconnect.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Screen.class)
public abstract class ScreenMixin {

    @Shadow
    @Final // while it isn't required on 1.21, 1.21.10+ (or maybe only 1.21.11?) does mark this field as final.
    protected MinecraftClient client;
}
