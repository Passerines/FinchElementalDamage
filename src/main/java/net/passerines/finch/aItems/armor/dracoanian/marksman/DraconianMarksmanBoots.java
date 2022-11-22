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

public class DraconianMarksmanBoots extends FinchArmor implements FinchCraftableItem {

    public DraconianMarksmanBoots() {
        super("DraconianMarksmanBoots");
        this.defense = 140;
        this.health = 70;
        this.bowDamage = 40;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.MAROON);
        itemMeta.displayName(Chat.formatC("&4Draconian Marksman Boots"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f+"+ (int) this.health)));
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
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "   ", "A A", "A A" , item);
        finchRecipe.addRecipe();
    }
}
