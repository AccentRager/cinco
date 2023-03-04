package com.stigglespatch.main.Custom.Items;

import com.stigglespatch.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import org.bukkit.entity.Entity;

import java.util.Arrays;

public class Bows {

    private static final Main plugin = Main.getPlugin(Main.class);
    private static final NamespacedKey boomKey = new NamespacedKey(plugin, "boom_arrow");

    private ItemStack getBoomBow(){
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(("Boom Bows"));
        meta.setLore(Arrays.asList("This bow is boomin!"));
        meta.setLocalizedName("boom_bow");
        bow.setItemMeta(meta);
        return bow;
    }

    private boolean isBoomBow(ItemStack item){
        return(item.hasItemMeta()
        && item.getItemMeta().hasLocalizedName()
        && item.getItemMeta().getLocalizedName().equals("boom_bow"));
    }
    @EventHandler
    public void onShoot(EntityShootBowEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if (isBoomBow(e.getBow())){
            e.getProjectile().getPersistentDataContainer().set(boomKey, PersistentDataType.STRING, "boom_arrow");
        }
    }

    @EventHandler
    public void onDamage(ProjectileHitEvent e){
        if (e.getHitEntity() == null) return;
        PersistentDataContainer container = e.getEntity().getPersistentDataContainer();
        if (!container.has(boomKey, PersistentDataType.STRING)) return;
        if (container.get(boomKey, PersistentDataType.STRING).equals("boom_arrow")) {
            Entity hitEntity = e.getHitEntity();
            TNTPrimed tnt = (TNTPrimed) hitEntity.getWorld().spawnEntity(hitEntity.getLocation(), EntityType.PRIMED_TNT);
            tnt.setFuseTicks(0);
        }
    }

    public ItemStack getBoomBowPlayer(Player p){
        p.getInventory().addItem(getBoomBow());
        return getBoomBow();
    }

}
