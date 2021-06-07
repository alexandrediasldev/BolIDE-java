package fr.epita.assistants.myide.domain.entity;

import java.util.List;

public class Maven implements Aspect{
    private final Type type = Mandatory.Aspects.MAVEN;

    public Maven(List<Feature> featureList) {
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
