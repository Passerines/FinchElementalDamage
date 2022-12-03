package net.passerines.finch.util;

import jdk.jfr.Percentage;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchItem;

import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.reforge.PrefixManager;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
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
    public static double getMaxHealth(LivingEntity entity){
        return entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    }

    public static int getEffectiveHealth(int playerHealth, int defense){
        int finalDamage = playerHealth*defense/500 + playerHealth;
        return finalDamage;
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
    //Return the FinchItem of an ItemStack
    public static FinchItem getFinchItem(ItemStack item) {
        return ItemManager.ITEM_HASH_MAP.getOrDefault(getId(item), null);
    }


    //Gets the item prefix enum type
    public static ItemPrefix.Type getItemPrefixType(ItemStack item){
        String Id = getId(item);
        FinchItem finchItem = ItemManager.ITEM_HASH_MAP.get(Id);
        if(finchItem instanceof FinchWeapon){
            return ItemPrefix.Type.WEAPON;
        }
        else if(finchItem instanceof FinchTrinkets){
            return ItemPrefix.Type.TRINKET;
        }
        else if(finchItem instanceof FinchArmor){
            return ItemPrefix.Type.ARMOR;
        }
        else{
            return ItemPrefix.Type.NONE;
        }
    }

    //Gets the prefix that is currently on the item
    public static String getPrefix(ItemStack item) {
        if(isSafe(item) && item.getItemMeta().getPersistentDataContainer().has(Util.getNamespacedKey("prefix"))) {
            return item.getItemMeta().getPersistentDataContainer().get(Util.getNamespacedKey("prefix"), PersistentDataType.STRING);
        } else {
            return null;
        }
    }

    public static HashMap<String, Integer> getEnchants(ItemStack item) {
        HashMap<String, Integer> enchantList = new HashMap<>();
        if(item != null){
            if(item.getItemMeta().getPersistentDataContainer().has(Util.getNamespacedKey("enchant"))){
                String list = item.getItemMeta().getPersistentDataContainer().get(Util.getNamespacedKey("enchant"), PersistentDataType.STRING);
                String[] arrayEnchants = list.split(",");
                for(String enchant : arrayEnchants){
                    String[] level = enchant.split(":");
                    enchantList.put(level[0], Integer.parseInt(level[1]));
                }
            }
        }
        return enchantList;
    }

    //Returns a string that represents a hashmap of enchantments and their respective levels
    //This is for the persistent data container
    public static String getEnchantString(HashMap<String, Integer> map){
        String enchantString = "";
        for(String enchants : map.keySet()){
            enchantString += enchants + ":" + map.get(enchants) + ",";
        }
        if(enchantString.length() > 0){
            enchantString.substring(0, enchantString.length()-1);
        }
        return enchantString;
    }
    //Gets the ItemPrefix that is currently on the item
    public static ItemPrefix getItemPrefix(ItemStack item) {
        String prefix = getPrefix(item);
        return PrefixManager.PREFIX_HASH_MAP.getOrDefault(prefix, null);
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
