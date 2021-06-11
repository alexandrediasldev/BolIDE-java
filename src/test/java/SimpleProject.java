import fr.epita.assistants.MyIde;
import fr.epita.assistants.myide.domain.entity.Mandatory;

import java.nio.file.Path;

public class SimpleProject {
    public static void main(String[] args){
        var projectService = MyIde.init(new MyIde.Configuration(null, null));
        var project = projectService.load(Path.of("./testfiles"));
        projectService.execute(project, Mandatory.Features.Any.DIST);

    }
}
