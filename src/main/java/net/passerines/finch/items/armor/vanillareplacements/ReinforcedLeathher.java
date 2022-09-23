package net.passerines.finch.items.armor.vanillareplacements;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ReinforcedLeathher extends FinchArmor {
    public ReinforcedLeathher() {
        super("CarbonFiberBoots");
        this.defense = 100;
        this.health = 15;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fReinforced Leather Boots"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+15")));
        lore.add(Component.text(Chat.format("&aDefense: &f+100")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
