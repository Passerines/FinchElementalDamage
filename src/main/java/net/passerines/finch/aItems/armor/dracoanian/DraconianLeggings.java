package net.passerines.finch.aItems.armor.dracoanian;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class DraconianLeggings extends FinchArmor implements FinchCraftableItem {

    public DraconianLeggings() {
        super("DraconianLeggings");
        this.defense = 220;
        this.health = 145;
        this.strength = 25;
        displayName = Chat.formatC("&4Draconian Leggings");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.MAROON);
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("DragonScale").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "A A" , item);
        finchRecipe.addRecipe();
    }
}
