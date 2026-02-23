package dev.azuuure.reconnect.provider;

import dev.azuuure.reconnect.ReconnectMod;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@UtilityClass
public class ReconnectModProvider {

    private static ReconnectMod instance;

    public ReconnectMod getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Attempted to obtain an instance before the mod was initialized");
        }

        return instance;
    }

    @ApiStatus.Internal
    public void setInstance(@NotNull ReconnectMod inst) {
        if (instance != null) {
            throw new IllegalStateException("Attempted to set an instance when it was already set");
        }

        instance = inst;
    }
}