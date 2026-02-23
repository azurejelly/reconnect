package dev.azuuure.reconnect.store;

import com.google.common.base.Preconditions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ServerDataStore {

    @Nullable
    private ServerData data;

    /**
     * Builds a store with the specified {@link ServerData}.
     *
     * @param data A {@link ServerData} parameter, which could be null.
     */
    public ServerDataStore(@Nullable ServerData data) {
        this.data = data;
    }

    /**
     * Builds a store with no {@link ServerData}.
     */
    public ServerDataStore() {
        this.data = null;
    }

    /**
     * Returns the {@link ServerData} for the last server the
     * client has connected to, which contains the address, name,
     * version, among other properties.
     * <p>
     * This method can return null if, for example, the client
     * has just started and has not connected to a server just yet.
     *
     * @return a {@link ServerData} instance or null.
     */
    @Nullable
    public ServerData data() {
        return data;
    }

    /**
     * Returns the raw server address. If no {@link ServerData}
     * is available, this method will return null.
     *
     * @return the raw server address or null.
     */
    @Nullable
    public String address() {
        if (data == null) {
            return null;
        }

        return data.ip;
    }

    /**
     * Updates the last known server's data.
     *
     * @param data a {@link ServerData} instance or null.
     */
    public void update(@Nullable ServerData data) {
        this.data = data;
    }

    /**
     * Whether the client can reconnect to the last server,
     * which is determined by whether the store contains any
     * {@link ServerData server data}.
     *
     * @return true if the client can reconnect to a server
     */
    public boolean canReconnect() {
        return data != null;
    }

    /**
     * Reconnects the client to the last known server
     *
     * @param parent the parent {@link Screen} (e.g. the Server List screen). Cannot be null.
     * @param minecraft a {@link Minecraft} instance. Cannot be null.
     */
    public void reconnect(@NotNull Screen parent, @NotNull Minecraft minecraft) {
        Preconditions.checkNotNull(parent);
        Preconditions.checkNotNull(minecraft);

        if (data == null) {
            return;
        }

        ServerAddress address = ServerAddress.parseString(data.ip);
        ConnectScreen.startConnecting(parent, minecraft, address, data, false, null);
    }
}
