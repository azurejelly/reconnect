package dev.azuuure.reconnect;

import dev.azuuure.reconnect.store.ServerDataStore;
import net.fabricmc.api.ClientModInitializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReconnectMod implements ClientModInitializer {

    private static ReconnectMod instance;
    private Logger logger;
    private ServerDataStore store;

    /**
     * Called when the client is initialized. Sets up the
     * logger, store and instance field.
     */
    @Override
    public void onInitializeClient() {
        instance = this;
        logger = LoggerFactory.getLogger(ReconnectMod.class);
        store = new ServerDataStore();
    }

    /**
     * Returns the {@link Logger SLF4J logger} used by the mod.
     * @return a {@link Logger SLF4J logger}.
     */
    @NotNull
    public Logger getLogger() {
        return logger;
    }

    /**
     * Returns an instance of the main {@link ReconnectMod} class.
     *
     * @return a {@link ReconnectMod} instance.
     */
    public static ReconnectMod getInstance() {
        return instance;
    }

    /**
     * Returns an instance of {@link ServerDataStore}, which stores
     * the last server the client has connected to.
     *
     * @return an instance of {@link ServerDataStore}
     */
    @NotNull
    public ServerDataStore getStore() {
        return store;
    }
}
