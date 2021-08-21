package mods.thecomputerizer.restrictedcreative;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RestrictedCreative.MODID, name = RestrictedCreative.NAME, version = RestrictedCreative.VERSION, dependencies = "required-after:jei")
public class RestrictedCreative
{
    public static final String MODID = "restrictedcreative";
    public static final String NAME = "Restricted Creative";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(events.class);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
    }

}
