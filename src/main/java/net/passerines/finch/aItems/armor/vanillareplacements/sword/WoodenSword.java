package net.passerines.finch.aItems.armor.vanillareplacements.sword;

import net.passerines.finch.FinchCraftableItem;
import net.passerines.finch.FinchElementalDamage;
import net.passerines.finch.attacks.Slash;
import net.passerines.finch.attacks.ThrowBlade;
import net.passerines.finch.data.Cooldown;
import net.passerines.finch.events.ElementalDamageEvent;
import net.passerines.finch.itemmanaging.FinchRecipe;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerData;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wood;

import java.util.ArrayList;

public class WoodenSword extends FinchWeapon implements Listener, FinchCraftableItem {
    Cooldown<Player> cd = new Cooldown<>(60);
    public WoodenSword() {
        super("WoodenSword");
        this.attack = 10;
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
        ArrayList<String> lore = new ArrayList<>();
        lore.add(STATS);
        lore.add(" ");
        lore.add(ENCHANTS);
        lore.add("&6Ability: &fNoob's Blessing");
        lore.add("&7Right Clicking Heals 5 HP");
        this.lore = Chat.formatC(lore);
        Bukkit.getPluginManager().registerEvents(this, FinchElementalDamage.inst());
    }
   @EventHandler
   public void abilityTrigger(PlayerInteractEvent playerInteractEvent){
        Player player = playerInteractEvent.getPlayer();
        PlayerData playerData = PlayerMap.PLAYERS.get(player);
        if(cd.isOffCooldown(player)){
            playerData.heal(5);
        }
   }
    @Override
    public void registerRecipe() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemStack wood = new ItemStack(Material.LEGACY_WOOD);
        FinchRecipe finchRecipe = new FinchRecipe(getItem(), id, " A ", " A ", " B " , wood, stick);
        finchRecipe.addRecipe();
        FinchRecipe finchRecipe0 = new FinchRecipe(getItem(), "woodensword0", "  A", "  A", "  B" , wood, stick);
        finchRecipe0.addRecipe();
        FinchRecipe finchRecipe1 = new FinchRecipe(getItem(), "woodensword1", "A  ", "A  ", "B  " , wood, stick);
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
