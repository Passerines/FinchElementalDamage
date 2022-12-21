package net.passerines.finch.recipebook;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
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
        if(click.getClickedInventory() != null && click.getClickedInventory().equals(gui)) {
            click.setCancelled(true);
        }
    }
    public static void registerRecipe(ShapedRecipe recipe){
        ItemStack item = recipe.getResult();
        if(Util.getFinchItem(item) instanceof FinchWeapon){
            int itemIndex = weaponRecipes.size();
            int page = itemIndex/45;
            int slot = itemIndex%45;
            weaponRecipes.add(recipe);
            if(page >= weaponGui.size()){
                weaponGui.add(Bukkit.createInventory(null, 54, Component.text("Weapon Recipes [" + (page + 1) + "]" )));
            }
            weaponGui.get(page).setItem(slot, item);
        }
    }
    public static void openDefault(Player player){
        player.openInventory(gui);
    }
}
