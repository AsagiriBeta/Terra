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

package util;

import org.junit.jupiter.api.Test;

import com.dfsek.terra.util.BlockStateStringUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BlockStateStringUtilTest {
    @Test
    public void stripsTrailingBlockEntityNbt() {
        assertEquals("minecraft:chest",
            BlockStateStringUtil.stripBlockEntityNbt("minecraft:chest{LootTable:'chests/simple_dungeon'}"));
    }

    @Test
    public void preservesBlockStateProperties() {
        assertEquals("minecraft:chest[facing=north,type=single]",
            BlockStateStringUtil.stripBlockEntityNbt(
                "minecraft:chest[facing=north,type=single]{LootTable:'chests/simple_dungeon'}"));
    }

    @Test
    public void leavesPlainBlockStatesUnchanged() {
        assertEquals("minecraft:stone", BlockStateStringUtil.stripBlockEntityNbt("minecraft:stone"));
        assertEquals("minecraft:oak_log[axis=y]", BlockStateStringUtil.stripBlockEntityNbt("minecraft:oak_log[axis=y]"));
    }

    @Test
    public void stripsEntityNbtPayloads() {
        assertEquals("minecraft:end_crystal",
            BlockStateStringUtil.stripBlockEntityNbt("minecraft:end_crystal{SHOWBOTTOM:0}"));
    }
}
