/*
 * This file is part of Terra.
 *
 * Terra is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Terra is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Terra.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.dfsek.terra.util;


/**
 * Paper 1.21.11's {@code Bukkit.createBlockData} parser rejects SNBT block-entity
 * payloads such as {@code minecraft:chest{LootTable:'chests/simple_dungeon'}}
 * ("Spurious trailing data"). Config packs still emit those strings; strip the
 * trailing NBT so only the block id and {@code [properties]} remain.
 */
public final class BlockStateStringUtil {
    private BlockStateStringUtil() {
    }

    public static String stripBlockEntityNbt(String data) {
        int nbtStart = -1;
        for(int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if(c == '[') {
                int end = data.indexOf(']', i);
                if(end < 0) {
                    return data;
                }
                i = end;
                continue;
            }
            if(c == '{') {
                nbtStart = i;
                break;
            }
        }
        if(nbtStart < 0) {
            return data;
        }
        return data.substring(0, nbtStart);
    }
}
