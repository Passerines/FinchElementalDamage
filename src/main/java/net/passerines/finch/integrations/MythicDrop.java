package net.passerines.finch.integrations;

import io.lumine.mythic.api.adapters.AbstractItemStack;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IItemDrop;
import io.lumine.mythic.bukkit.BukkitAdapter;
import net.passerines.finch.itemmanaging.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class MythicDrop implements IItemDrop {
    private String item;
    public MythicDrop(String item){
        this.item = item;
    }

    @Override
    public AbstractItemStack getDrop(DropMetadata dropMetadata, double v) {
        if(ItemManager.ITEM_HASH_MAP.containsKey(item)){
            ItemStack itemStack = ItemManager.ITEM_HASH_MAP.get(item).getItem();
            itemStack.setAmount((int) v);
            return BukkitAdapter.adapt(itemStack);
        }
        return null;
    }
}
