package net.hs.easyj.maven.plugin.mojo;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.classreading.SimpleMetadataReaderFactory;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

/**
 * @author gavin
 * @create 15/8/22
 */
@Mojo(name="parse-widget", threadSafe = true)
public class ParseWidgetMojo extends AbstractMojo {

    @Parameter(defaultValue = "${scanPattern}")
    private String scanPattern;
    @Parameter(defaultValue = "${session}", readonly = true, required = true )
    private MavenSession session;
    @Parameter(defaultValue = "${project.basedir}")
    private File baseDirectory;
    @Parameter(defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        //
        try {
            ClassLoader classLoader = new URLClassLoader(new URL[]{outputDirectory.toURI().toURL()});
            //
            MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory(classLoader);
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(classLoader);
            //
            Resource[] resources = resourcePatternResolver.getResources("classpath*:/net/hs/**/*.class");
            for(Resource resource : resources) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                ClassMetadata classMetadata = metadataReader.getClassMetadata();
                AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                //
                if(annotationMetadata.hasAnnotation("net.hs.easyj.web.widget.annotation.Widget")) {
                    //
                    Class widgetClass = classLoader.loadClass(classMetadata.getClassName());
                    Class viewMappingClass = classLoader.loadClass("net.hs.easyj.web.widget.annotation.ViewMapping");
                    //

                }
            }



        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

}
