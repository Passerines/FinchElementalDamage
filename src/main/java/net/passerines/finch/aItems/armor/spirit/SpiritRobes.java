package net.passerines.finch.aItems.armor.spirit;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpiritRobes extends FinchArmor implements FinchCraftableItem, Listener {

    public SpiritRobes() {
        super("SpiritRobes");
        this.health = 400;
        this.mana = 800;
        this.healthRegen = 40;
        displayName = Chat.formatC("&bSpirit Robes");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("Ectoplasm").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "A A", "AAA", "AAA" , item);
        finchRecipe.addRecipe();
    }
}
