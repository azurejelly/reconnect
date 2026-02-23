package dev.azuuure.reconnect;

import dev.azuuure.reconnect.store.ServerDataStore;
import org.slf4j.Logger;

public interface ReconnectMod {

    void init();

    Logger getLogger();

    ServerDataStore getStore();

    String getVersion();

    String getPlatform();
}
