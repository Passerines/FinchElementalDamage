package net.passerines.finch.aItems.weapons.melee.spears.t3;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.UUID;

public class PoisonedSpear extends FinchWeapon implements Listener {
    Cooldown cd = new Cooldown<>(5);
    public PoisonedSpear() {
        super("PoisonedSpear");
        this.attack = 25;
        this.critChance = 30;
        this.healthRegen = 15;
        this.element = ElementalDamageEvent.Element.EARTH;

        // Set the displayname and lore
        displayName = Chat.formatC("&2Poison Spear");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &cVampiric &2Poison");
        lore.add("&7Heal for poison damage enemy will take in one burst");
        lore.add("&6Passive Ability: &2Poison Arts");
        lore.add("&7All attacks using this weapon applies poison");
        this.lore = Chat.formatC(lore);
        //

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
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Slash slash = new Slash(player, player.getEyeLocation(), player.getInventory().getItemInMainHand(), Particle.CRIT, Particle.DRAGON_BREATH, 5, attack,2,0, null);
            slash.drawSlash();
            cd.add(player);
        }
    }
    @EventHandler
    public void onHit(ElementalDamageEvent event){
        if(event.getAttacker() instanceof Player player){
            if(id.equals(Util.getId(event.getWeapon()))){
                if(event.getVictim() instanceof LivingEntity livingEntity){
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 30, 2, false, false, true));
                    PlayerData playerData = PlayerMap.PLAYERS.get(player);
                    if(60 + playerData.getHealth() < playerData.getHealthMax()){
                        playerData.setHealth(playerData.getHealth() + 60);
                        String bar = Chat.format("&bRestored &f60 &4Health");
                        Chat.sendActionBar(player, bar);
                    }
                }
            }
        }
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(1);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        //
        return writeId(item);
    }
}
