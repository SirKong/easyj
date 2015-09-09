package net.hs.easyj.config.definition;

import net.hs.easyj.config.spi.annotation.ConfigCategory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author gavin
 * @create 15/8/11
 */
public class DefaultObjectConfigDefinitionScanner
        implements ObjectConfigDefinitionScanner, ResourceLoaderAware {

    private String[] scanPatterns = new String[]{"classpath*:/net/hs/**/*Config.class"};

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();

    public void setScanPatterns(String[] scanPatterns) {
        for(String scanPattern : scanPatterns) {
            StringUtils.addStringToArray(this.scanPatterns, scanPattern);
        }
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourcePatternResolver = new PathMatchingResourcePatternResolver(resourceLoader);
        this.metadataReaderFactory = new SimpleMetadataReaderFactory(resourceLoader);
    }

    @Override
    public List<ObjectConfigDefinition> scan() {
        //
        List<ObjectConfigDefinition> definitions = new ArrayList<>();
        //
        try {
            for(String scanPattern : scanPatterns) {
                Resource[] resources = resourcePatternResolver.getResources(scanPattern);
                for (Resource resource : resources) {
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    ClassMetadata classMetadata = metadataReader.getClassMetadata();
                    if (annotationMetadata.isAnnotated(ConfigCategory.class.getName())) {
                        //
                        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(ConfigCategory.class.getName());
                        String categoryPath = String.valueOf(annotationAttributes.get("value"));
                        //
                        String[] categoryNames = StringUtils.tokenizeToStringArray(categoryPath, "/");
                        String className = classMetadata.getClassName();
                        //
                        ObjectConfigDefinition definition = new ObjectConfigDefinition();
                        definition.setClassName(className);
                        definition.setCategory(categoryNames[0]);
                        //
                        BeanWrapper definitionWrapper = new BeanWrapperImpl(definition);
                        for (int i = 1; i < categoryNames.length; i++) {
                            definitionWrapper.setPropertyValue("subCategory" + i, categoryNames[i]);
                        }
                        //
                        definitions.add(definition);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return definitions;
    }


}
