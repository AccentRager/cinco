package com.stigglespatch.main.Custom.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Random;

public class Pickaxes implements Listener {

    private int rollNumber(int min, int max){
        Random rand = new Random();
        int randomNumber = rand.nextInt(max) + min;

        return randomNumber;
    }
    private ItemStack getHandyToolPickaxe(){
        /*
        Smurfs Handy Tool (Netherite Pickaxe)
        Mines stone, coal ore, iron ore, lapis lazuli, gold ore, diamond ore, and ancient debris 30% faster.
        */
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.AQUA + "Smurf's Handy Tool");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "When mining stone, coal ore, iron ore, lapis lazuli,",
                "gold ore, diamond ore, and ancient debris - you get Haste 2"));
        meta.setLocalizedName("smurf_handy_tool");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack giveHandyToolPickaxe(){
        return getHandyToolPickaxe();
    }

    private ItemStack getWardenWeaknessPickaxe(){
        /*
        Smurfs Handy Tool (Netherite Pickaxe)
        Mines stone, coal ore, iron ore, lapis lazuli, gold ore, diamond ore, and ancient debris 30% faster.
        */
        ItemStack item = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta meta = item.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(ChatColor.BLUE + "The Warden's Weakness");
        meta.setLore(Arrays.asList(ChatColor.GRAY + "Deals 200% more damage to the warden than any other",
                "pickaxe.",
                "",
                "When paired with fortune III, the pickaxe allows",
                "anywhere from 2x - 4x drops."));
        meta.setLocalizedName("warden_weakness");
        item.setItemMeta(meta);
        return item;
    }
    public ItemStack giveWardenWeaknessPickaxe(){
        return getWardenWeaknessPickaxe();
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if (p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("warden_weakness")
                    && e.getEntity().getType().equals(EntityType.WARDEN)){

                int experience = p.getExpToLevel();
                e.setDamage(experience);
            }
        }
    }
    @EventHandler
    public void breakBlock(BlockBreakEvent e) {
        if (e.getPlayer() != null){
            Player p = e.getPlayer();
            if (p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("warden_weakness")
                    && p.getInventory().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)){
                int level = p.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                if (level == 3){
                    Material block = e.getBlock().getType();
                    if (block.equals(Material.COAL_ORE) || block.equals(Material.DEEPSLATE_COAL_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.COAL, Math.multiplyExact(Math.addExact(rollNumber(0,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.DIAMOND_ORE) || block.equals(Material.DEEPSLATE_DIAMOND_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.DIAMOND, Math.multiplyExact(Math.addExact(rollNumber(0,1), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.REDSTONE_ORE) || block.equals(Material.DEEPSLATE_REDSTONE_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.REDSTONE, Math.multiplyExact(Math.addExact(rollNumber(0,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.EMERALD_ORE) || block.equals(Material.DEEPSLATE_EMERALD_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.EMERALD, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(2,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.LAPIS_ORE) || block.equals(Material.DEEPSLATE_LAPIS_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.LAPIS_LAZULI, Math.multiplyExact(Math.addExact(rollNumber(2,3), rollNumber(2,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.NETHER_QUARTZ_ORE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.QUARTZ, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.MELON)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.MELON_SLICE, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.CLAY)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.STONE, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    } else if (block.equals(Material.GLOWSTONE)) {
                        e.setDropItems(false);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(),
                                new ItemStack(Material.GLOWSTONE_DUST, Math.multiplyExact(Math.addExact(rollNumber(1,2), rollNumber(3,4)), rollNumber(2,4))));

                    }
                }
            }
        }
    }

    @EventHandler
    public void breakingBlockEvent(PlayerInteractEvent e){
        if (e.hasBlock() == true){
            Player p = e.getPlayer();
            if (e.getPlayer().getInventory().getItemInMainHand() == null) {return;}
            if (p.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("smurf_handy_tool")){
                Material block = e.getMaterial();
                if (block.equals(Material.STONE) || block.equals(Material.IRON_ORE) || block.equals(Material.COAL_ORE) || block.equals(Material.GOLD_ORE)
                        || block.equals(Material.LAPIS_ORE) || block.equals(Material.DIAMOND_ORE) || block.equals(Material.ANCIENT_DEBRIS)){
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 100, 100, false, false, false));
                }
            }
        }
    }
}