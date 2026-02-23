package dev.azuuure.reconnect;

import dev.azuuure.reconnect.provider.ReconnectModProvider;
import dev.azuuure.reconnect.store.ServerDataStore;
import dev.azuuure.reconnect.utils.SharedConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractReconnectMod implements ReconnectMod {

    private Logger logger;
    private ServerDataStore store;

    public AbstractReconnectMod() {
        ReconnectModProvider.setInstance(this);
    }

    @Override
    public final void init() {
        this.store = new ServerDataStore();
        this.logger = LoggerFactory.getLogger(SharedConstants.MOD_ID);
        this.logger.info("Running Reconnect version {} for {}", getVersion(), getPlatform());
        this.logger.info("Modrinth: https://modrinth.com/mod/reconnect-mod");

        this.onInit();
    }

    @Override
    public final Logger getLogger() {
        return logger;
    }

    @Override
    public final ServerDataStore getStore() {
        return store;
    }

    protected void onInit() {
        // noop by default, used for platform specific stuff
    }
}
