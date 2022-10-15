package net.passerines.finch.aItems.trinkets;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class NecklaceOfGod extends FinchTrinkets {
    public NecklaceOfGod() {
        super("NecklaceOfGod");
        this.mana = 3000;
        this.damage = 15;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&bNecklaceOfGod"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cDamage: &f+" + this.damage )));
        lore.add(Component.text(Chat.format("&bMana: &f+" + this.mana )));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
