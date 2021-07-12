package moe.exusiai.kirara;

import net.fabricmc.api.ModInitializer;

public class Kirara implements ModInitializer {

    @Override
    public void onInitialize() {
        Config.loadConfig();
    }
}
