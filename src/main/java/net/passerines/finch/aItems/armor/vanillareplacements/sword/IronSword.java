package net.passerines.finch.aItems.armor.vanillareplacements.sword;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class IronSword extends FinchWeapon implements Listener, FinchCraftableItem {
    Cooldown<Player> cd = new Cooldown<>(60);
    public IronSword() {
        super("IronSword");
        this.attack = 20;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &fNovice's Blessing");
        lore.add("&7Right Clicking Heals 15 HP");
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
   @EventHandler
   public void abilityTrigger(PlayerInteractEvent playerInteractEvent){
        Player player = playerInteractEvent.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(cd.isOffCooldown(player)){
            playerData.heal(15);
        }
   }
    @Override
    public void registerRecipe() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, " A ", " A ", " B " , iron, stick);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "ironsword0", "  A", "  A", "  B" , iron, stick);
        finchRecipe0.addRecipe();
        FinchRecipe finchRecipe1 = new FinchRecipe(getItem(), "ironsword1", "A  ", "A  ", "B  " , iron, stick);
        finchRecipe1.addRecipe();
    }
    @Override
    public ItemStack getItem() {
        ItemStack item = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(itemMeta);
        format(item);
        return writeId(item);
    }
}
