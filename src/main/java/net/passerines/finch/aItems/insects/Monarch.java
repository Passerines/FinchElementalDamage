package net.passerines.finch.aItems.insects;

import net.passerines.finch.items.FinchInsect;
import net.passerines.finch.items.FinchWeapon;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Monarch extends FinchInsect {
    public Monarch() {
        super("MonarchFly", 5);
        this.health = -15;
        this.mana = 1000;
        this.strength = 100;
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
