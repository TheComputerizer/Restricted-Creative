package mods.thecomputerizer.restrictedcreative;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Arrays;
import java.util.List;

public class Handler {
    public static SimpleNetworkWrapper network;

    public static void init() {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(RestrictedCreative.MODID);
        registerPackets();
    }

    private static void registerPackets()
    {
        network.registerMessage(CombinedConfig.class, CombinedConfig.ConfigMessage.class, 0, Side.CLIENT);
        network.registerMessage(CombinedConfig.class, CombinedConfig.ConfigMessage.class, 1, Side.SERVER);
    }
}
