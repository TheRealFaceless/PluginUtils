package dev.faceless.util;

import dev.faceless.PluginUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChestFillerUtils {
    private static final Set<Location> activeOutlines = ConcurrentHashMap.newKeySet();

    public static List<Container> findContainersAround(Player player, int radius) {
        World world = player.getWorld();
        int px = player.getLocation().getBlockX();
        int pz = player.getLocation().getBlockZ();
        int cx = px >> 4, cz = pz >> 4;
        int chunkRadius = (radius + 15) >> 4;
        int maxChunkDist = radius + 8;
        int maxChunkDistSq = maxChunkDist * maxChunkDist;
        int radiusSq = radius * radius;

        List<Container> containers = new ArrayList<>();

        for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
            int chunkCenterX = ((cx + dx) << 4) + 8;
            int deltaX = chunkCenterX - px;
            if (deltaX * deltaX > maxChunkDistSq) continue;

            for (int dz = -chunkRadius; dz <= chunkRadius; dz++) {
                int chunkCenterZ = ((cz + dz) << 4) + 8;
                int deltaZ = chunkCenterZ - pz;
                if (deltaZ * deltaZ > maxChunkDistSq) continue;

                Chunk chunk = world.getChunkAt(cx + dx, cz + dz);
                for (BlockState state : chunk.getTileEntities()) {
                    if (!(state instanceof Container container)) continue;
                    Block block = container.getBlock();
                    if (block.getLocation().distanceSquared(player.getLocation()) <= radiusSq) {
                        containers.add(container);
                    }
                }
            }
        }
        return containers;
    }

    public static List<Container> findContainersAroundLoaded(Player player, int radius) {
        World world = player.getWorld();
        int px = player.getLocation().getBlockX();
        int pz = player.getLocation().getBlockZ();
        int cx = px >> 4, cz = pz >> 4;
        int chunkRadius = (radius + 15) >> 4;
        int maxChunkDist = radius + 8;
        int maxChunkDistSq = maxChunkDist * maxChunkDist;
        int radiusSq = radius * radius;

        List<Container> containers = new ArrayList<>();

        for (int dx = -chunkRadius; dx <= chunkRadius; dx++) {
            int chunkX = cx + dx;
            int chunkCenterX = (chunkX << 4) + 8;
            int deltaX = chunkCenterX - px;
            if (deltaX * deltaX > maxChunkDistSq) continue;

            for (int dz = -chunkRadius; dz <= chunkRadius; dz++) {
                int chunkZ = cz + dz;
                int chunkCenterZ = (chunkZ << 4) + 8;
                int deltaZ = chunkCenterZ - pz;
                if (deltaZ * deltaZ > maxChunkDistSq) continue;

                // Skip unloaded chunks
                if (!world.isChunkLoaded(chunkX, chunkZ)) continue;

                Chunk chunk = world.getChunkAt(chunkX, chunkZ);
                for (BlockState state : chunk.getTileEntities()) {
                    if (state instanceof Container container) {
                        Block block = container.getBlock();
                        if (block.getLocation().distanceSquared(player.getLocation()) <= radiusSq) {
                            containers.add(container);
                        }
                    }
                }
            }
        }

        return containers;
    }

    public static void highlightBlocks(List<Block> blocks, Particle particle, long durationTicks) {
        Plugin plugin = PluginUtils.getPlugin();
        List<Block> filtered = blocks.stream()
                .filter(block -> activeOutlines.add(block.getLocation()))
                .toList();

        if (filtered.isEmpty()) return;

        List<ParticleOutline> outlines = new ArrayList<>();

        for (Block block : filtered) {
            ParticleOutline outline = new ParticleOutline(plugin, block, particle, 0.1, 1.0);
            outline.start(10L);
            outlines.add(outline);
        }

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (ParticleOutline outline : outlines) {
                outline.stop();
            }
            for (Block block : filtered) {
                activeOutlines.remove(block.getLocation());
            }
        }, durationTicks);
    }
}
