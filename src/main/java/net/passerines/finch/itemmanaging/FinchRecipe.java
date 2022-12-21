package net.passerines.finch.itemmanaging;

import net.passerines.finch.recipebook.RecipeBookDefault;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;

public class FinchRecipe {
    private String namespacedKey;
    private ItemStack itemResult;
    private List<ItemStack> itemStacks;
    private ShapedRecipe recipe;
    private String shape1,shape2,shape3;


    public FinchRecipe(ItemStack itemResult, String namespacedKey, String shape1, String shape2, String shape3, ItemStack... itemStacks){
        this.namespacedKey = namespacedKey;
        this.itemResult = itemResult;
        this.itemStacks = List.of(itemStacks);
        this.shape1 = shape1;
        this.shape2 = shape2;
        this.shape3 = shape3;
        this.recipe = getRecipe();
    }
    public void addRecipe(){
        Bukkit.addRecipe(recipe);
        RecipeBookDefault.registerRecipe(recipe);
    }
    private ShapedRecipe getRecipe(){
        ShapedRecipe asRecipe = new ShapedRecipe(Util.getNamespacedKey(namespacedKey), itemResult);
        asRecipe.shape(shape1, shape2, shape3);
        for(int i = 0; i < itemStacks.size(); i++){
            asRecipe.setIngredient((char) ('A'+ i), new RecipeChoice.ExactChoice(itemStacks.get(i)));
        }
        return asRecipe;
    }
}
