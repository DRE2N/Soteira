package de.erethon.soteira.util;

import net.openhft.chronicle.bytes.BytesMarshallable;
import org.bukkit.Location;

/**
 * @author Fyreum
 */
public class ProtectionLocation implements BytesMarshallable {

    private String world;
    private int x, y, z;

    /**
     * Empty ProtectionLocation constructor.
     *
     * @deprecated for internal use only
     */
    @Deprecated
    public ProtectionLocation() {

    }

    public ProtectionLocation(Location location) {
        this(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public ProtectionLocation(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String getWorld() {
        return world;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
