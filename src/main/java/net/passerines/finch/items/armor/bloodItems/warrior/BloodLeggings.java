package net.passerines.finch.items.armor.bloodItems.warrior;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BloodLeggings extends FinchArmor {

    public BloodLeggings() {
        super("BloodLeggings");
        this.defense = 20;
        this.health = -10;
        this.damage = 7;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Leggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&f -10 &cHealth")));
        lore.add(Component.text(Chat.format("&f +20 &aDefense")));
        lore.add(Component.text(Chat.format("&f +7 &4Damage" )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
