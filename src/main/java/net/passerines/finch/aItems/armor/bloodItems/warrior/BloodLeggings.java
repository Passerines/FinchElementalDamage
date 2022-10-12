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

public class BloodLeggings extends FinchArmor implements FinchCraftableItem {

    public BloodLeggings() {
        super("BloodLeggings");
        this.defense = 20;
        this.health = -10;
        this.damage = 7;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&cBlood Leggings"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&cHealth: &f-10")));
        lore.add(Component.text(Chat.format("&aDefense: &f+20")));
        lore.add(Component.text(Chat.format("&4Damage: &f+7" )));
        itemMeta.lore(lore);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
    @Override
    public void registerRecipe() {
        ItemStack iron = ItemManager.ITEM_HASH_MAP.get("BloodIron").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "A A" , iron);
        finchRecipe.addRecipe();
    }
}
