package fr.epita.assistants.myide.domain.service;

import fr.epita.assistants.myide.domain.entity.BasicProject;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Node;
import fr.epita.assistants.myide.domain.entity.Project;
import fr.epita.assistants.myide.domain.entity.node.Folder;

import java.nio.file.Path;

public class ProjectServiceImplementation implements ProjectService{
    private NodeService nodeService;

    @Override
    public Project load(Path root) {
        Folder node = new Folder(root);
        return new BasicProject(node);
    }

    @Override
    public Feature.ExecutionReport execute(Project project, Feature.Type featureType, Object... params) {

        return null;
    }

    @Override
    public NodeService getNodeService() {
        return nodeService;
    }
}
