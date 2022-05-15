package org.gymbrain.orm.generator.utils;

import java.util.ArrayList;
import java.util.List;

public class GymUtils {

    public static String getClassName(String fullName) {
        String packageName = fullName.substring(fullName.lastIndexOf(".")+1);
        return packageName;
    }

    public static String getPackage(String fullName) {
        String packageName = fullName.substring(0, fullName.lastIndexOf("."));
        return packageName;
    }

    public static String buildRepetitiveChar(String c, int count) {
        List<String> text = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            text.add(c);
        }
        return String.join(",",text);
    }
}
