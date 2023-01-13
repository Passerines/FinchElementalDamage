package net.passerines.finch.aItems.weapons.melee.katanas.t5;

import de.slikey.effectlib.effect.*;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchEffectManager;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

public class KatanaOfFate extends FinchWeapon implements Listener, FinchCraftableItem {
    Cooldown<Player> cd = new Cooldown<>(9);
    Cooldown<Player> cd1 = new Cooldown<>(70);
    public KatanaOfFate() {
        super("KatanaOfFate", 4);
        this.attack = 35;
        this.critChance = 210;
        this.element = ElementalDamageEvent.Element.NEUTRAL;
        displayName = Chat.formatC("&6Katana of Fate");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &6Seal Fate");
        lore.add("&7Create a Column of Embers which damages players for 3 seconds");
        lore.add("&7dealing 35 damage (scales with strength) and applying slowness 5 for 3 seconds");
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
    @Override
    public void onClick(PlayerInteractEvent click){
        Player player = click.getPlayer();
        if(click.getAction().isLeftClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd.isOffCooldown(player)){
            Particle.DustOptions dust = new Particle.DustOptions(Color.fromRGB(255, 215, 84), 1.0F);
            Slash slash0 = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.SNOWFLAKE, 3, attack,2,0, dust);
            slash0.drawSlash();
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                Slash slash = new Slash(player, player.getEyeLocation(), getItem() , Particle.REDSTONE, Particle.SNOWFLAKE, 8, attack,90,1, dust);
                slash.drawSlash();
            }, 4);
            cd.add(player);
        }
        if(click.getAction().isRightClick() && id.equals(Util.getId(player.getInventory().getItemInMainHand())) && cd1.isOffCooldown(player)){
            HashSet<Material> transparent = new HashSet<>();
            transparent.add(Material.AIR);
            Block block = player.getTargetBlock(transparent, 120);
            Location loc = block.getLocation();
            CylinderEffect cylinderEffect = new CylinderEffect(FinchEffectManager.getEffectManager());
            ExplodeEffect explodeEffect = new ExplodeEffect(FinchEffectManager.getEffectManager());
            explodeEffect.setLocation(loc);
            explodeEffect.particle1 = Particle.LAVA;
            explodeEffect.particle2 = Particle.FLAME;
            loc.setY(loc.getY() + 6);
            cylinderEffect.setLocation(loc);
            cylinderEffect.particle = Particle.LAVA;
            cylinderEffect.start();
            int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(FinchElementalDamage.inst(), ()->{
                Collection<Entity> entitylist = cylinderEffect.getLocation().getNearbyEntities(2, 5, 2);
                Object[] entities = entitylist.toArray();
                for(Object entity : entities) {
                    if (entity instanceof Damageable) {
                        if (!(entity.equals(player))) {
                            String weaponId = Util.getId(player.getInventory().getItemInMainHand());
                            FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(weaponId);
                            if(finchItem instanceof FinchWeapon finchWeapon) {
                                ElementalDamageEvent elementalDamageEvent = new ElementalDamageEvent(player, (Entity) entity, EntityDamageEvent.DamageCause.ENTITY_ATTACK , finchWeapon.getElement(), 35, player.getInventory().getItemInMainHand());
                                elementalDamageEvent.apply();
                            }
                        }
                    }
                }
            }, 0, 4);
            Bukkit.getScheduler().scheduleSyncDelayedTask(FinchElementalDamage.inst(), ()->{
                FinchEffectManager.getEffectManager().done(cylinderEffect);
                Bukkit.getScheduler().cancelTask(task);
                FinchEffectManager.getEffectManager().start(explodeEffect);
            }, 48);
            cd1.add(player);
        }
    }


    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.setCustomModelData(7);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", -2.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        // Format the item instead of setting displayname and lore
        format(item);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack gold = ItemManager.ITEM_HASH_MAP.get("RitualEssence").getItem();
        ItemStack handle = ItemManager.ITEM_HASH_MAP.get("ProsperityKatana").getItem();
        ItemStack luck = ItemManager.ITEM_HASH_MAP.get("AngelicEssence").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "CAC", "CBC", "CAC" , gold, handle, luck);
        finchRecipe.addRecipe();
    }
}
