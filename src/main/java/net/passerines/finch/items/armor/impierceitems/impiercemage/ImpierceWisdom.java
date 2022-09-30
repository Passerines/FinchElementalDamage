package net.passerines.finch.items.armor.impierceitems.impiercemage;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ImpierceWisdom extends FinchArmor {

    public ImpierceWisdom() {
        super("ImpierceWisdom");
        this.defense = 70;
        this.health = 30;
        this.mana = 1500;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Impierce &fWisdom"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+30")));
        lore.add(Component.text(Chat.format("&aDefense: &f+70")));
        lore.add(Component.text(Chat.format("&4Damage: &f+1500")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
