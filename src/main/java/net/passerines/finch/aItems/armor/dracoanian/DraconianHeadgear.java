package net.passerines.finch.aItems.armor.dracoanian;

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

public class DraconianHeadgear extends FinchArmor implements FinchCraftableItem {

    public DraconianHeadgear() {
        super("DraconianHeadgear", 4);
        this.defense = 140;
        this.health = 90;
        this.strength = 40;
        displayName = Chat.formatC("&4Draconian Headgear");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("DragonScale").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , item);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "dracoheadgear0", "   ", "AAA", "A A" , item);
        finchRecipe0.addRecipe();
    }
}
