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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.random.RandomGenerator;


/**
 * Paper (and other isolated plugin class loaders) set the thread context class loader
 * to a loader that cannot see the {@code jdk.random} module on Java 17-21.
 * {@link RandomGenerator#getDefault()} then fails to resolve {@code L32X64MixRandom},
 * which seismic uses while initializing trigonometry lookup tables. Warming the JDK
 * factory map and those classes up front on the platform loader avoids pack-load races.
 */
public final class NativeLibraryWarmup {
    private static final Logger logger = LoggerFactory.getLogger(NativeLibraryWarmup.class);

    private NativeLibraryWarmup() {
    }

    public static void warmup() {
        ClassLoader previous = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader loader = ClassLoader.getSystemClassLoader();
            if(loader == null) {
                loader = ClassLoader.getPlatformClassLoader();
            }
            Thread.currentThread().setContextClassLoader(loader);
            RandomGenerator.getDefault();
            Class.forName("com.dfsek.seismic.math.trigonometry.TrigonometryUtils");
            Class.forName("com.dfsek.paralithic.functions.natives.NativeMath");
        } catch(ClassNotFoundException e) {
            logger.debug("Native math classes were not present during warmup", e);
        } catch(Throwable t) {
            logger.warn("Failed to warm up JDK random providers / native math. Config packs may fail to load.", t);
        } finally {
            Thread.currentThread().setContextClassLoader(previous);
        }
    }
}
