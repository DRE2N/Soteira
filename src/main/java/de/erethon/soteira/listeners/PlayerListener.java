package de.erethon.soteira.listeners;

import de.erethon.soteira.Soteira;
import de.erethon.soteira.protection.Protection;
import de.erethon.soteira.util.ProtectionLocation;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * @author Fyreum
 */
public class PlayerListener implements Listener {

    final Soteira plugin = Soteira.inst();

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null || block.getType() == Material.AIR) {
            return;
        }
        ProtectionLocation location = new ProtectionLocation(block.getLocation());
        Protection protection = plugin.getProtectionManager().getProtection(location);
        if (protection == null) {
            return;
        }
        if (plugin.resolveProtectedInteraction(event.getPlayer(), protection)) {
            event.setCancelled(true);
        }
    }

}
