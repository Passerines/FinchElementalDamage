package net.passerines.finch.util;

import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchItem;

import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Util {

    /*
    Gets a List of online players
     */
    public static List<String> getOnlinePlayerNames() {
        ArrayList<String> names = new ArrayList<>();
        for(Player player : Bukkit.getOnlinePlayers()) {
            names.add(player.getName());
        }
        return names;
    }

    public static void shootArrow(Player player, Sound sound, int rotation, int damage){
        Arrow arrow = player.launchProjectile(Arrow.class);
        arrow.setVelocity(arrow.getVelocity().rotateAroundY(Math.toRadians(rotation)));
        player.getWorld().playSound(player.getLocation(), sound, 1, 1);
        arrow.setShooter(player);
        arrow.setDamage(damage);
    }

    /*
    Returns the first online player that match the given name
     */
    public static Player matchPlayer(String name) {
        List<Player> players = Bukkit.matchPlayer(name);
        if(players.size() > 0) {
            for(Player player : players) {
                if(player.getName().equals(name)) {
                    return player;
                } else {
                    return players.get(0);
                }
            }
        }
        return null;
    }

    /*
    Safely change max health of an entity without triggering the glitch
    where setting a player's max health below half of what it was would
    instantly kill the player
     */
    public static void setMaxHealth(LivingEntity entity, double amount) {
        double maxHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        double health = entity.getHealth();
        if(amount < maxHealth) {
            double targetMaxHealth = Math.max(1, amount);
            while(maxHealth > targetMaxHealth) {
                maxHealth *= 0.51;
                entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHealth);
                entity.setHealth(maxHealth);
            }
        }
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(amount);
        entity.setHealth(Math.min(amount, health));
        if(entity instanceof Player) {
            ((Player)entity).setHealthScale(20);
        }
    }

    //Get a namespaced key of this plugin with the given name
    public static NamespacedKey getNamespacedKey(String name) {
        return new NamespacedKey(FinchElementalDamage.getPlugin(FinchElementalDamage.class), name);
    }
    public static String getId(ItemStack item) {
        if(isSafe(item) && item.getItemMeta().getPersistentDataContainer().has(FinchItem.ID_KEY)) {
            return item.getItemMeta().getPersistentDataContainer().get(FinchItem.ID_KEY, PersistentDataType.STRING);
        } else {
            return null;
        }
    }

    //Log a message in console
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(Chat.format("&7[&bFinchElementalDamage&7] &f" + message));
    }
    public static void printStackTrace() {
        log(Chat.format("&cTracing Starts!"));
        for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
            if(ste.toString().contains("net.passerines")) log(ste.toString());
        }
        log(Chat.format("&cTracing Ends!"));
    }

    //Returns true if item is not null and has item meta
    public static boolean isSafe(ItemStack item) {
        if(item!=null) {
            return item.getItemMeta() != null;
        }
        return false;
    }
    //Returns true if player is not null, is online, and is not dead
    public static boolean isSafe(Player player) {
        return player!=null && player.isOnline() && !player.isDead();
    }
    //player.isOnGround() is not reliable, use this method instead
    public static boolean isOnGround(Player player) {
        return ((Entity)player).isOnGround();
    }

    //These two methods are for creating command autocompletes
    @NotNull
    public static <T extends Collection<? super String>> T copyPartialContains(@NotNull String token, @NotNull Iterable<String> originals, @NotNull T collection) throws UnsupportedOperationException, IllegalArgumentException {
        Validate.notNull(token, "Search token cannot be null");
        Validate.notNull(collection, "Collection cannot be null");
        Validate.notNull(originals, "Originals cannot be null");
        Iterator targets = originals.iterator();
        while(targets.hasNext()) {
            String str = (String)targets.next();
            if (str.toLowerCase().contains(token.toLowerCase())) {
                collection.add(str);
            }
        }
        return collection;
    }
    public static boolean containsIgnoreCase(@NotNull String string, @NotNull String prefix) throws IllegalArgumentException, NullPointerException {
        Validate.notNull(string, "Cannot check a null string for a match");
        return string.length() >= prefix.length() && string.regionMatches(true, 0, prefix, 0, prefix.length());
    }

    //Some random methods
    public static boolean randomBoolean(float trueChance) {
        return Math.random()<trueChance;
    }
    //Generate a random double between min and max, inclusive
    public static double rand(double min, double max) {
        return new Random().nextInt(101)/100.0 * (max - min) + min;
    }
    //Generate a random integer between min and max, inclusive
    public static int rand(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
    public static Color randomColorRainbow() {
        return Color.fromRGB(Util.rand(0, 255), Util.rand(0, 255), Util.rand(0, 255));
    }

}
