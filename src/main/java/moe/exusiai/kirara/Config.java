package moe.exusiai.kirara;

import java.io.*;
import java.util.Properties;

public class Config {
    private static File configFile = new File("./config/kirara.properties");

    public static void loadConfig() {
        if (configFile.exists()) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(configFile));
                for (String key : properties.stringPropertyNames()) {
                    Data.Emojis.put(key, properties.get(key).toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                writeDefaultConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeDefaultConfig() throws IOException {
        File dir = configFile.getParentFile();

        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("Could not create parent directories");
            }
        } else if (!dir.isDirectory()) {
            throw new IOException("The parent file is not a directory");
        }

        try (Writer writer = new FileWriter(configFile)) {
            writer.write("# This is the configuration file for Kirara.\n");
            writer.write("# Type your emojis in like below\n");
            writer.write("# REMEMBER TO USE UNICODE!!!!!");
            writer.write("heart=\\u2764");
        }
    }
}
