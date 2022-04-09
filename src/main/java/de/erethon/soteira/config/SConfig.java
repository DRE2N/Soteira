package de.erethon.soteira.config;

import de.erethon.bedrock.config.EConfig;

import java.io.File;

/**
 * @author Fyreum
 */
public class SConfig extends EConfig {

    public static final int CONFIG_VERSION = 1;

    private String language;
    private long defaultDatabaseEntriesAmount = 50_000;

    public SConfig(File file) {
        super(file, CONFIG_VERSION);
        initialize();
        load();
    }

    @Override
    public void initialize() {
        initValue("language", language);
        initValue("defaultDatabaseEntriesAmount", defaultDatabaseEntriesAmount);
        save();
    }

    @Override
    public void load() {
        language = config.getString("language", language);
        defaultDatabaseEntriesAmount = config.getLong("defaultDatabaseEntriesAmount", defaultDatabaseEntriesAmount);
    }

    public String getLanguage() {
        return language;
    }

    public long getDefaultDatabaseEntriesAmount() {
        return defaultDatabaseEntriesAmount;
    }
}
