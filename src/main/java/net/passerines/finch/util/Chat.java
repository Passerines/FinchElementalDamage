package net.passerines.finch.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    //Formats chat messages
    public static String format(String str) {
        str = applyColorCode(str);
        return str;
    }

    public static List<String> format(List<String> list) {
        for(int i=0; i<list.size(); i++) {
            list.set(i, format(list.get(i)));
        }
        return list;
    }

    public static String formatTime(int seconds) {
        return String.format("%d:%02d", seconds/60, seconds%60);
    }

    public static String applyColorCode(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void sendTitle(Player player, String title) {
        player.sendTitlePart(TitlePart.TITLE, Chat.formatC(title));
    }
    public static void sendSubtitle(Player player, String subtitle) {
        player.sendTitlePart(TitlePart.SUBTITLE, Chat.formatC(subtitle));
    }

    public static String asPlainText(Component component) {
        return PlainTextComponentSerializer.plainText().serialize(component);
    }
    public static String asLegacy(Component component) {
        return LegacyComponentSerializer.legacyAmpersand().serialize(component);
    }
    public static ArrayList<String> asLegacy(List<Component> components) {
        ArrayList<String> list = new ArrayList<>();
        for(Component component : components) {
            list.add(LegacyComponentSerializer.legacyAmpersand().serialize(component));
        }
        return list;
    }
    public static String asGson(Component component) {
        return GsonComponentSerializer.gson().serialize(component);
    }
    public static Component asComponent(String str) {
        return LegacyComponentSerializer.legacySection().deserialize(str).applyFallbackStyle(TextDecoration.ITALIC.withState(false), NamedTextColor.WHITE);
    }
    public static List<Component> asComponent(List<String> list) {
        List<Component> components = new ArrayList<>();
        for(String str : list) {
            components.add(asComponent(str));
        }
        return components;
    }
    public static Component formatC(String str) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(str).applyFallbackStyle(TextDecoration.ITALIC.withState(false), NamedTextColor.WHITE);
    }
    public static List<Component> formatC(List<String> list) {
        List<Component> components = new ArrayList<>();
        for(String str : list) {
            components.add(formatC(str));
        }
        return components;
    }

}
