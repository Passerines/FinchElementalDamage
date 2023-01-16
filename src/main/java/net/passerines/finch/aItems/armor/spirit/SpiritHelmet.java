package net.passerines.finch.aItems.armor.spirit;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpiritHelmet extends FinchArmor implements FinchCraftableItem, Listener {

    public SpiritHelmet() {
        super("SpiritHelmet");
        this.health = 200;
        this.dexterity = 45;
        this.healthRegen = 30;
        displayName = Chat.formatC("&");
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
    @EventHandler
    public void onHit(ElementalDamageEvent event){
        if(event.getVictim() instanceof Player player){
            if(id.equals(Util.getId(player.getEquipment().getHelmet()))){

            }
        }
    }
    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("DragonSkin").getItem();
        ItemStack item0 = ItemManager.ITEM_HASH_MAP.get("DragonScale").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "A A", "ABA", "AAA" , item, item0);
        finchRecipe.addRecipe();
    }
}
