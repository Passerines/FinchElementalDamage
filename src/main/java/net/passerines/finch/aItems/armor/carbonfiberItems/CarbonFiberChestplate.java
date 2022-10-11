package net.passerines.finch.aItems.armor.carbonfiberItems;

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

public class CarbonFiberChestplate extends FinchArmor implements FinchCraftableItem {
    public CarbonFiberChestplate() {
        super("CarbonFiberChestplate");
        this.defense = 160;
        this.health = 15;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fCarbon Fiber Chestplate"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f+" + (int) this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+" + this.defense)));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack leather = ItemManager.ITEM_HASH_MAP.get("CarbonFiber").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "A A", "AAA", "AAA" , leather);
        finchRecipe.addRecipe();
    }
}
