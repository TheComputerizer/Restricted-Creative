package mods.thecomputerizer.restrictedcreative;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Mouse;

import java.util.Objects;

import static mods.thecomputerizer.restrictedcreative.config.*;

@Mod.EventBusSubscriber(modid = RestrictedCreative.MODID)
public class events {
    public static EntityPlayer player;
    public static boolean middleMouse = false;
    public static boolean leftMouse = false;
    public static boolean rightMouse = false;
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
                if (Mouse.isButtonDown(0)) {
                    leftMouse = true;
                }
                if (Mouse.isButtonDown(1)) {
                    rightMouse = true;
                }
                if (event.getGui() instanceof GuiContainerCreative) {
                    GuiContainerCreative g = (GuiContainerCreative) event.getGui();
                    if (g.getSlotUnderMouse() != null) {
                        if (g.getSelectedTabIndex() != 11 && g.getSlotUnderMouse().slotNumber <= 44 || allGood) {
                            if (leftMouse || rightMouse) {
                                for (String i : badItems) {
                                    if (Objects.requireNonNull(g.getSlotUnderMouse().getStack().getItem().getRegistryName()).toString().contains(i)) {
                                        leftMouse = false;
                                        rightMouse = false;
                                        event.setCanceled(true);
                                    }
                                }
                            }
                        }
                    }
                }
                if (event.getGui().toString().contains("JEIModConfigGui")) {
                    player.closeScreen();
                }
                leftMouse = false;
                rightMouse = false;
                if (Mouse.isButtonDown(2)) {
                    middleMouse = true;
                }
                if (middleMouse) {
                    player.inventory.getItemStack().setCount(0);
                    middleMouse = false;
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
}
