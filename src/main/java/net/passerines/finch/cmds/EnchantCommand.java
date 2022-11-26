package net.passerines.finch.cmds;


import net.passerines.finch.FinchElementalDamage;

import net.passerines.finch.enchants.EnchantManager;
import net.passerines.finch.enchants.ItemEnchants;
import net.passerines.finch.itemmanaging.ItemManager;
import net.passerines.finch.items.FinchItem;
import net.passerines.finch.items.FinchTrinkets;
import net.passerines.finch.items.FinchWeapon;
import net.passerines.finch.players.PlayerMap;
import net.passerines.finch.reforge.ItemPrefix;
import net.passerines.finch.reforge.PrefixManager;
import net.passerines.finch.util.Chat;
import net.passerines.finch.util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static net.passerines.finch.enchants.EnchantManager.ENCHANTS_HASH_MAP;


public class EnchantCommand implements CommandExecutor {
    private FinchElementalDamage plugin = FinchElementalDamage.inst();

    public EnchantCommand() {
        plugin.getCommand("ench").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            ItemStack item = ((Player) sender).getInventory().getItemInMainHand();
            ENCHANTS_HASH_MAP.get("SwordSharpness").applyEnchant(item, 1);
        }
        return true;
    }
}

