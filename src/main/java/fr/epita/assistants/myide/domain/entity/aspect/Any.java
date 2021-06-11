package fr.epita.assistants.myide.domain.entity.aspect;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.feature.any.Cleanup;
import fr.epita.assistants.myide.domain.entity.feature.any.Dist;
import fr.epita.assistants.myide.domain.entity.feature.any.Search;

import java.util.ArrayList;
import java.util.List;

public class Any implements Aspect {
    private final Type type = Mandatory.Aspects.ANY;
    private final List<Feature> featureList;

    public Any() {
        this.featureList = new ArrayList<>();
        featureList.add(new Cleanup());
        featureList.add(new Dist());
        featureList.add(new Search());
    }


    @Override
    public Type getType() {
        return type;
    }

    @Override
    public List<Feature> getFeatureList() {
        return featureList;
    }
}