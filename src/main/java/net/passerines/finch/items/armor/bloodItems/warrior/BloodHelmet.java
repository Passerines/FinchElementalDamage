package net.passerines.finch.items.armor.bloodItems.warrior;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BloodHelmet extends FinchArmor {

    public BloodHelmet() {
        super("BloodHelmet");
        this.defense = 15;
        this.health = -5;
        this.damage = 6;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Helmet"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&f -5 &cHealth")));
        lore.add(Component.text(Chat.format("&f +15 &aDefense")));
        lore.add(Component.text(Chat.format("&f +6 &4Damage" )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
