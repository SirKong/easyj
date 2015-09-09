package net.hs.easyj.config.wrapper;

import net.hs.easyj.config.spi.ObjectConfig;
import net.hs.easyj.config.spi.annotation.ConfigCategory;
import net.hs.easyj.config.spi.annotation.ConfigField;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gavin
 * @create 15/8/10
 */
public class ObjectConfigWrapper {

    private BeanWrapper beanWrapper;

    private String category;

    private String categoryLabel;

    private Map<String, String> properties = new LinkedHashMap<>();

    private Map<String, Boolean> propertyRequiredMap = new LinkedHashMap<>();

    private Map<String, String> propertyDescriptionMap = new LinkedHashMap<>();

    public ObjectConfigWrapper(ObjectConfig objectConfig) {
        init(objectConfig);
    }

    private void init(ObjectConfig objectConfig) {
        this.beanWrapper = new BeanWrapperImpl(objectConfig);
        //
        ConfigCategory configCategory = objectConfig.getClass().getAnnotation(ConfigCategory.class);
        this.category = configCategory.value();
        this.categoryLabel = configCategory.label();
        //
        for(Field field : getAllFields(objectConfig.getClass())){
            ConfigField configField = field.getAnnotation(ConfigField.class);
            if(configField!=null) {
                try {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(objectConfig);
                    //
                    this.properties.put(name, value==null ? "" : String.valueOf(value));
                    this.propertyRequiredMap.put(name, configField.required());
                    this.propertyDescriptionMap.put(name, configField.description());
                    //
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //
        this.properties.putAll(objectConfig.getProperties());
    }

    private Field[] getAllFields(Class<?> objectConfigClass) {
        List<Field> fields = new ArrayList<>();
        collectFields(fields, objectConfigClass);
        return fields.toArray(new Field[fields.size()]);
    }

    private void collectFields(List<Field> collectedFields, Class<?> clazz) {
        Class<?> supperClazz = clazz.getSuperclass();
        if(ObjectConfig.class.isAssignableFrom(supperClazz)) {
            collectFields(collectedFields, supperClazz);
        }
        Field[] fields = clazz.getDeclaredFields();
        collectedFields.addAll(Arrays.asList(fields));
    }

    public String getCategory() {
        return category;
    }

    public String getCategoryLabel() {
        return categoryLabel;
    }

    public boolean hasPropertyName(String name) {
        return properties.containsKey(name);
    }

    public Set<String> getPropertyNames() {
        return this.properties.keySet();
    }

    public String getPropertyValue(String name) {
        Object value = this.properties.get(name);
        if(value==null) {
            value = beanWrapper.getPropertyValue(name);
        }
        return value==null ? null : String.valueOf(value);
    }

    public boolean getPropertyRequired(String property) {
        return propertyRequiredMap.get(property);
    }

    public String getPropertyDescription(String property) {
        return propertyDescriptionMap.get(property);
    }

    public void setPropertyValue(String name, String value) {
        if(beanWrapper.isWritableProperty(name)) {
            beanWrapper.setPropertyValue(name, value);
        } else {
            ((ObjectConfig)beanWrapper.getWrappedInstance()).setProperty(name, value);
        }
    }

    public ObjectConfig getObjectConfig() {
        return (ObjectConfig) beanWrapper.getWrappedInstance();
    }
}
