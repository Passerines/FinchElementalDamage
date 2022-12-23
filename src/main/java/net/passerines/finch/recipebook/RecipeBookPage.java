package net.passerines.finch.recipebook;

import net.kyori.adventure.text.Component;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class RecipeBookPage {
    private static final ItemStack placeholder = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    static {
        ItemMeta meta = placeholder.getItemMeta();
        meta.displayName(Chat.formatC(" "));
        meta.getPersistentDataContainer().set(Util.getNamespacedKey("unmovable"), PersistentDataType.BYTE, (byte) 1);
        placeholder.setItemMeta(meta);
    }
    private ShapedRecipe recipe;
    private Inventory gui;
    public RecipeBookPage(ShapedRecipe recipe){
        this.recipe = recipe;
        gui = Bukkit.createInventory(null, 54, Component.text("Recipe for: " + recipe.getResult().displayName()));
        addItemsRecipe();
    }
    public void addItemsRecipe(){
        for(int i = 0; i < gui.getSize(); i++){
            gui.setItem(i, placeholder);
        }
        String[] strings = recipe.getShape();
        gui.setItem(10, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[0].charAt(0))).getItemStack());
        gui.setItem(11, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[0].charAt(1))).getItemStack());
        gui.setItem(12, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[0].charAt(2))).getItemStack());
        gui.setItem(19, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[1].charAt(0))).getItemStack());
        gui.setItem(20, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[1].charAt(1))).getItemStack());
        gui.setItem(21, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[1].charAt(2))).getItemStack());
        gui.setItem(28, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[2].charAt(0))).getItemStack());
        gui.setItem(29, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[2].charAt(1))).getItemStack());
        gui.setItem(30, ((RecipeChoice.ExactChoice) recipe.getChoiceMap().get(strings[2].charAt(2))).getItemStack());
    }
    public void openRecipe(Player player){
        player.openInventory(gui);
    }
}
