package net.passerines.finch.aItems.weapons.ranged.t5;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.DrawLine;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchBow;
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

public class FateCrossbow extends FinchBow implements Listener {
    Cooldown cd = new Cooldown<>(1);
    public FateCrossbow() {
        super("FateCrossbow");
        this.bowDamage = 125;
        this.element = ElementalDamageEvent.Element.DARK;
        displayName = Chat.formatC("&0Fate's" + " &fCrossbow");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&5Right Click: &fReality Consumer");
        lore.add("&5Shoots 3 arrows around you");
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
        Player player = click.getPlayer();
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Location aboveLoc = player.getEyeLocation().add(0,2,0);
            aboveLoc.setPitch(player.getLocation().getPitch()+4);
            //RightLoc
            Location rightLoc = player.getEyeLocation();
            rightLoc.setYaw(rightLoc.getYaw()+90);
            rightLoc.setPitch(0);
            rightLoc.add(rightLoc.getDirection().multiply(2));
            rightLoc.setPitch(player.getLocation().getPitch());
            rightLoc.setYaw(player.getLocation().getYaw()-4);
            //Leftloc
            Location leftLoc = player.getEyeLocation();
            leftLoc.setYaw(leftLoc.getYaw()-90);
            leftLoc.setPitch(0);
            leftLoc.add(leftLoc.getDirection().multiply(2));
            leftLoc.setPitch(player.getLocation().getPitch());
            leftLoc.setYaw(player.getLocation().getYaw()+4);
            //end
            int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst() ,()->{
                FinchArrow arrow = new FinchArrow(player, aboveLoc,player.getInventory().getItemInMainHand(), 8, 0, this.bowDamage);
                FinchArrow arrow1 = new FinchArrow(player, rightLoc,player.getInventory().getItemInMainHand(), 8, 0, (int)(this.bowDamage*0.80));
                FinchArrow arrow2 = new FinchArrow(player, leftLoc,player.getInventory().getItemInMainHand(), 8, 0, (int)(this.bowDamage*0.80));
                arrow.shootCustomArrow("neutral", Particle.CRIT);
                arrow1.shootCustomArrow("light", Particle.FALLING_HONEY);
                arrow2.shootCustomArrow("dark", Particle.SOUL);
            }, 0, 1);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->Bukkit.getScheduler().cancelTask(taskid), 2);
            cd.add(player);
        }
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        // Format the item instead of setting displayname and lore
        format(item);
        //
        return writeId(item);
    }
}
