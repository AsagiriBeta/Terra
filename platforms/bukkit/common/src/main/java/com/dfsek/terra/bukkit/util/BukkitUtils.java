package com.dfsek.terra.bukkit.util;

import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

import com.dfsek.terra.api.entity.EntityType;
import com.dfsek.terra.bukkit.world.entity.BukkitEntityType;
import com.dfsek.terra.util.BlockStateStringUtil;


public class BukkitUtils {
    private static final Logger logger = LoggerFactory.getLogger(BukkitUtils.class);

    public static boolean isLiquid(BlockData blockState) {
        Material material = blockState.getMaterial();
        return material == Material.WATER || material == Material.LAVA;
    }

    public static EntityType getEntityType(String id) {
        // Paper 1.21.11 EntityType.valueOf rejects SNBT payloads such as
        // minecraft:end_crystal{SHOWBOTTOM:0}.
        String normalized = BlockStateStringUtil.stripBlockEntityNbt(id);
        if(!normalized.startsWith("minecraft:")) throw new IllegalArgumentException("Invalid entity identifier " + id);
        String entityID = normalized.toUpperCase(Locale.ROOT).substring(10);

        return new BukkitEntityType(switch(entityID) {
            case "END_CRYSTAL" -> org.bukkit.entity.EntityType.END_CRYSTAL;
            case "ENDER_CRYSTAL" -> throw new IllegalArgumentException(
                "Invalid entity identifier " + id); // make sure this issue can't happen the other way around.
            default -> org.bukkit.entity.EntityType.valueOf(entityID);
        });
    }
}
