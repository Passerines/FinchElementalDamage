package net.passerines.finch.aItems.armor.dracoanian;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class DraconianPlate extends FinchArmor implements FinchCraftableItem {

    public DraconianPlate() {
        super("DraconianPlate", 4);
        this.defense = 450;
        this.health = 180;
        displayName = Chat.formatC("&4Draconian Plate");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("DragonScale").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "A A", "AAA", "AAA" , item);
        finchRecipe.addRecipe();
    }
}
