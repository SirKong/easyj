package net.hs.easyj.maven.plugin.webx.mojo;

import org.apache.commons.lang.StringUtils;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolutionRequest;
import org.apache.maven.artifact.resolver.ArtifactResolutionResult;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.repository.RepositorySystem;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 运行 UI 组件
 *
 * @author Gavin Hu
 * @create 2015/5/15
 */
@Mojo(name="run-components", threadSafe = true)
public class RunComponentsMojo extends AbstractWebxMojo {

    @Component
    protected RepositorySystem repositorySystem;

    @Parameter(defaultValue = "${localRepository}")
    protected ArtifactRepository localRepository;

    @Parameter(defaultValue = "${project.remoteArtifactRepositories}")
    protected List<ArtifactRepository> remoteArtifactRepositories;

    @Parameter(defaultValue = "8080")
    private int port;

    @Parameter(defaultValue = "/")
    private String contextPath;

    @Parameter(required = true)
    private String warGroupId;

    @Parameter(required = true)
    private String warArtifactId;

    @Parameter(required = true)
    private String warVersion;

    @Parameter(defaultValue = "${project.basedir}/src/main/resources/,${project.basedir}/target/classes/")
    private String extraClasspath;

    @Override
    public void doExecute() throws Exception {
        //
        try {
            Artifact warArtifact= repositorySystem.createArtifact(warGroupId, warArtifactId, warVersion, "war");
            ArtifactResolutionRequest request = new ArtifactResolutionRequest();
            request.setArtifact(warArtifact);
            request.setLocalRepository(localRepository);
            request.setRemoteRepositories(remoteArtifactRepositories);
            //
            ArtifactResolutionResult result = repositorySystem.resolve(request);
            if(result.hasExceptions()) {
                throw new MojoExecutionException("Artifact " + warArtifact.toString() + " can not found!");
            }
            //
            Set<String> jarPaths = new LinkedHashSet<>();
            //
            String[] parts = StringUtils.split(this.extraClasspath, ",");
            for(String part : parts) {
                if(StringUtils.isNotBlank(part)) {
                    jarPaths.add(part);
                }
            }
            //
            Set<Artifact> dependencyArtifacts = session.getCurrentProject().getDependencyArtifacts();
            for(Artifact dependencyArtifact : dependencyArtifacts) {
                dependencyArtifact = session.getLocalRepository().find(dependencyArtifact);
                jarPaths.add(dependencyArtifact.getFile().getAbsolutePath());
            }
            //
            String warPath = warArtifact.getFile().getAbsolutePath();
            String extraClasspath = StringUtils.join(jarPaths.iterator(), ",");
            //
            startJetty(warPath, extraClasspath);
            //
        } catch (Exception e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
    }

    private void startJetty(String war, String extraClasspath) throws Exception {
        //
        Server server = new Server(port);
        //
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath(contextPath);
        webapp.setExtraClasspath(extraClasspath);
        webapp.setWar(war);
        //
        server.setHandler(webapp);
        //
        server.start();
        server.join();
    }


}
