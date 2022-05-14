package org.gymbrain.orm.processor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomClassLoader extends ClassLoader {

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        return findClass(name,false);
    }

    public Class findClass(String name, boolean initArrayList) throws ClassNotFoundException {
        try {
            if(name.startsWith("java.util.ArrayList") || initArrayList){
                return ArrayList.class;
            }else if (name.startsWith("java.util.List")){
                return List.class;
            }
            return Class.forName(name);
        } catch (NoClassDefFoundError e) {
            byte[] b = loadClassFromFile(name);
            return defineClass(name, b, 0, b.length);
        }
    }

    private byte[] loadClassFromFile(String fileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                fileName.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue = 0;
        try {
            while ((nextValue = inputStream.read()) != -1) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}