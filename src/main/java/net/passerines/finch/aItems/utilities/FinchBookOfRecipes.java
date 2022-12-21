package net.passerines.finch.aItems.utilities;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.recipebook.RecipeBookDefault;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FinchBookOfRecipes extends FinchTrinkets implements Listener, FinchCraftableItem {
    public FinchBookOfRecipes() {
        super("FinchBookOfRecipes");
        this.health = 5;
        displayName = Chat.formatC("&fFinch Book of Recipes");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
    @EventHandler
    public void openMenu(PlayerInteractEvent event){
        if(id.equals(Util.getId(event.getPlayer().getInventory().getItemInMainHand()))){
            RecipeBookDefault.openDefault(event.getPlayer());
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = item.getItemMeta();
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack book = new ItemStack(Material.BOOK);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "AAA", "AAA" , book);
        finchRecipe.addRecipe();
    }
}
