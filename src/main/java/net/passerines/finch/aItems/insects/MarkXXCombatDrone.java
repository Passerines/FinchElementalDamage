package net.passerines.finch.aItems.insects;

import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchInsect;
import net.passerines.finch.util.Chat;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.Collection;


public class MarkXXCombatDrone extends FinchInsect {
    public MarkXXCombatDrone() {
        super("MarkXXCombatDrone", 5);
        this.defense = 50;
        this.bugDamage = 210;
        this.element = ElementalDamageEvent.Element.FIRE;
        displayName = Chat.formatC("&6Mark XX Damage Drone");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        this.lore = Chat.formatC(lore);
    }
    @Override
    public void onContact(Location loc){
        loc.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, loc, 15);
        Collection<Entity> entities = loc.getNearbyEntities(2,2,2);
        for(Object entity : entities){
            if(entity instanceof LivingEntity victim){
                victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 2, false, false, true));
            }
        }
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
