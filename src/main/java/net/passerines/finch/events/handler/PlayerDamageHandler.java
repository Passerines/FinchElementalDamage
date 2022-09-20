package net.passerines.finch.events.handler;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.events.CustomPlayerDeathEvent;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class PlayerDamageHandler implements Listener{

    public PlayerDamageHandler() {
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());

    }

    @EventHandler
    public void onElementalDamage(ElementalDamageEvent event) {
        Entity attacker = event.getAttacker();
        Entity victim = event.getVictim();
        if (victim instanceof Player) {
            PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
            double finalDamage = event.getDamage();
            double damageTaken = (int) ((finalDamage - (finalDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500.0))) * event.getElement().getElementalMultiplier()));

            if (attacker instanceof Player) {
                if(Util.getId(((Player) attacker).getPlayer().getInventory().getItemInMainHand()) != null){
                    damageTaken = (damageTaken + (PLAYERS.get(attacker).getDamage()*((Player) attacker).getPlayer().getAttackCooldown()));
                }
                else{damageTaken = 5;}
            }
            else if (attacker instanceof Arrow arrow) {
                if (arrow.getShooter() instanceof Player player) {
                    damageTaken = damageTaken + (PlayerMap.PLAYERS.get(player).getDamage()*(player).getPlayer().getAttackCooldown());
                }
            }
            else if (attacker instanceof LivingEntity entity) {
                damageTaken = damageTaken + EntityMap.ENTITIES.get(entity).getDamage();
            }

            vPlayerData.setHealth(vPlayerData.getHealth() - damageTaken);
            victim.playEffect(EntityEffect.HURT);

            victim.sendMessage("Damage Taken: " + damageTaken + " Element: " + event.getElement());

            if (attacker instanceof Player) {
                attacker.sendMessage("Damage Dealt: " + damageTaken + " Element: " + event.getElement());
            }

            if (vPlayerData.getHealth() <= 0) {
                CustomPlayerDeathEvent deathEvent = new CustomPlayerDeathEvent((Player) victim);
                deathEvent.apply();
            }
        }
    }

    @EventHandler
    public void onCustomPlayerDeath(CustomPlayerDeathEvent event){
        Player victim = event.getDeadVictim();
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((victim));
        victim.setInvulnerable(true);
        victim.sendMessage(Chat.format("&4You Died!"));
        Chat.sendTitle(victim, "&4You Died!");
        victim.teleport(victim.getWorld().getSpawnLocation());
        vPlayerData.setHealth(vPlayerData.getHealthMax());
        victim.getWorld().playSound(victim.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
        Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()-> victim.setInvulnerable(false), 60);
    }
}
