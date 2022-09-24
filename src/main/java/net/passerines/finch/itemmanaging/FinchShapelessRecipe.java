package net.passerines.finch.itemmanaging;

import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.List;

public class FinchShapelessRecipe {
    private String namespacedKey;
    private ItemStack itemResult;
    private List<ItemStack> itemStacks;


    public FinchShapelessRecipe(ItemStack itemResult, String namespacedKey, String shape1, String shape2, String shape3, ItemStack... itemStacks){
        this.namespacedKey = namespacedKey;
        this.itemResult = itemResult;
        this.itemStacks = List.of(itemStacks);
    }
    public void addRecipe(){
        ShapelessRecipe asRecipe = new ShapelessRecipe(Util.getNamespacedKey(namespacedKey), itemResult);
        for (ItemStack itemStack : itemStacks) {
            RecipeChoice recipeChoice = new RecipeChoice.ExactChoice(itemStack);
            asRecipe.addIngredient(recipeChoice);
        }
        Bukkit.addRecipe(asRecipe);
    }
}
