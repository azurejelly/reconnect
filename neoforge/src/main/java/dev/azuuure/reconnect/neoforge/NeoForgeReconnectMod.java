package dev.azuuure.reconnect.neoforge;

import dev.azuuure.reconnect.AbstractReconnectMod;
import dev.azuuure.reconnect.neoforge.util.NeoForgeUtils;
import dev.azuuure.reconnect.provider.ReconnectModProvider;
import dev.azuuure.reconnect.utils.SharedConstants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = SharedConstants.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = SharedConstants.MOD_ID, value = Dist.CLIENT)
public class NeoForgeReconnectMod extends AbstractReconnectMod {

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        ReconnectModProvider.getInstance().init();
    }

    @Override
    public String getVersion() {
        return NeoForgeUtils.getModVersion();
    }

    @Override
    public String getPlatform() {
        return NeoForgeUtils.PLATFORM;
    }
}
