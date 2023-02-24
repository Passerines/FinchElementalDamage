package net.passerines.finch.aItems.armor.bloodItems.warrior;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.kyori.adventure.text.Component;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchArmor;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BloodHelmet extends FinchArmor implements FinchCraftableItem {

    public BloodHelmet() {
        super("BloodHelmet", 1);
        this.defense = 20;
        this.health = -5;
        this.strength = 6;
        this.armorSetName = "BloodMage";
        displayName = Chat.formatC("&cBlood Helmet");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }
    @EventHandler
    public void checkSet(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        int setItems = Util.getArmorSet(player, armorSetName);
        if(setItems == 4 && playerData.getArmorBonus().getOrDefault(armorSetName, 0) < 4){
            playerData.setManaMax(playerData.getStrength() + 100);
            playerData.setArmorBonus(armorSetName, 4);
        }
        if(playerData.getArmorBonus().getOrDefault(armorSetName, 0) == 4 && setItems != 4){
            playerData.setManaMax(playerData.getStrength() - 100);
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
        ItemStack iron = ItemManager.ITEM_HASH_MAP.get("BloodIron").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , iron);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "bloodhelmet0", "   ", "AAA", "A A" , iron);
        finchRecipe0.addRecipe();
    }
}
