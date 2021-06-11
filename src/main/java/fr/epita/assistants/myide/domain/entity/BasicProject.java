package fr.epita.assistants.myide.domain.entity;

import fr.epita.assistants.myide.domain.entity.aspect.Any;
import fr.epita.assistants.myide.domain.entity.aspect.Git;
import fr.epita.assistants.myide.domain.entity.aspect.Maven;

import javax.validation.constraints.NotNull;
import java.util.*;

public class BasicProject implements Project{
    private Node rootNode;
    private Set<Aspect> aspects;
    private Set<Aspect.Type> aspectsType;
    private List<@NotNull Feature> features;

    public BasicProject() {
        aspects = new HashSet<>();
        aspectsType = new HashSet<>();
        features = new ArrayList<>();
    }

    @Override
    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
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
        if (aspectsType.contains(aspectType))
            return;

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
        aspectsType.add(aspectType);
    }

}
