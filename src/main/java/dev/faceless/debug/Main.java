package dev.faceless.debug;

import dev.faceless.PluginUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginUtils.init(this);

        PluginUtils.enableChatModifier(true);
        PluginUtils.enableInputListener(true);

        PluginUtils.enableMenuApi();
        PluginUtils.enablePacketInjector();

        PluginUtils.enablePackHosting();
    }
}
