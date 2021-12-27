package de.eazypaulcode.settings;

import lombok.Getter;
import lombok.Setter;

/**
 * @author eazypaul
 * @since 24.12.2021
 */

@Getter
@Setter
public class SettingsManager {

    private boolean backpackEnabled = false;
    private int borderSize = 10000;
    private boolean border = true;
    private boolean damage = true;
    private boolean hunger = true;

    private boolean slowchat = true;
    private int slowchatDelay = 2;
    private boolean resetChatCommand = true;
    private boolean spawnCommand = true;
    private boolean suicideCommand = true;
    private boolean topCommand = true;

    public void resetGeneral() {
        backpackEnabled = false;
        borderSize = 10000;
        border = true;
        damage = true;
        hunger = true;
    }

}
