package net.passerines.finch.itemmanaging;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class FinchRecipe {
    private String namespacedKey;
    private ItemStack itemResult;


    public void Recipe(ItemStack itemResult, String namespacedKey){
        this.namespacedKey = namespacedKey;
        this.itemResult = itemResult;

    }
    public void addRecipe(){
        ShapedRecipe asRecipe = new ShapedRecipe(NamespacedKey.minecraft(namespacedKey), itemResult);
        asRecipe.shape(
                "BBB",
                "BOB",
                "BAB"
        );

        asRecipe.setIngredient('B', new RecipeChoice.ExactChoice());
        asRecipe.setIngredient('O', Material.BOW);
        asRecipe.setIngredient('A', Material.SPECTRAL_ARROW);

        Bukkit.addRecipe(asRecipe);
    }
}
