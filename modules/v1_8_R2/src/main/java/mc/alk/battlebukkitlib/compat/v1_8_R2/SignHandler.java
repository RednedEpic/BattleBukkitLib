package mc.alk.battlebukkitlib.compat.v1_8_R2;

import mc.alk.battlebukkitlib.fanciful.FancyMessage;
import mc.alk.battlebukkitlib.handlers.ISignHandler;

import net.minecraft.server.v1_8_R2.BlockPosition;
import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutUpdateSign;
import net.minecraft.server.v1_8_R2.World;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

/**
 * For craftbukkit 1.8.3
 */
public class SignHandler implements ISignHandler {

    @Override
    public void sendLines(Player player, Sign sign, String[] lines) {
        Location loc = sign.getLocation();
        int x = loc.getBlock().getX();
        int y = loc.getBlock().getY();
        int z = loc.getBlock().getZ();
        World world = ((CraftWorld)loc.getWorld()).getHandle();
        BlockPosition bp = new BlockPosition(x, y, z);
        IChatBaseComponent[] components = new IChatBaseComponent[lines.length];
        for (int index = 0; index < lines.length; index++) {
            String json = new FancyMessage(lines[index]).toJSONString();
            components[index] = ChatSerializer.a(json);
        }
        PacketPlayOutUpdateSign packet = new PacketPlayOutUpdateSign(world, bp, components);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
