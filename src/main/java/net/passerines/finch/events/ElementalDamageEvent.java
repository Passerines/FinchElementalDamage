package net.passerines.finch.events;

import com.ticxo.modelengine.api.ModelEngineAPI;
import net.passerines.finch.entity.EntityData;
import net.passerines.finch.entity.EntityMap;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchBow;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import static net.passerines.finch.players.PlayerMap.PLAYERS;

public class ElementalDamageEvent extends Event implements Cancellable {

    private double damage;
    private double finalDamage;
    private int critAmount;
    private Entity attacker;
    private Entity victim;
    private EntityDamageEvent.DamageCause damageCause;
    private Element element;
    private ItemStack weapon;

    private boolean cancelled;
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public enum Element {
        FIRE(Chat.format("&6Fire"), "&6",1f),
        WATER(Chat.format("&bWater"), "&b",1f),
        EARTH(Chat.format("&2Earth"), "&2",1f),
        WIND(Chat.format("&fWind"), "&f",1f),
        ELECTRO(Chat.format("&dElectro"), "&d",1f),
        LIGHT(Chat.format("&eLight"), "&e",1f),
        NEUTRAL(Chat.format("&7Neutral"), "&7",1f),
        UNDEAD(Chat.format("&4Undead"), "&4",1f),
        DARK(Chat.format("&8Dark"), "&8",1f),
        TRUE(Chat.format("&fTrue"), "&f",1f);
        private final String displayName;
        private final String color;
        private final float elementalMultiplier;
        Element(String displayName, String color, float elementalMultiplier) {
            this.displayName = displayName;
            this.color = color;
            this.elementalMultiplier = elementalMultiplier;
        }
        public String getDisplayName() {
            return displayName;
        }
        public String getColor(){return color;}
        public float getElementalMultiplier() {
            return elementalMultiplier;
        }
    }

    public ElementalDamageEvent(Entity attacker, Entity victim, EntityDamageEvent.DamageCause damageCause, Element element, double damage) {
        this(attacker, victim, damageCause, element, damage, null);
    }
    public ElementalDamageEvent(Entity attacker, Entity victim, Element element, double damage) {
        this(attacker, victim, EntityDamageEvent.DamageCause.CUSTOM, element, damage, null);
    }
    public ElementalDamageEvent(Entity attacker, Entity victim,  EntityDamageEvent.DamageCause damageCause, Element element, double damage, ItemStack weapon) {
        this.attacker = attacker;
        this.victim = victim;
        this.element = element;
        this.damage = damage;
        this.damageCause = damageCause;
        this.weapon = weapon;
    }

    public void calculate() {
        double attackDamage = damage;
        if(element != Element.TRUE) {
            if (attacker instanceof Player player) {
                PlayerData playerData = PlayerMap.PLAYERS.get(player);
                float critMultiplier;
                int baseCrit = playerData.getCritChance()/100;
                int calcCrit = playerData.getCritChance()%100;
                if(Util.getFinchItem(weapon) instanceof FinchBow){
                    attackDamage = attackDamage * (1 + playerData.getDexterity()/150.0);
                }
                else if (Util.getFinchItem(weapon) instanceof FinchWeapon) {
                    //Every 150 strength double damage
                    attackDamage = attackDamage * (1 + playerData.getStrength()/150.0);
                }
                else{
                    attackDamage = 5 * (1 + playerData.getStrength()/150.0);
                }
                critMultiplier = baseCrit * 1.5f;
                if(Util.randomBoolean(calcCrit/100f)){
                    critMultiplier += 1.5;
                }
                attackDamage = Math.max(attackDamage * critMultiplier, attackDamage);
            }
            else if (attacker instanceof LivingEntity entity) {
                attackDamage = attackDamage + EntityMap.get(entity).getDamage();
            }
            if (victim instanceof Player) {
                PlayerData vPlayerData = PlayerMap.PLAYERS.get(victim);
                attackDamage = (int) ((attackDamage - (attackDamage * (vPlayerData.getDefense() / (vPlayerData.getDefense() + 500.0))) * element.getElementalMultiplier()));
            } else if (victim instanceof LivingEntity) {
                if (EntityMap.has(victim)) {
                    EntityData vEntityData = EntityMap.get(victim);
                    attackDamage = (int) (attackDamage - attackDamage * (vEntityData.getDefense() / (vEntityData.getDefense() + 500.0)));
                } else {
                    Util.log(Chat.format("&cELEMENTAL DAMAGE EVENT: &7Entity not found"));
                }
            } else {
                attackDamage = damage;
            }
        }
        if(attackDamage < 0){
            attackDamage = 1;
        }
        this.finalDamage = attackDamage;
    }

    public void apply() {
        if(!victim.getPersistentDataContainer().has(Util.getNamespacedKey("invulnerable")) &&
                !victim.getPersistentDataContainer().has(Util.getNamespacedKey("ignore"))) {
            calculate();
            Bukkit.getPluginManager().callEvent(this);
        }
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Entity getAttacker() {
        return attacker;
    }

    public Entity getVictim() {
        return victim;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
    public int getCritAmount() {
        return critAmount;
    }

    public EntityDamageEvent.DamageCause getDamageCause() {
        return damageCause;
    }

    public ItemStack getWeapon() {
        return weapon;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLER_LIST;
    }
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public double getFinalDamage() {
        return finalDamage;
    }

}
