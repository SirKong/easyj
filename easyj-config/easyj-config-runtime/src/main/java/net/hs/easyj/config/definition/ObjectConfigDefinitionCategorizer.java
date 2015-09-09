package net.hs.easyj.config.definition;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gavin
 * @create 15/8/11
 */
public class ObjectConfigDefinitionCategorizer {

    private Set<String> categories = new LinkedHashSet<>();

    private Map<String, Set<String>> subCategoriesMap1 = new HashMap<>();

    private Map<String, Set<String>> subCategoriesMap2 = new HashMap<>();

    private Map<String, Set<String>> subCategoriesMap3 = new HashMap<>();

    private Map<String, ObjectConfigDefinition> objectConfigDefinitionMap = new HashMap<>();

    public ObjectConfigDefinitionCategorizer(List<ObjectConfigDefinition> objectConfigDefinitions) {
        init(objectConfigDefinitions);
    }

    public void init(List<ObjectConfigDefinition> objectConfigDefinitions) {
        //
        for(ObjectConfigDefinition objectConfigDefinition : objectConfigDefinitions) {
            //
            categories.add(objectConfigDefinition.getCategory());
            //
            if(objectConfigDefinition.getSubCategory1()!=null) {
                Set<String> subCategories1 = getSubCategories(subCategoriesMap1, objectConfigDefinition.getCategory());
                subCategories1.add(objectConfigDefinition.getSubCategory1());
            }
            if(objectConfigDefinition.getSubCategory2()!=null) {
                Set<String> subCategories2 = getSubCategories(subCategoriesMap2, objectConfigDefinition.getSubCategory1());
                subCategories2.add(objectConfigDefinition.getSubCategory2());
            }
            if(objectConfigDefinition.getSubCategory3()!=null) {
                Set<String> subCategories3 = getSubCategories(subCategoriesMap3, objectConfigDefinition.getSubCategory2());
                subCategories3.add(objectConfigDefinition.getSubCategory3());
            }
            //
            objectConfigDefinitionMap.put(objectConfigDefinition.getCategoryPath(), objectConfigDefinition);
        }
    }

    private Set<String> getSubCategories(Map<String, Set<String>> subCategoriesMap, String category) {
        Set<String> subCategories = subCategoriesMap.get(category);
        if(subCategories==null) {
            subCategories = new LinkedHashSet<>();
            subCategoriesMap.put(category, subCategories);
        }
        return subCategories;
    }

    public Set<String> getCategories() {
        return categories;
    }

    public Set<String> getSubCategories1(String category) {
        return getSubCategories(subCategoriesMap1, category);
    }

    public Set<String> getSubCategories2(String subCategory1) {
        return getSubCategories(subCategoriesMap2, subCategory1);
    }

    public Set<String> getSubCategories3(String subCategory2) {
        return getSubCategories(subCategoriesMap3, subCategory2);
    }

    public ObjectConfigDefinition getObjectConfigDefinition(String category) {
        return this.getObjectConfigDefinition(category, null);
    }

    public ObjectConfigDefinition getObjectConfigDefinition(String category, String subCategory1) {
        return this.getObjectConfigDefinition(category, subCategory1, null);
    }

    public ObjectConfigDefinition getObjectConfigDefinition(String category, String subCategory1, String subCategory2) {
        return this.getObjectConfigDefinition(category, subCategory1, subCategory2, null);
    }

    public ObjectConfigDefinition getObjectConfigDefinition(String category, String subCategory1, String subCategory2, String subCategory3) {
        StringBuilder categoryPathBuilder = new StringBuilder("/" + category);
        if(subCategory1!=null) {
            categoryPathBuilder.append("/").append(subCategory1);
        }
        if(subCategory2!=null) {
            categoryPathBuilder.append("/").append(subCategory2);
        }
        if(subCategory3!=null) {
            categoryPathBuilder.append("/").append(subCategory3);
        }
        return objectConfigDefinitionMap.get(categoryPathBuilder.toString());
    }

}
