package dev.azuuure.reconnect.mixin;

import dev.azuuure.reconnect.ReconnectMod;
import dev.azuuure.reconnect.store.LastServerStore;
import dev.azuuure.reconnect.utils.Constants;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public abstract class DisconnectedScreenMixin extends ScreenMixin {

    @Shadow @Final
    private DirectionalLayoutWidget grid;

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
                    target = "Lnet/minecraft/client/gui/widget/DirectionalLayoutWidget;refreshPositions()V"
            )
    )
    public void addReconnectButton(CallbackInfo ci) {
        ReconnectMod mod = ReconnectMod.getInstance();
        LastServerStore store = mod.getStore();

        if (grid == null) {
            mod.getLogger().error("Cannot manipulate screen grid. Reconnect button will not be visible.");
            mod.getLogger().error(Constants.PLEASE_REPORT);
            return;
        }

        ButtonWidget widget = ButtonWidget
                .builder(
                        Text.translatable("reconnect.button"),
                        (btn) -> store.reconnect(parent, client)
                ).width(200)
                .build();

        if (!store.canReconnect()) {
            mod.getLogger().warn("Reconnect button grayed out, the mod does not remember which server the client was connected to!");
            mod.getLogger().warn(Constants.PLEASE_REPORT);

            widget.active = false;
            widget.setTooltip(
                    Tooltip.of(
                            Text.translatable("reconnect.tooltip.fail")
                    )
            );
        }

        grid.add(widget);
    }
}
