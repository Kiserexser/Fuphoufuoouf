package name.modid.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;
import name.modid.gui.ClickGuiScreen;

public class DusaruysClientClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyMapping keyBinding = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.clickgui.open",
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.clickgui"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (keyBinding.consumeClick()) {
                Screen currentScreen = client.screen;
                if (currentScreen instanceof ClickGuiScreen) {
                    client.setScreen(null);
                } else {
                    client.setScreen(new ClickGuiScreen());
                }
            }
        });
    }
}
