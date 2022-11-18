package net.passerines.finch.aItems.weapons.ranged.artifacts;

import de.slikey.effectlib.effect.*;
import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchEffectManager;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.FinchArrow;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.events.handler.ItemChangeEvent;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchBow;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class BowOfTheElements extends FinchBow implements Listener {
    public BowOfTheElements() {
        super("BowOfTheElements");
        this.bowDamage = 150;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    public static final HashMap<Player, DonutEffect> SHIELDED_PLAYER_BOE = new HashMap<>();
    private final Cooldown cd = new Cooldown(10);
    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if(event.getAction().isLeftClick() && cd.isOffCooldown(player)) {
            if(id.equals(Util.getId(item))) {
                FinchArrow finchArrow = new FinchArrow(player, 2, 0, this.bowDamage);
                cd.add(player);
                ItemMeta itemMeta = item.getItemMeta();
                if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (0)){
                    finchArrow.shootWaterArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralWater");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (1)){
                    finchArrow.shootFireArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralFire");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (2)){
                    finchArrow.shootEarthArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralEarth");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (3)){
                    finchArrow.shootElectroArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralElectro");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (4)){
                    finchArrow.shootDarkArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralDark");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (5)){
                    finchArrow.shootLightArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralLight");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (6)){
                    finchArrow.shootUndeadArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralUndead");
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (7)){
                    finchArrow.shootWindArrow().getPersistentDataContainer().set(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING, "SpiralWind");
                }
                item.setItemMeta(itemMeta);
            }
        }
    }
    @EventHandler
    public void onContact(ProjectileHitEvent event){
        if(event.getEntity() instanceof Arrow arrow){
            if(arrow.getPersistentDataContainer().has(Util.getNamespacedKey("ElementalArrow"))){
                Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                    Location loc = arrow.getLocation();
                    HelixEffect effect = new HelixEffect(FinchEffectManager.getEffectManager());
                    if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralFire")){
                        effect.particle = Particle.FLAME;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralWater")){
                        effect.particle = Particle.GLOW;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralEarth")){
                        effect.particle = Particle.CRIT;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralElectro")){
                        effect.particle = Particle.ELECTRIC_SPARK;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralDark")){
                        effect.particle = Particle.ASH;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralLight")){
                        effect.particle = Particle.WHITE_ASH;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralUndead")){
                        effect.particle = Particle.SOUL;
                    }
                    else if(arrow.getPersistentDataContainer().get(Util.getNamespacedKey("ElementalArrow"), PersistentDataType.STRING).equals("SpiralWind")){
                        effect.particle = Particle.EXPLOSION_NORMAL;
                    }
                    effect.setLocation(loc.add(0, 0,0));
                    FinchEffectManager.getEffectManager().start(effect);

                    Player player = (Player) arrow.getShooter();
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta itemMeta = item.getItemMeta();
                    if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (0)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 1);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.BUBBLE_POP;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (1)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 2);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.CRIT;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (2)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 3);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.SPELL_WITCH;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (3)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 4);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.ASH;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (4)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 5);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.WHITE_ASH;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (5)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 6);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.SOUL;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (6)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 7);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.TOTEM;
                    }
                    else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (7)){
                        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0);
                        SHIELDED_PLAYER_BOE.get(player).particle = Particle.FLAME;
                    }
                    item.setItemMeta(itemMeta);
                }, 6);

            }
        }
    }
    @EventHandler
    public void blockingEffect(ElementalDamageEvent event){
        if(event.getVictim() instanceof Player player){
            if(Util.getId(player.getInventory().getItemInMainHand()).equals(id)){
                ItemStack item = player.getInventory().getItemInMainHand();
                ItemMeta itemMeta = item.getItemMeta();
                if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (0)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.FIRE)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (1)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.WATER)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (2)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.EARTH)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (3)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.ELECTRO)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (4)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.DARK)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (5)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.LIGHT)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (6)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.UNDEAD)){
                        event.setCancelled(true);
                    }
                }
                else if(itemMeta.getPersistentDataContainer().getOrDefault(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0) == (7)){
                    if(event.getElement().equals(ElementalDamageEvent.Element.WIND)){
                        event.setCancelled(true);
                    }
                }
            }
        }
    }
    @EventHandler
    public void onHold(ItemChangeEvent event){
        Player player = event.getPlayer();
        if(SHIELDED_PLAYER_BOE.containsKey(player)){
            FinchEffectManager.getEffectManager().done(SHIELDED_PLAYER_BOE.get(player));
            SHIELDED_PLAYER_BOE.remove(player);
        }
        if(id.equals(Util.getId(event.getItemStack()))){
            Location loc = player.getEyeLocation();
            DonutEffect effect =  new DonutEffect(FinchEffectManager.getEffectManager());
            loc.setPitch(0);
            effect.setLocation(loc);
            effect.setEntity(player);
            effect.setTargetEntity(player);
            effect.updateDirections = false;
            effect.iterations = -1;
            effect.disappearWithOriginEntity = true;
            effect.disappearWithTargetEntity = true;
            FinchEffectManager.getEffectManager().start(effect);
            SHIELDED_PLAYER_BOE.put(player, effect);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fBow of the &4E&6l&ae&bm&9e&dn&5t&8s"));
        item.getItemMeta().getPersistentDataContainer().set(Util.getNamespacedKey("BOTE"), PersistentDataType.INTEGER, 0);
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&4Damage: &f+" + this.damage )));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
