package net.hs.easyj.web.extender.util;

/**
 * Created by gavin on 15/4/2.
 */
public class ClassUtils {

    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            classLoader.loadClass(className);
            return true;
        } catch (Exception cnfe) {
            return false;
        }
    }

}
