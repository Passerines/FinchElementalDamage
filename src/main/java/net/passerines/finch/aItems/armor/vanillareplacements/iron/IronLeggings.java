package net.passerines.finch.aItems.armor.vanillareplacements.iron;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class IronLeggings extends FinchArmor implements FinchCraftableItem {
    public IronLeggings() {
        super("ReinforcedLeatherLeggings");
        this.defense = 80;
        this.health = 20;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fReinforced Leather Leggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f+" + (int)this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+" + this.defense)));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack leather = new ItemStack(Material.IRON_INGOT);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "A A" , leather);
        finchRecipe.addRecipe();
    }
}
