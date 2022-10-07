package net.passerines.finch.items;


import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.util.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public abstract class FinchItem {

    public static final NamespacedKey ID_KEY = Util.getNamespacedKey("id");
    protected String id;
    protected int rarity;

    public FinchItem(String id, int rarity){
        this.id = id;
        this.rarity = rarity;
        ItemManager.ITEM_HASH_MAP.put(id, this);
    }
    public FinchItem(String id){
        this(id,0);
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
    public int getRarity(){return rarity;}
    public void setRarity(int rarities){rarity = rarities;}
}
