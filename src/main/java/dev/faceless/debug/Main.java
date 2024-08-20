package dev.faceless.debug;

import dev.faceless.PluginUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginUtils.onEnable(this);
    }

    @Override
    public void onDisable() {
        PluginUtils.onDisable();
    }
}
