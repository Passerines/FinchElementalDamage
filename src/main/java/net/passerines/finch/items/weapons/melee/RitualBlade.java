package net.passerines.finch.items.weapons.melee;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class RitualBlade extends FinchWeapon implements Listener {

    public RitualBlade() {
        super("Ritualblade");
        this.damage = 30;
        this.health = -10;
        this.defense = -10;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&4Ritual Blade"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
