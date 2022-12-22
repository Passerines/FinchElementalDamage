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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
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
    private final FinchElementalDamage plugin = FinchElementalDamage.inst();

    private static final Inventory gui = Bukkit.createInventory(null, 9, Component.text("RecipeBook"));
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
            Inventory inv = click.getClickedInventory();
            Player player = (Player) click.getWhoClicked();
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
            }
        }
    }
    @EventHandler
    public void dragEvent(InventoryDragEvent click) {
        Inventory inv = click.getInventory();
        if (inv.equals(gui) || weaponGui.contains(inv) || armorGui.contains(inv) || trinketGui.contains(inv) || utilGui.contains(inv) || miscGui.contains(inv)) {
            click.setCancelled(true);
        }
    }
    public static void registerRecipe(ShapedRecipe recipe){
        ItemStack item = recipe.getResult();
        if(Util.getFinchItem(item) instanceof FinchWeapon){
            addTo(recipe, weaponRecipes, weaponGui, "Weapon Recipes");
        }
        else if(Util.getFinchItem(item) instanceof FinchArmor){
            addTo(recipe, armorRecipes, armorGui, "Armor Recipes");
        }
        else if(Util.getFinchItem(item) instanceof FinchTrinkets){
            addTo(recipe, trinketRecipes, trinketGui, "Trinket Recipes");
        }
    }
    public static void addTo(ShapedRecipe recipe, ArrayList<ShapedRecipe> recipes, ArrayList<Inventory> inventory, String s){
        ItemStack item = recipe.getResult();
        if(Util.getFinchItem(item) instanceof FinchWeapon){
            int itemIndex = recipes.size();
            int page = itemIndex/45;
            int slot = itemIndex%45;
            recipes.add(recipe);
            if(page >= inventory.size()){
                inventory.add(Bukkit.createInventory(null, 54, Component.text(s + " [" + (page + 1) + "]" )));
            }
            inventory.get(page).setItem(slot, item);
        }
    }
    public static void openDefault(Player player){
        player.openInventory(gui);
    }
}
