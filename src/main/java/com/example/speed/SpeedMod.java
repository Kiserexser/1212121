package com.example.speed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import sweetie.evaware.client.ui.clickgui.ScreenClickGUI;

public class SpeedMod implements ModInitializer {
    private static boolean lastPressed = false;

    @Override
    public void onInitialize() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            long window = client.getWindow().getHandle();
            boolean pressed = GLFW.glfwGetKey(window, GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            if (pressed && !lastPressed) {
                if (client.currentScreen == null) {
                    client.setScreen(ScreenClickGUI.getInstance());
                } else if (client.currentScreen == ScreenClickGUI.getInstance()) {
                    client.setScreen(null);
                }
            }
            lastPressed = pressed;
        });
    }
}
