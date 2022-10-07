package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ElectroVision extends FinchTrinkets {
    public ElectroVision() {
        super("ElectroVision");
        this.health = 300;
        this.electro = 0.5;
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(Chat.formatC("&dElectro Vision"));
        item.setItemMeta(itemMeta);
        return writeId(item);
    }
}
