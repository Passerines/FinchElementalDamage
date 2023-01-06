package net.passerines.finch.items;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class FinchInsect extends FinchEquipment implements Listener {
    public static HashMap<Player, FinchThrownInsect> thrownInsectMap = new HashMap<>();

    protected ElementalDamageEvent.Element element;
    protected int bugDamage;

    public FinchInsect(String id) {
        this(id, 1);
    }
    public FinchInsect(String id, int rarity) {
        super(id, rarity);
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    public abstract void onContact(Location loc);
    @EventHandler
    public void ifInsect(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(event.getAction().isRightClick() && event.getPlayer().isSneaking() && Util.getFinchItem(player.getInventory().getItemInOffHand()) instanceof FinchInsect finchInsect) {
            FinchThrownInsect finchThrownInsect = new FinchThrownInsect(player, finchInsect.getItem());
            finchThrownInsect.throwInsect();
            thrownInsectMap.put(player, finchThrownInsect);
        }

    }
    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event){
        if(event.isSneaking()){
            Player player = event.getPlayer();
            if(thrownInsectMap.containsKey(player)){
                FinchThrownInsect finchThrownInsect = thrownInsectMap.get(player);
                finchThrownInsect.retractInsect();
                thrownInsectMap.remove(player);
            }
        }
    }
    public int getBugDamage(){
        return bugDamage;
    }
    public ElementalDamageEvent.Element getElement() {
        return element;
    }
}