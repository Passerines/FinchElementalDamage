package net.passerines.finch.aItems.armor.impierceitems.impiercemage;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ImpierceShoes extends FinchArmor {

    public ImpierceShoes() {
        super("ImpierceShoes");
        this.defense = 175;
        this.health = 25;
        this.mana = 450;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Impierce &fBoots"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+25 ")));
        lore.add(Component.text(Chat.format("&aDefense: &f+175")));
        lore.add(Component.text(Chat.format("&4Mana: &f+450" )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
