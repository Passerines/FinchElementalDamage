package net.passerines.finch.itemmanaging;

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


    public FinchRecipe(ItemStack itemResult, String namespacedKey, ItemStack... itemStacks){
        this.namespacedKey = namespacedKey;
        this.itemResult = itemResult;
        this.itemStacks = List.of(itemStacks);

    }
    public void addRecipe(){
        ShapedRecipe asRecipe = new ShapedRecipe(NamespacedKey.minecraft(namespacedKey), itemResult);
        asRecipe.shape(
                "ABC",
                "DEF",
                "GHI"
        );
        for(int i = 0; i < 9; i++){
            asRecipe.setIngredient((char) ('A'+ i), new RecipeChoice.ExactChoice(itemStacks.get(i)));
        }
        Bukkit.addRecipe(asRecipe);
    }
}
