package fr.epita.assistants.myide.domain.entity;

import java.util.List;

public class Any implements Aspect{
    private final Type type = Mandatory.Aspects.ANY;

    public Any(List<Feature> featureList) {
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
