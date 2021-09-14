package mods.thecomputerizer.restrictedcreative;

import mezz.jei.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.Objects;

import static mezz.jei.config.KeyBindings.toggleCheatMode;
import static mods.thecomputerizer.restrictedcreative.config.*;

@Mod.EventBusSubscriber(modid = RestrictedCreative.MODID)
public class events {
    public static EntityPlayer player;
    public static boolean allGood = false;

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onMouseInput(GuiScreenEvent.MouseInputEvent event) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().world.isRemote) {
            player = Minecraft.getMinecraft().player;
            if (player.isCreative()) {
                for (String exempt : exemptPlayers) {
                    if (player.getName().matches(exempt)) {
                        return;
                    }
                }
                if (event.getGui() instanceof GuiContainerCreative) {
                    GuiContainerCreative g = (GuiContainerCreative) event.getGui();
                    if (g.getSlotUnderMouse() != null) {
                        if (g.getSelectedTabIndex() != 11 && g.getSlotUnderMouse().slotNumber <= 44 || allGood) {
                            if (Mouse.getEventButton()==0 || Mouse.getEventButton()==1) {
                                for (String i : badItems) {
                                    if (Objects.requireNonNull(g.getSlotUnderMouse().getStack().getItem().getRegistryName()).toString().contains(i)) {
                                        player.inventory.getItemStack().setCount(0);
                                        event.setCanceled(true);
                                    }
                                }
                            }
                        }
                    }
                }
                if (Mouse.getEventButton()==2) {
                    player.inventory.getItemStack().setCount(0);
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onKeyboardInput(GuiScreenEvent.KeyboardInputEvent event) {
        if (event.getGui() instanceof GuiContainerCreative) {
            GuiContainerCreative g = (GuiContainerCreative) event.getGui();
            if (g.getSelectedTabIndex() != 11 && Objects.requireNonNull(g.getSlotUnderMouse()).slotNumber <= 44 || allGood) {
                if (Keyboard.getEventKey() >= 2 && Keyboard.getEventKey() <= 10) {
                    for (String i : badItems) {
                        if (Objects.requireNonNull(Objects.requireNonNull(g.getSlotUnderMouse()).getStack().getItem().getRegistryName()).toString().contains(i)) {
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onCommand(CommandEvent event) {
        if (player != null) {
            if (player.isCreative()) {
                for (String exempt : exemptPlayers) {
                    if (player.getName().matches(exempt)) {
                        return;
                    }
                }
                for (String command : badCommands) {
                    if (event.getCommand().getName().matches(command)) {
                        event.setCanceled(true);
                        return;
                    }
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().player != null) {
            if (Minecraft.getMinecraft().player.isCreative()) {
                for (String exempt : exemptPlayers) {
                    if (Minecraft.getMinecraft().player.getName().matches(exempt)) {
                        return;
                    }
                }
                if(noCheating) {
                    Config.setCheatItemsEnabled(false);
                    toggleCheatMode.setKeyCode(Keyboard.KEY_NONE);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        /*
        if (!player.world.isRemote) {
            System.out.print(configHandler.buildExemptPlayers()+"\n"+configHandler.buildBadCommands()+"\n"+configHandler.buildBadItems()+"\n"+valueOf(noCheating)+"\n");
            if(!configHandler.buildExemptPlayers().matches("e:")) {
                Handler.network.sendTo(new CombinedConfig.ConfigMessage(configHandler.buildExemptPlayers()), (EntityPlayerMP) player);
            }
            if(!configHandler.buildExemptPlayers().matches("c:")) {
                Handler.network.sendTo(new CombinedConfig.ConfigMessage(configHandler.buildBadCommands()), (EntityPlayerMP) player);
            }
            if(!configHandler.buildExemptPlayers().matches("i:")) {
                Handler.network.sendTo(new CombinedConfig.ConfigMessage(configHandler.buildBadItems()), (EntityPlayerMP) player);
            }
            Handler.network.sendTo(new CombinedConfig.ConfigMessage(valueOf(noCheating)), (EntityPlayerMP) player);
        }
         */
    }
}
