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

public class IronChestplate extends FinchArmor implements FinchCraftableItem {
    public IronChestplate() {
        super("ReinforcedLeatherTunic");
        this.defense = 120;
        this.health = 30;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fReinforced Leather Tunic"));
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
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "A A", "AAA", "AAA" , leather);
        finchRecipe.addRecipe();
    }
}
