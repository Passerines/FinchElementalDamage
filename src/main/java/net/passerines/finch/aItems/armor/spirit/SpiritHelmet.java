package net.passerines.finch.aItems.armor.spirit;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.events.ElementalDamageEvent;
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
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SpiritHelmet extends FinchArmor implements FinchCraftableItem, Listener {

    public SpiritHelmet() {
        super("SpiritHelmet");
        this.health = 200;
        this.mana = 1400;
        this.healthRegen = 30;
        this.armorSetName = "spiritset";
        displayName = Chat.formatC("&bSpirit Helmet");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        this.lore = Chat.formatC(lore);
    }

    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setUnbreakable(true);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
    @EventHandler
    public void checkSet(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        int setItems = Util.getArmorSet(player, armorSetName);
        if(setItems == 2 && playerData.getArmorBonus().getOrDefault(armorSetName, 0) < 2){
            playerData.setManaRegen(playerData.getManaRegen() + 10);
            playerData.setManaMax(playerData.getManaMax() + 1000);
            playerData.setHealthMax(playerData.getHealthMax() + 100);
            playerData.setArmorBonus(armorSetName, 2);
        }
        if(playerData.getArmorBonus().getOrDefault(armorSetName, 0) == 2 && setItems != 2){
            playerData.setManaRegen(playerData.getManaRegen() - 10);
            playerData.setManaMax(playerData.getManaMax() - 1000);
            playerData.setHealthMax(playerData.getHealthMax() - 100);
            playerData.removeArmorBonus(armorSetName);
        }
    }

    public void registerRecipe() {
        ItemStack item = ItemManager.ITEM_HASH_MAP.get("Ectoplasm").getItem();
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, "AAA", "A A", "   " , item);
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "spirithelmet0", "   ", "AAA", "A A" , item);
        finchRecipe.addRecipe();
        finchRecipe0.addRecipe();
    }
}
