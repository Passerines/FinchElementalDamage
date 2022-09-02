package net.passerines.finch.items;


import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public abstract class FinchItem {

    public static final NamespacedKey ID_KEY = Util.getNamespacedKey("id");
    protected String id;

    public FinchItem(String id){
        this.id = id;
        ItemManager.ITEM_HASH_MAP.put(id, this);
    }
    public abstract ItemStack getItem();

    protected ItemStack writeId(ItemStack item) {
        if(Util.isSafe(item)) {
            ItemMeta meta = item.getItemMeta();
            meta.getPersistentDataContainer().set(ID_KEY, PersistentDataType.STRING, id);
            item.setItemMeta(meta);
        }
        return item;
    }
}
