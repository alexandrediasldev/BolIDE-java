package fr.epita.assistants.myide.domain.entity.aspect;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.feature.git.Commit;
import fr.epita.assistants.myide.domain.entity.feature.maven.*;
import fr.epita.assistants.myide.domain.entity.feature.maven.Package;

import java.util.ArrayList;
import java.util.List;

public class Maven implements Aspect {
    private final Type type = Mandatory.Aspects.MAVEN;
    private final List<Feature> featureList;

    public Maven() {
        featureList = new ArrayList<>();
        featureList.add(new Clean());
        featureList.add(new Compile());
        featureList.add(new Exec());
        featureList.add(new Install());
        featureList.add(new Package());
        featureList.add(new Test());
        featureList.add(new Tree());
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
