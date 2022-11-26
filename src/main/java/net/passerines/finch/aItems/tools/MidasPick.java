package net.passerines.finch.aItems.tools;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MidasPick extends FinchItem implements FinchCraftableItem {
    public MidasPick() {
        super("MidasPick");
    }

    public void breakStone(BlockBreakEvent event){
        if(event.getBlock().getBlockData().getMaterial().equals(Material.STONE)){
            if(id.equals(Util.getId(event.getPlayer().getInventory().getItemInMainHand()))){
                event.getBlock().getLocation().getWorld().dropItem(event.getBlock().getLocation(), new ItemStack(Material.GOLD_NUGGET));
            }
        }
    }



    public ItemStack getItem(){
        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&6Midas Pickaxe"));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text(Chat.format("")));
        lore.add(Component.text(Chat.format("&6Ability: Goldstone")));
        lore.add(Component.text(Chat.format("Whenever you break stone you get 1 gold nugget")));
        lore.add(Component.text(Chat.format("to drop a gold ingot")));
        itemMeta.lore(lore);
        itemMeta.setUnbreakable(true);
        item.setItemMeta(itemMeta);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack goldEssence = ItemManager.ITEM_HASH_MAP.get("GoldEssence").getItem();
        ItemStack apple = new ItemStack(Material.GOLDEN_APPLE);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", " B ", " B " , goldEssence, apple);
        finchRecipe.addRecipe();
    }
}
