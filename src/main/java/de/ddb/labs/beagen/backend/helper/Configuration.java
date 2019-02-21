/* 
 * Copyright 2019 Michael Büchner, Deutsche Digitale Bibliothek
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.ddb.labs.beagen.backend.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class Configuration {

    private final static String PROPERTY_FILE = "/beagen.cfg";
    private final static Configuration INSTANCE = new Configuration();
    private final static Properties PROPERTIES = new Properties();

    private Configuration() {
    }

    public static Configuration get() throws InvalidPropertiesFormatException, IOException {

        if (PROPERTIES.isEmpty()) {
            try (final BufferedReader cfg = new BufferedReader(new InputStreamReader(Configuration.class.getResourceAsStream(PROPERTY_FILE), StandardCharsets.UTF_8));) {
                PROPERTIES.load(cfg);
            }
        }

        return Configuration.INSTANCE;
    }

    public String[] getValueAsArray(String key, String split) {
        if (PROPERTIES == null) {
            return null;
        }
        final String[] r = PROPERTIES.getProperty(key).split(split);
        return r == null ? new String[0] : r;
    }

    public String getValue(String key) {
        if (PROPERTIES == null) {
            return null;
        }
        return PROPERTIES.getProperty(key);
    }

    public String getProperty(String key) {
        if (PROPERTIES == null) {
            return null;
        }
        return PROPERTIES.getProperty(key);
    }
}
