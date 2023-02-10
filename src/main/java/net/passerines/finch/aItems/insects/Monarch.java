package net.passerines.finch.aItems.insects;

import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchInsect;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class Monarch extends FinchInsect {
    public Monarch() {
        super("MonarchFly", 5);
        this.strength = 80;
        this.defense = 50;
        this.health = 100;
        this.bugDamage = 100;
        this.element = ElementalDamageEvent.Element.EARTH;
        displayName = Chat.formatC("&6Monarch Butterfly");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        this.lore = Chat.formatC(lore);
    }
    @Override
    public void onContact(Location loc){

    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
}
