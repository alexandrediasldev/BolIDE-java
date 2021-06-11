package fr.epita.assistants.myide.domain.entity;

import fr.epita.assistants.myide.domain.entity.aspect.Any;
import fr.epita.assistants.myide.domain.entity.aspect.Git;
import fr.epita.assistants.myide.domain.entity.aspect.Maven;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BasicProject implements Project{
    private Node rootNode;
    private Set<Aspect> aspects;
    private List<@NotNull Feature> features;

    public BasicProject(Node rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public Node getRootNode() {
        return rootNode;
    }

    @Override
    public Set<Aspect> getAspects() {
        return aspects;
    }

    @Override
    public Optional<Feature> getFeature(Feature.Type featureType) {
       for (var feature : features)
       {
           if (feature.type()==featureType)
               return Optional.of(feature);
       }
       return Optional.empty();
    }

    @Override
    public List<@NotNull Feature> getFeatures() {
        return features;
    }

    public void addAspect(Aspect.Type aspectType)
    {
        if (Mandatory.Aspects.ANY.equals(aspectType)) {
            var any = new Any();
            aspects.add(any);
            features.addAll(any.getFeatureList());
        } else if (Mandatory.Aspects.GIT.equals(aspectType)) {
            var git = new Git();
            aspects.add(git);
            features.addAll(git.getFeatureList());
        } else if (Mandatory.Aspects.MAVEN.equals(aspectType)) {
            var maven = new Maven();
            aspects.add(maven);
            features.addAll(maven.getFeatureList());
        }
    }
}
