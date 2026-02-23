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
public abstract class ConnectScreenMixin {

    @Inject(
            method = "startConnecting",
            at = @At("HEAD")
    )
    private static void storeLastServer(
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
