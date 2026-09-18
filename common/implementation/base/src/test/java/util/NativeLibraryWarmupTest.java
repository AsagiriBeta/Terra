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

import java.util.random.RandomGenerator;

import com.dfsek.terra.util.NativeLibraryWarmup;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class NativeLibraryWarmupTest {
    @Test
    public void warmupResolvesDefaultRandomGenerator() {
        assertDoesNotThrow(NativeLibraryWarmup::warmup);
        assertDoesNotThrow(RandomGenerator::getDefault);
        assertDoesNotThrow(() -> Class.forName("com.dfsek.seismic.math.trigonometry.TrigonometryUtils"));
        assertDoesNotThrow(() -> Class.forName("com.dfsek.paralithic.functions.natives.NativeMath"));
    }
}
