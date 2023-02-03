package net.passerines.finch.aItems.armor.bloodItems.mage;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class BloodMageHelmet extends FinchArmor implements FinchCraftableItem, Listener {

    public BloodMageHelmet() {
        super("BloodMageHelmet", 1);
        this.armorSetName = "BloodMage";
        this.defense = 15;
        this.health = -5;
        this.mana = 45;
        displayName = Chat.formatC("&cBloodMage Helmet");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());

    }
    @EventHandler
    public void checkSet(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        int setItems = Util.getArmorSet(player, armorSetName);
        player.sendMessage("&bPlayerArmorChangeEvent called from BloodMageHelmet");
        if(Util.isSafe(event.getOldItem())) {
            player.sendMessage(Chat.formatC("&bOld Item ").append(event.getOldItem().getItemMeta().displayName()).append(Chat.formatC(" &bSet &7" + Util.getArmorSet(event.getOldItem()))));
        } else {
            player.sendMessage("Old Item Null");
        }
        if(Util.isSafe(event.getNewItem())) {
            player.sendMessage(Chat.formatC("&bNew Item ").append(event.getNewItem().getItemMeta().displayName()).append(Chat.formatC(" &bSet &7" + Util.getArmorSet(event.getNewItem()))));
        } else {
            player.sendMessage("New item null");
        }
        player.sendMessage(Chat.formatC("&bNew Set " + setItems + " &bPieces &7"
                + Util.getArmorSet(player.getInventory().getHelmet())
                + " " + Util.getArmorSet(player.getInventory().getChestplate())
                + " " + Util.getArmorSet(player.getInventory().getLeggings())
                + " " + Util.getArmorSet(player.getInventory().getBoots())));

        if(setItems == 4 && playerData.getArmorBonus().getOrDefault(armorSetName, 0) < 4){
            playerData.setManaMax(playerData.getManaMax() + 500);
            playerData.setArmorBonus(armorSetName, 4);
        }
        if(playerData.getArmorBonus().getOrDefault(armorSetName, 0) == 4 && setItems != 4){
            playerData.setManaMax(playerData.getManaMax() - 500);
            playerData.removeArmorBonus(armorSetName);
        }
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.IRON_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }

    @Override
    public void registerRecipe() {
        ItemStack iron = ItemManager.ITEM_HASH_MAP.get("MagicBloodIron").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , iron);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "bloodmagehelmet0", "   ", "AAA", "A A" , iron);
        finchRecipe0.addRecipe();
    }
}
