package dev.faceless.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundOpenSignEditorPacket;
import net.minecraft.network.protocol.game.ServerboundSignUpdatePacket;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class SignInputService {
    private final Map<UUID, Consumer<String>> callbacks = new ConcurrentHashMap<>();
    private final Map<UUID, Location> signLocations = new ConcurrentHashMap<>();

    public void prompt(Player player, Consumer<String> onComplete) {
        Location base = player.getLocation();
        Location hiddenLoc = base.clone().subtract(0, 3, 0);
        BlockPos pos = new BlockPos(hiddenLoc.getBlockX(), hiddenLoc.getBlockY(), hiddenLoc.getBlockZ());

        callbacks.put(player.getUniqueId(), onComplete);
        signLocations.put(player.getUniqueId(), hiddenLoc);

        player.sendBlockChange(hiddenLoc, Material.OAK_SIGN.createBlockData());

        ClientboundOpenSignEditorPacket openPacket = new ClientboundOpenSignEditorPacket(pos, true);
        ((CraftPlayer) player).getHandle().connection.send(openPacket);
    }

    public void handleSignUpdate(Player player, ServerboundSignUpdatePacket pkt) {
        Consumer<String> cb = callbacks.remove(player.getUniqueId());
        Location loc = signLocations.remove(player.getUniqueId());
        if (cb == null || loc == null) return;

        StringBuilder sb = new StringBuilder();
        for (var line : pkt.getLines()) {
            sb.append(line);
        }

        cb.accept(sb.toString().trim());

        player.sendBlockChange(loc, loc.getBlock().getBlockData());
    }
}
