import fr.epita.assistants.myide.domain.entity.feature.maven.Compile;
import fr.epita.assistants.myide.domain.entity.feature.maven.MavenExecuter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CompileTest {

    @Test
    void compile() {
        try {
            MavenExecuter.mvnCommand("mvn compile");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            assert(false);
        }
    }

}