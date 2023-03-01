
package net.passerines.finch.reforge;

import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.items.FinchEquipment;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ReforgeMenu implements Listener {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();
    private Inventory reforgeMenu;
    Player player;

    private final int cooldownTimeMilliseconds = 6000;
    public ReforgeMenu() {
        reforgeMenu = Bukkit.createInventory(null, 9, Component.text("Reforge Menu"));
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }

    public void open(Player player){
        player.openInventory(reforgeMenu);
    }
    @EventHandler
    public void clickEvent(InventoryClickEvent click) {
        if(click.getClickedInventory() != null && click.getClickedInventory().equals(reforgeMenu)) {
            if(click.getCurrentItem() != null && click.getCurrentItem().hasItemMeta() && click.getCurrentItem().getItemMeta().getPersistentDataContainer().has(Util.getNamespacedKey("unmovable"))) {
                click.setCancelled(true);
            }
            if(click.getCurrentItem() != null && click.getCurrentItem().hasItemMeta() && click.getCurrentItem().getItemMeta().getPersistentDataContainer().has(Util.getNamespacedKey("reforge"))) {
                click.getWhoClicked().sendMessage("Reforging");
                if(Util.getFinchItem(click.getClickedInventory().getItem(4)) instanceof FinchEquipment finchEquipment){
                    click.getWhoClicked().sendMessage("Reforging... " + finchEquipment.getItem().displayName());
                    if(Util.getPrefix(finchEquipment.getItem()) == null) {
                        double percentage = Math.random();
                        int randomTier = 1;
                        if (percentage <= 0.05) {
                            randomTier = 3;
                        } else if (percentage >= 0.85) {
                            randomTier = 2;
                        }
                        ArrayList<ItemPrefix> typePrefixList = PrefixManager.getPrefixes(Util.getItemPrefixType(finchEquipment.getItem()), randomTier);
                        int random = Util.rand(0, typePrefixList.size() - 1);
                        ItemPrefix prefix = typePrefixList.get(random);
                        prefix.applyPrefix(finchEquipment.getItem());
                    }
                }
                click.setCancelled(true);
            }
        }
    }
    public void initialize() {
        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta itemMeta = placeholder.getItemMeta();
        itemMeta.displayName(Chat.formatC(" "));
        itemMeta.getPersistentDataContainer().set(Util.getNamespacedKey("unmovable"), PersistentDataType.BYTE, (byte)1 );
        placeholder.setItemMeta(itemMeta);
        ItemStack reforgeButton = new ItemStack(Material.IRON_AXE);
        ItemMeta itemMeta0 = reforgeButton.getItemMeta();
        itemMeta0.getPersistentDataContainer().set(Util.getNamespacedKey("reforge"), PersistentDataType.BYTE, (byte)1);
        itemMeta0.displayName(Chat.formatC("Reforge Item"));
        reforgeButton.setItemMeta(itemMeta0);
        reforgeMenu.setItem(0, placeholder);
        reforgeMenu.setItem(1, placeholder);
        reforgeMenu.setItem(2, placeholder);
        reforgeMenu.setItem(3, reforgeButton);
        reforgeMenu.setItem(5, placeholder);
        reforgeMenu.setItem(6, placeholder);
        reforgeMenu.setItem(7, placeholder);
        reforgeMenu.setItem(8, placeholder);

    }
}
