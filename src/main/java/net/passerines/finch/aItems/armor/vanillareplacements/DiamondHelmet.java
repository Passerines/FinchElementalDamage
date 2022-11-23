package net.passerines.finch.aItems.armor.vanillareplacements;

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

public class DiamondHelmet extends FinchArmor implements FinchCraftableItem {
    public DiamondHelmet() {
        super("DiamondHelmet");
        this.defense = 90;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&fDiamond Helmet"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("&aDefense: &f+" + this.defense)));
        itemMeta.lore(lore);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack leather = new ItemStack(Material.DIAMOND);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , leather);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "diamondhelmet0", "   ", "AAA", "A A" , leather);
        finchRecipe0.addRecipe();
    }
}
