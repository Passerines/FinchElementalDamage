package net.passerines.finch.items.armor;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BigBrainHat extends FinchArmor {

    public BigBrainHat() {
        super("BigBrainHat");
        this.defense = 25;
        this.health = 50;
        this.mana = 1000;

    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bBig Brain Hat"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&f +25 &cHealth")));
        lore.add(Component.text(Chat.format("&f +25 &aDefense")));
        lore.add(Component.text(Chat.format("&f +1000 &bMana " )));
        lore.add(Component.text(Chat.format(" ")));;
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
