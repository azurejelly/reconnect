package dev.azuuure.reconnect.mixin;

import dev.azuuure.reconnect.ReconnectMod;
import dev.azuuure.reconnect.provider.ReconnectModProvider;
import dev.azuuure.reconnect.store.ServerDataStore;
import dev.azuuure.reconnect.utils.SharedConstants;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public abstract class DisconnectedScreenMixin extends ScreenMixin {

    @Shadow @Final
    private LinearLayout layout;

    @Shadow @Final
    private Screen parent;

    /**
     * Injects the Reconnect button into the Disconnected screen.
     *
     * @param ci Mixin callback information
     */
    @Inject(
            method = "init",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/layouts/LinearLayout;arrangeElements()V"
            )
    )
    public void reconnect$addReconnectButton(CallbackInfo ci) {
        ReconnectMod mod = ReconnectModProvider.getInstance();
        ServerDataStore store = mod.getStore();

        if (layout == null) {
            mod.getLogger().error("Cannot manipulate screen grid. Reconnect button will not be visible.");
            mod.getLogger().error(SharedConstants.PLEASE_REPORT);
            return;
        }

        Button widget = Button
                .builder(
                        Component.translatable("reconnect.button"),
                        (_) -> store.reconnect(parent, minecraft)
                ).width(200)
                .build();

        if (!store.canReconnect()) {
            mod.getLogger().warn("Reconnect button grayed out, the mod does not remember which server the client was connected to!");
            mod.getLogger().warn(SharedConstants.PLEASE_REPORT);

            widget.active = false;
            widget.setTooltip(
                    Tooltip.create(
                            Component.translatable("reconnect.tooltip.fail")
                    )
            );
        }

        layout.addChild(widget);
    }
}
