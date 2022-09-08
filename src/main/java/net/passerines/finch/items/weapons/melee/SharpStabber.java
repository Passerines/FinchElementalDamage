package net.passerines.finch.items.weapons.melee;

import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class SharpStabber extends FinchWeapon {

    public SharpStabber() {
        super("SharpStabber");
        this.damage = 25;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cSharp Stabber"));
        item.setItemMeta(itemMeta);
        itemMeta.setUnbreakable(true);
        return writeId(item);
    }
}
