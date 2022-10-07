package net.passerines.finch.aItems.armor.bloodItems.mage;

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

public class BloodMageChestplate extends FinchArmor implements FinchCraftableItem {

    public BloodMageChestplate() {
        super("BloodMageChestplate");
        this.defense = 25;
        this.health = -10;
        this.mana = 75;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBloodMage Chestplate"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format(" ")));
        lore.add(Component.text(Chat.format("&cHealth: &f-10")));
        lore.add(Component.text(Chat.format("&aDefense: &f+25")));
        lore.add(Component.text(Chat.format("&bMana: &f+70")));
        lore.add(Component.text(Chat.format(" ")));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    public void registerRecipe() {
        ItemStack iron = ItemManager.ITEM_HASH_MAP.get("MagicBloodIron").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "A A", "AAA", "AAA" , iron);
        finchRecipe.addRecipe();
    }
}

