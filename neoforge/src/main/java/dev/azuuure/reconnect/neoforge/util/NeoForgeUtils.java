package dev.azuuure.reconnect.neoforge.util;

import dev.azuuure.reconnect.utils.SharedConstants;
import lombok.experimental.UtilityClass;
import net.neoforged.fml.ModList;

@UtilityClass
public class NeoForgeUtils {

    public static final String PLATFORM = "NeoForge";

    public static String getModVersion(String id) {
        return ModList.get()
                .getModFileById(id)
                .versionString();
    }

    public static String getModVersion() {
        return getModVersion(SharedConstants.MOD_ID);
    }
}
