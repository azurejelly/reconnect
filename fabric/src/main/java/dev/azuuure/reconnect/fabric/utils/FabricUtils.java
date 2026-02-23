package dev.azuuure.reconnect.fabric.utils;

import dev.azuuure.reconnect.utils.SharedConstants;
import lombok.experimental.UtilityClass;
import net.fabricmc.loader.api.FabricLoader;

@UtilityClass
public class FabricUtils {

    public static String PLATFORM = "Fabric";

    public String getVersion(String id) {
        return FabricLoader.getInstance()
                .getModContainer(id)
                .orElseThrow()
                .getMetadata()
                .getVersion()
                .getFriendlyString();
    }

    public String getVersion() {
        return getVersion(SharedConstants.MOD_ID);
    }
}
