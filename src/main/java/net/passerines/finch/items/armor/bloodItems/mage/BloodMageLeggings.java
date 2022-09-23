package net.passerines.finch.items.armor.bloodItems.mage;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BloodMageLeggings extends FinchArmor {

    public BloodMageLeggings() {
        super("BloodMageLeggings");
        this.defense = 20;
        this.health = -10;
        this.mana = 55;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBloodMage Leggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f-10")));
        lore.add(Component.text(Chat.format("&aDefense: &f+20")));
        lore.add(Component.text(Chat.format("&bMana: &f+55")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
