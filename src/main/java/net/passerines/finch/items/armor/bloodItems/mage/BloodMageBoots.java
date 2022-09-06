package net.passerines.finch.items.armor.bloodItems.mage;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BloodMageBoots extends FinchArmor {

    public BloodMageBoots() {
        super("BloodMageBoots");
        this.defense = 15;
        this.health = -5;
        this.mana = 40;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBloodMage Boots"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&f -5 &cHealth")));
        lore.add(Component.text(Chat.format("&f +15 &aDefense")));
        lore.add(Component.text(Chat.format("&f +40 &bMana" )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
