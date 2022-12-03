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

        displayName = Chat.formatC("&bBig Brain Hat");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        format(item);
        return writeId(item);
    }
}
