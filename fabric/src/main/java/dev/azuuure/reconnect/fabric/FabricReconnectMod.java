package dev.azuuure.reconnect.fabric;

import dev.azuuure.reconnect.AbstractReconnectMod;
import dev.azuuure.reconnect.fabric.utils.FabricUtils;
import net.fabricmc.api.ClientModInitializer;

public class FabricReconnectMod extends AbstractReconnectMod implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        this.init();
    }

    @Override
    public String getVersion() {
        return FabricUtils.getVersion();
    }

    @Override
    public String getPlatform() {
        return FabricUtils.PLATFORM;
    }
}
