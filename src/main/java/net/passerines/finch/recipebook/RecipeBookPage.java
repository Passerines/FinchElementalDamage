package net.passerines.finch.recipebook;

import io.r2dbc.spi.Parameter;
import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;

public class RecipeBookPage implements Listener {
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
    private ShapedRecipe recipe;
    private Inventory gui;
    private Inventory previousGui;
    public RecipeBookPage(ShapedRecipe recipe, Inventory inventory){
        this.recipe = recipe;
        gui = Bukkit.createInventory(null, 54, Chat.formatC("Recipe for: " + Chat.asLegacy(recipe.getResult().displayName())));
        addItemsRecipe();
        gui.setItem(48, placeholder1);
        gui.setItem(49, placeholder2);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        previousGui = inventory;

    }
    public void addItemsRecipe(){
        for(int i = 0; i < gui.getSize(); i++){
            gui.setItem(i, placeholder);
        }
        String[] strings = recipe.getShape();
        Map<Character, RecipeChoice> choiceMap = recipe.getChoiceMap();
        gui.setItem(10, choiceMap.get(strings[0].charAt(0))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[0].charAt(0))).getItemStack():null);
        gui.setItem(11, choiceMap.get(strings[0].charAt(1))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[0].charAt(1))).getItemStack():null);
        gui.setItem(12, choiceMap.get(strings[0].charAt(2))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[0].charAt(2))).getItemStack():null);
        gui.setItem(19, choiceMap.get(strings[1].charAt(0))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[1].charAt(0))).getItemStack():null);
        gui.setItem(20, choiceMap.get(strings[1].charAt(1))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[1].charAt(1))).getItemStack():null);
        gui.setItem(21, choiceMap.get(strings[1].charAt(2))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[1].charAt(2))).getItemStack():null);
        gui.setItem(28, choiceMap.get(strings[2].charAt(0))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[2].charAt(0))).getItemStack():null);
        gui.setItem(29, choiceMap.get(strings[2].charAt(1))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[2].charAt(1))).getItemStack():null);
        gui.setItem(30, choiceMap.get(strings[2].charAt(2))!=null?((RecipeChoice.ExactChoice) choiceMap.get(strings[2].charAt(2))).getItemStack():null);
        gui.setItem(24, recipe.getResult());

    }
    public void openRecipe(Player player){
        player.openInventory(gui);
    }
    @EventHandler
    public void back(InventoryClickEvent event){
        if(event.getInventory().equals(gui) && event.getSlot() == 48){
            event.getWhoClicked().openInventory(previousGui);
        }
        else if(event.getSlot() == 49){
            event.getWhoClicked().closeInventory();
        }
    }
    @EventHandler
    public void cancelClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory() != null && player.getOpenInventory().getTopInventory().equals(gui)){
            event.setCancelled(true);
        }
    }@EventHandler
    public void cancelDrag(InventoryDragEvent event){
        if(event.getInventory().equals(gui)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void closeGui(InventoryCloseEvent event){
        if(event.getInventory().equals(gui)){
            HandlerList.unregisterAll(this);
        }
    }

}
