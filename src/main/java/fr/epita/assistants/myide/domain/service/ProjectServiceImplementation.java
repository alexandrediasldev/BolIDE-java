package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.*;
import fr.epita.assistants.myide.domain.entity.node.Folder;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ProjectServiceImplementation implements ProjectService{
    private final NodeService nodeService = new NodeServiceImplementation();


    // default report when a non implemented feature is searched
    public class ProjectReport implements Feature.ExecutionReport {

        @Override
        public boolean isSuccess() {
            return false;
        }
    }

    // add each children of the root to have complete project architecture
    // depth is used to detect if pom.xml is somewhere else than root directory
    private Node buildArchitecture(BasicProject project, Path root, int depth){
        java.io.File file = new File(String.valueOf(root));
        var children = file.listFiles();
        if (children == null)
        {
            if (depth==1 && String.valueOf(root).matches(".*" + File.separator +  "pom.xml"))
                project.addAspect(Mandatory.Aspects.MAVEN);
           return new fr.epita.assistants.myide.domain.entity.node.File(root);
        }

        var res = new Folder(root);
        for (var child : children)
        {
            res.addChild(buildArchitecture(project, child.toPath(), depth+1));
        }
        return res;
    }


    private boolean isGitProject(File root){
        if(root == null)
            return false;

        var children = root.listFiles();

        if (children != null) {
            for (var child : children) {
                System.out.println(child.toPath());

                if (child.getPath().matches(".*" + File.separator + ".git")) {
                    return true;
                }
            }
        }
        return isGitProject(root.getParentFile());
    }

    @Override
    public Project load(Path root) {
        System.out.println(root.toString());
        var res =  new BasicProject();
        res.addAspect(Mandatory.Aspects.ANY);
        var node = buildArchitecture(res, root, 0);

        if (isGitProject(new File(root.toString())))
            res.addAspect(Mandatory.Aspects.GIT);

        res.setRootNode(node);
        System.out.println(res.getAspects());
        return res;
    }

    @Override
    public Feature.ExecutionReport execute(Project project, Feature.Type featureType, Object... params) {

        var feature = project.getFeature(featureType);
        System.out.println(feature);
        if (feature.isPresent()) {
            return feature.get().execute(project, params);
        }
        return new ProjectReport();
    }

    @Override
    public NodeService getNodeService() {
        return nodeService;
    }
}
