package dev.azuuure.reconnect.mixin;

import dev.azuuure.reconnect.ReconnectMod;
import dev.azuuure.reconnect.store.LastServerStore;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.CookieStorage;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin extends ScreenMixin {

    /**
     * Updates the {@link LastServerStore} with updated {@link ServerInfo server information}
     * when the client connects to a server.
     *
     * @param screen Parent {@link Screen}.
     * @param client The {@link MinecraftClient Minecraft client}.
     * @param address The {@link ServerAddress server address} the client is connecting to.
     * @param info The {@link ServerInfo server information}.
     * @param quickPlay Quick play. I have no fucking idea what that is.
     * @param cookieStorage Cookie storage, used for Realms from what I understand.
     * @param ci Mixin callback information
     */
    @Inject(
            method = "connect(Lnet/minecraft/client/gui/screen/Screen;Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;Lnet/minecraft/client/network/ServerInfo;ZLnet/minecraft/client/network/CookieStorage;)V",
            at = @At("HEAD")
    )
    private static void storeLastServer(Screen screen, MinecraftClient client, ServerAddress address, ServerInfo info, boolean quickPlay, CookieStorage cookieStorage, CallbackInfo ci) {
        ReconnectMod.getInstance().getLogger().info("Updating last known server...");
        ReconnectMod.getInstance().getStore().update(info);
    }
}
