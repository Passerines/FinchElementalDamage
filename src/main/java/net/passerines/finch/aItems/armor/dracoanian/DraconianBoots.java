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

public class DraconianBoots extends FinchArmor implements FinchCraftableItem {

    public DraconianBoots() {
        super("DraconianBoots");
        this.defense = 155;
        this.health = 80;
        this.damage = 35;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
        itemMeta.setColor(Color.MAROON);
        itemMeta.displayName(Chat.formatC("&4Draconian Boots"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f+"+ (int) this.health)));
        lore.add(Component.text(Chat.format("&aDefense: &f+"+this.defense)));
        lore.add(Component.text(Chat.format("&4Damage: &f+"+this.damage)));
        itemMeta.setUnbreakable(true);
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("DragonSkin").getItem();
        ItemStack item0 = ItemManager.ITEM_HASH_MAP.get("DragonScale").getItem();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), id, "   ", "B B", "A A" , item, item0);
        finchRecipe0.addRecipe();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), "dracoboots0", "B B", "A A", "   " , item, item0);
        finchRecipe.addRecipe();
    }
}
