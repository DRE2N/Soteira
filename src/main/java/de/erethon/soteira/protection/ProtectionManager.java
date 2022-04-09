package de.erethon.soteira.protection;

import de.erethon.bedrock.chat.MessageUtil;
import de.erethon.soteira.Soteira;
import de.erethon.soteira.util.ProtectionLocation;
import net.openhft.chronicle.map.ChronicleMap;
import net.openhft.chronicle.map.ChronicleMapBuilder;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

/**
 * @author Fyreum
 */
public class ProtectionManager {

    final Soteira plugin = Soteira.inst();

    private final ChronicleMap<ProtectionLocation, Protection> protections;

    public ProtectionManager(File file) throws IOException {
        this.protections = ChronicleMapBuilder.of(ProtectionLocation.class, Protection.class)
                .name("protections-map")
                .averageKey(new ProtectionLocation("Erethon", 3000, 150, -3000))
                .entries(plugin.getSConfig().getDefaultDatabaseEntriesAmount())
                .createOrRecoverPersistedTo(file, false);
        // test:
        UUID[] owners = new UUID[]{
                UUID.fromString("f5218b36-8118-4ec0-8988-bb8bde94de94"),
                UUID.fromString("6cf2d12e-9ff5-4580-b167-f60e8d95135e"),
                UUID.fromString("f3f84625-e15e-474e-bbc9-be6f5d8b0bad"),
                UUID.fromString("86393676-00fb-425d-9ebf-c8ae22794e56"),
                UUID.fromString("68876839-5f7d-4866-8f08-5ada70083fc5"),
                UUID.fromString("b9c0925e-befc-4fce-988a-6b6bca9e1e04"),
                UUID.fromString("4bda76e7-6700-4901-afb2-bc76621a1194"),
                UUID.fromString("33bf100e-b8cc-4365-a679-3f665f4dfbc7"),
                UUID.fromString("c635bc9c-bc60-4cea-87b3-b5f70302e94c"),
                UUID.fromString("9535b445-6b19-428e-9340-edc0d95fcc54")
        };
        // test phase 1:
        if (protections.isEmpty()) {
            MessageUtil.log("Empty protections map: adding test values...");
            Random random = new Random();
            int ownerId = 0;
            for (int i = 0; i < 5_000; i++) {
                if (ownerId == owners.length) {
                    ownerId = 0;
                }
                ProtectionLocation location = new ProtectionLocation("Erethon", random.nextInt(-5000, 5000),
                        random.nextInt(0, 255), random.nextInt(-5000, 5000));
                Protection protection = new Protection(System.currentTimeMillis(), Protection.Type.PRIVATE, owners[ownerId++],
                        null, null, null, "");
                addProtection(location, protection); // add random generated protections and locations
            }
            MessageUtil.log("Finished adding test values!");
        }
        // test phase 2:
    }

    public Protection getProtection(ProtectionLocation location) {
        return protections.get(location);
    }

    public void addProtection(ProtectionLocation location, Protection protection) {
        protections.put(location, protection);
    }

    public void removeProtection(ProtectionLocation location) {
        protections.remove(location);
    }

    /**
     * Removes every protection the player owns.
     * This process might take a while and influence the server performance negatively.
     */
    public void removePlayerProtections(Player player) {
        UUID uuid = player.getUniqueId();
        protections.forEachEntry(entry -> {
            Protection protection = entry.value().get();
            if (protection.getOwner().equals(uuid)) {
                entry.doRemove();
            }
            // This would erase the player completely. Might be used somewhere else.
            //protection.getAdmins().remove(uuid);
            //protection.getMembers().remove(uuid);
        });
    }

    public void close() {
        protections.close();
    }
}
