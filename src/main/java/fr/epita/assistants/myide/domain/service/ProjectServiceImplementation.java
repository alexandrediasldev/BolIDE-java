package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.BasicProject;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.node.Folder;

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
    @Override
    public Project load(Path root) {
        Folder node = new Folder(root);
        return new BasicProject(node);
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
