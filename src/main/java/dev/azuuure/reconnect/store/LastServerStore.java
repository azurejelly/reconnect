package dev.azuuure.reconnect.store;

import com.google.common.base.Preconditions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LastServerStore {

    @Nullable
    private ServerInfo info;

    /**
     * Builds a store with the specified {@link ServerInfo}.
     *
     * @param info A {@link ServerInfo} parameter, which could be null.
     */
    public LastServerStore(@Nullable ServerInfo info) {
        this.info = info;
    }

    /**
     * Builds a store with no {@link ServerInfo}.
     */
    public LastServerStore() {
        this.info = null;
    }

    /**
     * Returns the {@link ServerInfo} for the last server the
     * client has connected to, which contains the address, name,
     * version, among other properties.
     * <p>
     * This method can return null if, for example, the client
     * has just started and has not connected to a server just yet.
     *
     * @return A {@link ServerInfo} instance or null.
     */
    @Nullable
    public ServerInfo info() {
        return info;
    }

    /**
     * Returns the raw server address. If no {@link ServerInfo}
     * is available, this method will return null.
     *
     * @return The raw server address or null.
     */
    @Nullable
    public String address() {
        if (info == null) {
            return null;
        }

        return info.address;
    }

    /**
     * Updates the last known server's information.
     *
     * @param info A {@link ServerInfo} instance or null.
     */
    public void update(@Nullable ServerInfo info) {
        this.info = info;
    }

    /**
     * Whether the client can reconnect to the last server,
     * which is determined by whether the store contains any
     * {@link ServerInfo server information}.
     *
     * @return true if the client can reconnect to any server
     */
    public boolean canReconnect() {
        return info != null;
    }

    /**
     * Reconnects the client to the last known server
     *
     * @param parent The parent {@link Screen} (e.g. the Server List screen). Cannot be null.
     * @param client The {@link MinecraftClient Minecraft client}. Cannot be null.
     */
    public void reconnect(@NotNull Screen parent, @NotNull MinecraftClient client) {
        Preconditions.checkNotNull(parent);
        Preconditions.checkNotNull(client);

        if (info == null) {
            return;
        }

        ServerAddress address = ServerAddress.parse(address());
        ConnectScreen.connect(parent, client, address, info, false, null);
    }
}
