package net.passerines.finch.aItems.weapons.ranged.artifacts;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.DrawLine;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class FateCrossbow extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(1);
    public FateCrossbow() {
        super("FateCrossbow");
        this.attack = 15;
        this.element = ElementalDamageEvent.Element.DARK;
        displayName = Chat.formatC("&0Fate" + " &fCrossbow");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Right Click: &6Future Eraser");
        lore.add("&8Shoots a Ray of Devastation That Consumes Time");
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void cancelHit(EntityDamageByEntityEvent hit){
        if(hit.getDamager() instanceof Player player){
            if(id.equals(Util.getId(player.getInventory().getItemInMainHand()))){
                hit.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent click){
        PlayerData vPlayer = PlayerMap.PLAYERS.get(click.getPlayer());
        Player player = click.getPlayer();
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            AtomicInteger i = new AtomicInteger(2);
            Location loc = player.getEyeLocation();
            int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst() ,()->{
                loc.add(loc.getDirection().multiply(i.get()));
                Slash slash = new Slash(player, loc, getItem() , Particle.ASH, Particle.TOTEM, 5, attack,2,0, null);
                slash.drawSlash();
            }, 0, 1);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->Bukkit.getScheduler().cancelTask(taskid), 28);
            cd.add(player);
        }
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(9);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        // Format the item instead of setting displayname and lore
        format(item);
        //
        return writeId(item);
    }
}
