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

        int oldSetItems = Util.getArmorSet(player, armorSetName);
        if(Util.getFinchItem(event.getOldItem()) instanceof FinchArmor finchArmor){
            if(finchArmor.getArmorSetName() != null && finchArmor.getArmorSetName().equals(armorSetName) && !((FinchArmor) Util.getFinchItem(event.getNewItem())).getArmorSetName().equals(armorSetName)){
                oldSetItems++;
            }
        }

        int deduct = 0;
        switch (oldSetItems) {
            case 3 -> deduct = 20;
            case 4 -> deduct = 40;
        }
        playerData.setHealthRegen(playerData.getMana() - deduct);
        int buff = 0;
        switch (Util.getArmorSet(player, armorSetName)) {
            case 2 -> buff = 20;
            case 3 -> buff = 40;
            case 4 -> buff = 80;
        }
        playerData.setHealthRegen(playerData.getMana() + buff);
        player.sendMessage(Chat.formatC("You are wearing " + Util.getArmorSet(player, armorSetName) + " Pieces of " + armorSetName));
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
