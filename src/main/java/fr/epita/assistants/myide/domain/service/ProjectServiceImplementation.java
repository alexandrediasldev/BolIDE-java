package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.*;
import fr.epita.assistants.myide.domain.entity.node.Folder;

import java.io.File;
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
    private Node buildArchitecture(BasicProject project, Path root){
        java.io.File file = new File(String.valueOf(root));
        var children = file.listFiles();
        if (children == null)
        {
            if (String.valueOf(root).matches(".*[.]git"))
                project.addAspect(Mandatory.Aspects.GIT);
            if (String.valueOf(root).equals("pom.xml"))
                project.addAspect(Mandatory.Aspects.MAVEN);
           return new fr.epita.assistants.myide.domain.entity.node.File(root);
        }

        var res = new Folder(root);
        for (var child : children)
        {
            res.addChild(buildArchitecture(project, child.toPath()));
        }
        return res;
    }

    @Override
    public Project load(Path root) {
        var res =  new BasicProject();
        res.addAspect(Mandatory.Aspects.ANY);
        var node = buildArchitecture(res, root);
        res.setRootNode(node);
        return res;
    }

    @Override
    public Feature.ExecutionReport execute(Project project, Feature.Type featureType, Object... params) {
 
        var feature = project.getFeature(featureType);
        if (feature.isPresent())
            return feature.get().execute(project, params);
        return new ProjectReport();
    }

    @Override
    public NodeService getNodeService() {
        return nodeService;
    }
}
