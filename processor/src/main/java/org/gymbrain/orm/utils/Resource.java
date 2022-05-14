package org.gymbrain.orm.utils;

import java.util.ResourceBundle;

public class Resource {
    public static String getValue(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("gymorm");
        return resourceBundle.getString(key);
    }
}
