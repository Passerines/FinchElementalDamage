package net.passerines.finch.aItems.armor.dracoanian.marksman;

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
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;

public class DraconianMarksmanLeggings extends FinchArmor implements FinchCraftableItem {

    public DraconianMarksmanLeggings() {
        super("DraconianMarksmanLeggings");
        this.defense = 205;
        this.health = 130;
        this.bowDamage = 45;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.MAROON);
        itemMeta.displayName(Chat.formatC("&4Draconian Marksman Leggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f+"+(int) this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+"+this.defense)));
        lore.add(Component.text(Chat.format("&4Ranged Damage: &f+"+this.bowDamage)));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("DragonSkin").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "A A" , item);
        finchRecipe.addRecipe();
    }
}
