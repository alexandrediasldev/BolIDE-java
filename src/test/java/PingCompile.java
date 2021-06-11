import fr.epita.assistants.MyIde;
import fr.epita.assistants.myide.domain.entity.Mandatory;

import java.nio.file.Path;

public class PingCompile {

    public static void main(String[] args) {
        var projectService = MyIde.init(new MyIde.Configuration(null, null));
        var project = projectService.load(Path.of("./testfiles"));

        /*
        if (project.getAspects().stream().noneMatch(e ->e.getType().toString().equals("MAVEN")));
        {
            System.err.println("pom.xml not found");
        }
         */

        projectService.execute(project, Mandatory.Features.Maven.COMPILE);
    }
}
