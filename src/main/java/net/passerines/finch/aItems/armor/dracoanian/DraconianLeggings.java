package net.passerines.finch.aItems.armor.dracoanian;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DraconianLeggings extends FinchArmor {

    public DraconianLeggings() {
        super("DraconianLeggings");
        this.defense = 220;
        this.health = 145;
        this.damage = 25;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Draconian Leggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+"+this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+"+this.defense)));
        lore.add(Component.text(Chat.format("&4Damage: &f+"+this.damage)));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
