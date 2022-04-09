package de.erethon.soteira.protection;

import net.openhft.chronicle.bytes.BytesMarshallable;

import java.util.Set;
import java.util.UUID;

/**
 * @author Fyreum
 */
public class Protection implements BytesMarshallable {

    /**
     * The protection type
     * <p>
     * <p>
     * Ordering <b>must NOT change</b> as ordinal values are used
     * </p>
     */
    public enum Type {

        /**
         * The protection is usable by anyone; the most common use would include
         * community chests where anyone can use the chest but no one should be
         * able to protect as their own.
         */
        PUBLIC,

        /**
         * The owner (and anyone else) must enter a set password entered onto
         * the chest in order to be able to access it. Entering the correct
         * password allows them to use the chest until they log out or the
         * protection is removed.
         */
        PASSWORD,

        /**
         * The protection is only usable by the player who created it. Further
         * access can be given to players, groups, and even more specific
         * entities such as Towns in the "Towny" plugin, or access lists via the
         * "Lists" plugin
         */
        PRIVATE,

        /**
         * Reserved / unused, to keep ordinal order
         */
        RESERVED1,

        /**
         * Reserved / unused, to keep ordinal order
         */
        RESERVED2,

        /**
         * Allows players to deposit items into
         */
        DONATION,

        /**
         * Allows players to look into but not take
         */
        DISPLAY;

        /**
         * Match a protection type using its name
         *
         * @param name the name
         * @return the matching type or null
         */
        public static Type getByName(String name) {
            for (Type type : values()) {
                if (type.toString().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return null;
        }

    }

    private Long creationDate; // The date at which the protection was created
    private Type type; // The type of protection
    private UUID owner; // The creator of the protection
    private Set<UUID> admins; // Everyone that can administrate the protection
    private Set<UUID> members; // Everyone that has access to the protection
    private Set<ProtectionFlag> flags; // A list of protection flags
    /* optional */
    private String password;

    /**
     * Empty Protection constructor.
     *
     * @deprecated for internal use only
     */
    @Deprecated
    public Protection() {

    }

    public Protection(Long creationDate, Type type, UUID owner, Set<UUID> admins, Set<UUID> members,
                      Set<ProtectionFlag> flags, String password) {
        this.creationDate = creationDate;
        this.type = type;
        this.owner = owner;
        this.admins = admins;
        this.members = members;
        this.flags = flags;
        this.password = password;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public Type getType() {
        return type;
    }

    public UUID getOwner() {
        return owner;
    }

    public Set<UUID> getAdmins() {
        return admins;
    }

    public Set<UUID> getMembers() {
        return members;
    }

    public Set<ProtectionFlag> getFlags() {
        return flags;
    }

    public String getPassword() {
        return password;
    }
}
