package fr.epita.assistants.myide.domain.entity;

import java.util.List;

public class Git implements Aspect{
    private final Type type = Mandatory.Aspects.GIT;

    public Git(List<Feature> featureList) {
        this.featureList = featureList;
    }

    private final List<Feature> featureList;

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public List<Feature> getFeatureList() {
        return featureList;
    }
}
