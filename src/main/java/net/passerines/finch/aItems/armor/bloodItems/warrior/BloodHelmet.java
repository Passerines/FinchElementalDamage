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

public class BloodHelmet extends FinchArmor implements FinchCraftableItem {

    public BloodHelmet() {
        super("BloodHelmet");
        this.defense = 15;
        this.health = -5;
        this.strength = 6;
        displayName = Chat.formatC("&cBlood Helmet");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Helmet"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f-5")));
        lore.add(Component.text(Chat.format("&aDefense: &f+15")));
        lore.add(Component.text(Chat.format("&4Damage: &f+6" )));
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack iron = ItemManager.ITEM_HASH_MAP.get("BloodIron").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , iron);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "bloodhelmet0", "   ", "AAA", "A A" , iron);
        finchRecipe0.addRecipe();
    }
}
