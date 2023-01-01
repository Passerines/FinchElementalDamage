package net.passerines.finch.recipebook;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class RecipeBookDefault implements Listener {
    private static final ItemStack placeholder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    static {
        ItemMeta meta = placeholder.getItemMeta();
        meta.displayName(Chat.formatC(" "));
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("unmovable"), PersistentDataType.BYTE, (byte) 1);
        placeholder.setItemMeta(meta);
    }
    private static final ItemStack placeholder1 = new ItemStack(Material.RED_BED);
    static {
        ItemMeta meta = placeholder1.getItemMeta();
        meta.displayName(Chat.formatC("Back"));
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("unmovable"), PersistentDataType.BYTE, (byte) 1);
        placeholder1.setItemMeta(meta);
    }
    private static final ItemStack placeholder2 = new ItemStack(Material.BARRIER);
    static {
        ItemMeta meta = placeholder2.getItemMeta();
        meta.displayName(Chat.formatC("Close"));
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("unmovable"), PersistentDataType.BYTE, (byte) 1);
        placeholder2.setItemMeta(meta);
    }
    private final FinchElementalDamage plugin = FinchElementalDamage.inst();

    private static final Inventory gui = Bukkit.createInventory(null, 9, Component.text("RecipeBook"));
    static{
        ItemStack weaponIcon = new ItemStack(Material.IRON_SWORD);
        ItemMeta weaponIconItemMeta = weaponIcon.getItemMeta();
        weaponIconItemMeta.displayName(Chat.formatC("Weapon Recipes"));
        weaponIconItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        weaponIcon.setItemMeta(weaponIconItemMeta);
        //
        ItemStack armorIcon = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta armorIconItemMetaItemMeta = armorIcon.getItemMeta();
        armorIconItemMetaItemMeta.displayName(Chat.formatC("Armor Recipes"));
        armorIconItemMetaItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        armorIcon.setItemMeta(armorIconItemMetaItemMeta);
        //
        ItemStack trinketIcon = new ItemStack(Material.DIAMOND_HORSE_ARMOR);
        ItemMeta trinketItemMeta = armorIcon.getItemMeta();
        trinketItemMeta.displayName(Chat.formatC("Trinket Recipes"));
        trinketItemMeta.setCustomModelData(202);
        trinketIcon.setItemMeta(trinketItemMeta);
        gui.setItem(0, weaponIcon);
        gui.setItem(1, placeholder);
        gui.setItem(2, armorIcon);
        gui.setItem(3, placeholder);
        gui.setItem(4, trinketIcon);
        gui.setItem(5, placeholder);
        gui.setItem(6, placeholder);
        gui.setItem(7, placeholder);
        gui.setItem(8, placeholder);
    }
    private static final ArrayList<Inventory> weaponGui = new ArrayList<>();
    private static final ArrayList<Inventory> armorGui = new ArrayList<>();
    private static final ArrayList<Inventory> trinketGui = new ArrayList<>();
    private static final ArrayList<Inventory> utilGui = new ArrayList<>();
    private static final ArrayList<Inventory> miscGui = new ArrayList<>();
    private static final ArrayList<ShapedRecipe> weaponRecipes = new ArrayList<>();
    private static final ArrayList<ShapedRecipe> armorRecipes = new ArrayList<>();
    private static final ArrayList<ShapedRecipe> trinketRecipes = new ArrayList<>();
    private static final ArrayList<ShapedRecipe> utilRecipes = new ArrayList<>();
    private static final ArrayList<ShapedRecipe> miscRecipes = new ArrayList<>();



    public RecipeBookDefault(){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent click) {
        if(click.getClickedInventory() != null) {
            Player player = (Player) click.getWhoClicked();
            Inventory inv = player.getOpenInventory().getTopInventory();
            if (inv.equals(gui) || weaponGui.contains(inv) || armorGui.contains(inv) || trinketGui.contains(inv) || utilGui.contains(inv) || miscGui.contains(inv)) {
                click.setCancelled(true);
                if(inv.equals(gui)){
                    switch (click.getSlot()) {
                        case 0 -> player.openInventory(weaponGui.get(0));
                        case 2 -> player.openInventory(armorGui.get(0));
                        case 4 -> player.openInventory(trinketGui.get(0));
                        //case 6 -> player.openInventory(utilGui.get(0));
                        //case 8 -> player.openInventory(miscGui.get(0));
                    }
                }
                else{
                    if(weaponGui.contains(inv)){
                        int page = weaponGui.indexOf(inv);
                        int slot = (page * 45) + click.getSlot();
                        if(click.getSlot() < 45 && slot < weaponRecipes.size()) {
                            RecipeBookPage recipeBookPage = new RecipeBookPage(weaponRecipes.get(slot), weaponGui.get(page));
                            recipeBookPage.openRecipe(player);
                        }
                        else if(click.getSlot() == 48){
                            player.openInventory(gui);
                        }
                        else if(click.getSlot() == 49){
                            player.closeInventory();
                        }
                    }
                    else if(armorGui.contains(inv)){
                        int page = armorGui.indexOf(inv);
                        int slot = (page * 45) + click.getSlot();
                        if(click.getSlot() <= 45 && slot <= armorRecipes.size()) {
                            RecipeBookPage recipeBookPage = new RecipeBookPage(armorRecipes.get(slot), armorGui.get(page));
                            recipeBookPage.openRecipe(player);
                        }
                        else if(click.getSlot() == 48){
                            player.openInventory(gui);
                        }
                        else if(click.getSlot() == 49){
                            player.closeInventory();
                        }
                    }
                    else if(trinketGui.contains(inv)){
                        int page = trinketGui.indexOf(inv);
                        int slot = (page * 45) + click.getSlot();
                        if(click.getSlot() <= 45 && slot <= trinketRecipes.size()) {
                            RecipeBookPage recipeBookPage = new RecipeBookPage(trinketRecipes.get(slot), trinketGui.get(page));
                            recipeBookPage.openRecipe(player);
                        }
                        else if(click.getSlot() == 48){
                            player.openInventory(gui);
                        }
                        else if(click.getSlot() == 49){
                            player.closeInventory();
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void dragEvent(InventoryInteractEvent click) {
        Inventory inv = click.getInventory();
        if (inv.equals(gui) || weaponGui.contains(inv) || armorGui.contains(inv) || trinketGui.contains(inv) || utilGui.contains(inv) || miscGui.contains(inv)) {
            click.setCancelled(true);
        }
    }
    public static void registerRecipe(ShapedRecipe recipe){
        ItemStack item = recipe.getResult();
        if(Util.getFinchItem(item) instanceof FinchWeapon && (weaponRecipes.size() == 0 || !item.equals(weaponRecipes.get(weaponRecipes.size() - 1).getResult()))){
            addTo(recipe, weaponRecipes, weaponGui, "Weapon Recipes");
        }
        else if(Util.getFinchItem(item) instanceof FinchArmor && (armorRecipes.size() == 0 || !item.equals(armorRecipes.get(armorRecipes.size() - 1).getResult()))){
            addTo(recipe, armorRecipes, armorGui, "Armor Recipes");
        }
        else if(Util.getFinchItem(item) instanceof FinchTrinkets && (trinketRecipes.size() == 0 || !item.equals(trinketRecipes.get(trinketRecipes.size() - 1).getResult()))){
            addTo(recipe, trinketRecipes, trinketGui, "Trinket Recipes");
        }
    }
    public static void addTo(ShapedRecipe recipe, ArrayList<ShapedRecipe> recipes, ArrayList<Inventory> inventory, String s){
        ItemStack item = recipe.getResult();
        int itemIndex = recipes.size();
        int page = itemIndex/45;
        int slot = itemIndex%45;
        recipes.add(recipe);
        if(page >= inventory.size()){
            Inventory inv = Bukkit.createInventory(null, 54, Component.text(s + " [" + (page + 1) + "]" ));
            inv.setItem(45, placeholder);
            inv.setItem(46, placeholder);
            inv.setItem(47, placeholder);
            inv.setItem(48, placeholder1);
            inv.setItem(49, placeholder2);
            inv.setItem(51, placeholder);
            inv.setItem(52, placeholder);
            inv.setItem(53, placeholder);
            inventory.add(inv);

        }
        inventory.get(page).setItem(slot, item);
    }
    public static void openDefault(Player player){
        player.openInventory(gui);
    }
}
