package net.passerines.finch.aItems.armor.impierceitems.impiercewarrior;

import net.kyori.adventure.text.Component;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ImpierceAegis extends FinchArmor {

    public ImpierceAegis() {
        super("ImpierceAegis");
        this.defense = 650;
        this.health = 110;
        this.damage = 15;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Impierce &fAegis"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+110")));
        lore.add(Component.text(Chat.format("&aDefense: &f+650")));
        lore.add(Component.text(Chat.format("&4Damage: &f+15")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
