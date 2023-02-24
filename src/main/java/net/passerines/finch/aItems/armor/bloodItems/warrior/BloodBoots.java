package net.passerines.finch.aItems.armor.bloodItems.warrior;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BloodBoots extends FinchArmor implements FinchCraftableItem {

    public BloodBoots() {
        super("BloodBoots", 1);
        this.defense = 25;
        this.health = -5;
        this.strength = 5;
        this.armorSetName = "BloodMage";
        displayName = Chat.formatC("&cBlood Boots");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack iron = ItemManager.ITEM_HASH_MAP.get("BloodIron").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "   ", "A A", "A A" , iron);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "bloodboots0", "A A", "A A", "   " , iron);
        finchRecipe0.addRecipe();
    }
}
