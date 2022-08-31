package net.passerines.finch.items;


import net.passerines.finch.FinchElementalDamage;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public abstract class FinchItem implements Listener {
    private FinchElementalDamage plugin = FinchElementalDamage.getPlugin(FinchElementalDamage.class);
    public FinchItem(){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    public abstract ItemStack getItem();
}
