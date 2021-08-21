package mods.thecomputerizer.restrictedcreative;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = RestrictedCreative.MODID)
@Mod.EventBusSubscriber
public class config {

    @Comment("Whitelisted Players [These players will be exempt from the restriction placed by this mod]")
    public static String[] exemptPlayers = new String[] {};

    @Comment("Disabled Commands [Any commands listed here will be canceled]")
    public static String[] badCommands = new String[] {};

    @Comment("Disabled Items [Any items listed here will not be obtainable via the creative menu or jei]\nPut single items in as [modid:name] like [minecraft:stone]\nEntire mods can be blacklisted by inputting just the modid [You can also regex like this!]")
    public static String[] badItems = new String[] {};

    @Comment("Remove cheat mode from JEI [true/false]")
    public static boolean noCheating = false;

    @Mod.EventBusSubscriber(modid = RestrictedCreative.MODID)
    private static class EventHandler {

        /**
         * Inject the new values and save to the config file when the config has been changed from the GUI.
         *
         * @param event The event
         */
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(RestrictedCreative.MODID)) {
                ConfigManager.sync(RestrictedCreative.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
