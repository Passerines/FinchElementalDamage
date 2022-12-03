package net.passerines.finch.items.armor;

import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.util.Chat;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ElectroVision extends FinchTrinkets {
    public ElectroVision() {
        super("ElectroVision");
        this.health = 300;
        this.electro = 0.5;
        displayName = Chat.formatC("&dElectro Vision");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        format(item);
        return writeId(item);
    }
}
