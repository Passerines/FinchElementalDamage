package net.passerines.finch.aItems.trinkets;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TotemOfStrength extends FinchTrinkets {
    public TotemOfStrength() {
        super("TotemOfStrength");
        this.damage = 5;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cTotem of Strength"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cDamage: &f+" + this.damage)));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
