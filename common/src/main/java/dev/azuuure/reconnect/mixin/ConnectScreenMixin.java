package dev.azuuure.reconnect.mixin;

import dev.azuuure.reconnect.ReconnectMod;
import dev.azuuure.reconnect.provider.ReconnectModProvider;
import dev.azuuure.reconnect.store.ServerDataStore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.TransferState;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin extends ScreenMixin {

    /**
     * Updates the {@link ServerDataStore} with new {@link ServerData server data}
     * when the client connects to a server.
     *
     * @param screen the parent {@link Screen}.
     * @param minecraft a {@link Minecraft} instance.
     * @param serverAddress the {@link ServerAddress server address} the client is connecting to.
     * @param serverData the {@link ServerData server data}.
     * @param quickPlay quick play. I have no fucking idea what that is.
     * @param transferState a transfer state, used for Realms from what I understand.
     * @param ci mixin callback information
     */
    @Inject(
            method = "startConnecting",
            at = @At("HEAD")
    )
    private static void reconnect$storeLastServer(
            Screen screen,
            Minecraft minecraft,
            ServerAddress serverAddress,
            ServerData serverData,
            boolean quickPlay,
            TransferState transferState,
            CallbackInfo ci
    ) {
        ReconnectMod mod = ReconnectModProvider.getInstance();
        mod.getLogger().info("Updating last known server...");
        mod.getStore().update(serverData);
    }
}
