package net.passerines.finch.aItems.armor.impierceitems.impiercemage;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ImpierceLeggings extends FinchArmor {

    public ImpierceLeggings() {
        super("ImpierceLeggings");
        this.defense = 400;
        this.health = 45;
        this.mana = 750;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Impierce &fLeggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+" + (int) this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+" + this.health)));
        lore.add(Component.text(Chat.format("&bMana: &f+" + this.mana)));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
