package net.passerines.finch.aItems.armor.impierceitems.impiercewarrior;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ImpierceKneeplates extends FinchArmor {

    public ImpierceKneeplates() {
        super("ImpierceKneeplates");
        this.defense = 550;
        this.health = 95;
        this.strength = 15;
        displayName = Chat.formatC("&6Impierce Leggings");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
}
