package dev.faceless.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class ParticleOutline {
    private final Plugin plugin;
    private final Block block;
    private final Particle particle;
    private final double spacing;
    private final double distance;
    private BukkitTask task;

    public ParticleOutline(Plugin plugin, Block block, Particle particle, double spacing, double distance) {
        this.plugin = plugin;
        this.block = block;
        this.particle = particle;
        this.spacing = spacing;
        this.distance = distance;
    }

    public void start(long intervalTicks) {
        if (task != null) return;

        task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Location loc : getOutlinePoints()) {
                block.getWorld().spawnParticle(particle, loc, 1, 0, 0, 0, 0);
            }
        }, 0L, intervalTicks);
    }

    public void stop() {
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    public boolean isRunning() {
        return task != null;
    }

    private List<Location> getOutlinePoints() {
        List<Location> points = new ArrayList<>();
        Location base = block.getLocation().add(0.5, 0.5, 0.5);
        double half = distance / 2.0;

        for (double x = -half; x <= half; x += spacing) {
            for (double y = -half; y <= half; y += spacing) {
                for (double z = -half; z <= half; z += spacing) {
                    int count = 0;
                    if (Math.abs(x + half) < 1e-3 || Math.abs(x - half) < 1e-3) count++;
                    if (Math.abs(y + half) < 1e-3 || Math.abs(y - half) < 1e-3) count++;
                    if (Math.abs(z + half) < 1e-3 || Math.abs(z - half) < 1e-3) count++;
                    if (count >= 2) { // Only outline
                        points.add(base.clone().add(x, y, z));
                    }
                }
            }
        }
        return points;
    }
}
