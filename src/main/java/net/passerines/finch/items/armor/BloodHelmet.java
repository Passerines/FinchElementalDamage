package net.passerines.finch.items.armor;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Util;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BloodHelmet extends FinchItem {
    public static final NamespacedKey bloodhelmet = Util.getNamespacedKey("bloodhelmet");
    @EventHandler
    public void OnEquip(PlayerArmorChangeEvent changeEvent){
        PlayerData vPlayerData = PlayerMap.PLAYERS.get((changeEvent.getPlayer()));
        ItemStack newItem = changeEvent.getNewItem();
        ItemStack oldItem = changeEvent.getOldItem();
        if(newItem.hasItemMeta() && newItem.getItemMeta().getPersistentDataContainer().has(bloodhelmet)){
            vPlayerData.setDefense(vPlayerData.getDefense() + 100);
        }
        else if(oldItem.hasItemMeta() && oldItem.getItemMeta().getPersistentDataContainer().has(bloodhelmet)) {
            vPlayerData.setDefense(vPlayerData.getDefense() - 100);
        }
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        item.getItemMeta().getPersistentDataContainer().set(bloodhelmet, PersistentDataType.BYTE, ((byte)1));
        return item;
    }
}
