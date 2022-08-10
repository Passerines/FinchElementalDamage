package net.passerines.finch.util;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;

public class ProtocolLibIntegration {

    public static ProtocolManager manager = ProtocolLibrary.getProtocolManager();
    public static void sendJson(Player player, String message) {
        PacketContainer chat = manager.createPacket(PacketType.Play.Server.CHAT, true);
        chat.getChatTypes().write(0, EnumWrappers.ChatType.SYSTEM);
        chat.getChatComponents().write(0, WrappedChatComponent.fromJson(message));
        try {
            manager.sendServerPacket(player, chat);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void sendActionBar(Player player, String message) {
        PacketContainer chat = manager.createPacket(PacketType.Play.Server.SET_ACTION_BAR_TEXT, true);
        //chat.getTitleActions().write(0, EnumWrappers.TitleAction.ACTIONBAR);
        chat.getChatComponents().write(0, WrappedChatComponent.fromText(Chat.format(message)));
        try {
            manager.sendServerPacket(player, chat);
        } catch(InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
