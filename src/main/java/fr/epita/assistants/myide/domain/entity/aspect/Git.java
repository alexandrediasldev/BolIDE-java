package fr.epita.assistants.myide.domain.entity.aspect;

import fr.epita.assistants.myide.domain.entity.Aspect;
import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.feature.git.Add;
import fr.epita.assistants.myide.domain.entity.feature.git.Commit;
import fr.epita.assistants.myide.domain.entity.feature.git.Pull;
import fr.epita.assistants.myide.domain.entity.feature.git.Push;

import java.util.ArrayList;
import java.util.List;

public class Git implements Aspect {
    private final Type type = Mandatory.Aspects.GIT;
    private final List<Feature> featureList;

    public Git() {
        featureList  = new ArrayList<>();
        featureList.add(new Add());
        featureList.add(new Commit());
        featureList.add(new Pull());
        featureList.add(new Push());
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
