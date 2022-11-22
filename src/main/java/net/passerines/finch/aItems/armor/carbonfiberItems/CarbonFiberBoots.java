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

public class CarbonFiberBoots extends FinchArmor implements FinchCraftableItem {
    public CarbonFiberBoots() {
        super("CarbonFiberBoots");
        this.defense = 100;
        this.health = 15;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_BOOTS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fCarbon Fiber Boots"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f+" + (int) this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+" + this.defense)));
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack leather = ItemManager.ITEM_HASH_MAP.get("CarbonFiber").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "   ", "A A", "A A" , leather);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "carbonboots0", "   ", "AAA", "A A" , leather);
        finchRecipe0.addRecipe();
    }
}
