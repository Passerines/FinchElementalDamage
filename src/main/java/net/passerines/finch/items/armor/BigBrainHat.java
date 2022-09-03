package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BigBrainHat extends FinchArmor {

    public BigBrainHat() {
        super("BigBrainHat");
        this.defense = 25;
        this.health = 5;
        this.mana = 100;

    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bBig Brain Hat"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
