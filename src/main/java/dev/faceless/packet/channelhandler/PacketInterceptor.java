package dev.faceless.packet.channelhandler;

import dev.faceless.PluginUtils;
import dev.faceless.packet.PacketManager;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;

public class PacketInterceptor extends ChannelDuplexHandler {
    private final Player player;

    public PacketInterceptor(Player player) {
        this.player = player;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Packet<?> packet) {
            boolean cancelled = PacketManager.getInstance().notifyServerboundPacket(player, packet);
            if (!cancelled) {
                super.channelRead(ctx, msg);
                return;
            }
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof Packet<?> packet) {
            boolean cancelled = PacketManager.getInstance().notifyClientboundPacket(player, packet);
            if (!cancelled) {
                super.write(ctx, msg, promise);
                return;
            }
        }
        super.write(ctx, msg, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        PluginUtils.getPlugin().getSLF4JLogger().error("An Error occurred in PacketInterceptor", cause);
        ctx.close();
    }
}
