package de.erethon.soteira.protection;

/**
 * @author Fyreum
 */

public enum ProtectionFlag {

    /**
     * Redstone use will be disabled on the protection if protections.denyRedstone = false;
     * however if denyRedstone = true, this flag will instead enable redstone on the protection!
     */
    REDSTONE,

    /**
     * The door will automatically close after the time configured in plugins/LWC/doors.yml
     */
    AUTO_CLOSE,

    /**
     * Allows explosions to blow a protection up
     */
    ALLOW_EXPLOSIONS,

    /**
     * Controls whether or not hoppers can be used on a protection
     */
    HOPPER,

    /**
     * Controls whether or not hoppers can be used on a protection to place items in
     */
    HOPPER_IN,

    /**
     * Controls whether or not hoppers can be used on a protection to take items out
     */
    HOPPER_OUT;
}
