package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Collection;

public abstract class FinchGlaives extends FinchEquipment implements Listener {

    protected ElementalDamageEvent.Element element;

    public FinchGlaives(String id) {
        this(id, 1);
    }
    public FinchGlaives(String id, int rarity) {
        super(id, rarity);
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void ifInsect(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(id.equals(Util.getId(player.getInventory().getItemInMainHand())) && Util.getFinchItem(player.getInventory().getItemInOffHand()) instanceof FinchInsect finchInsect) {
            FinchThrownInsect finchThrownInsect = new FinchThrownInsect(player, finchInsect.getItem(), player.getInventory().getItemInMainHand());
            finchThrownInsect.throwInsect();
        }
    }
    public ElementalDamageEvent.Element getElement() {
        return element;
    }
}