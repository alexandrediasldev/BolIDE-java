package fr.epita.assistants.myide.domain.entity.feature.git;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.errors.*;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class Pull implements Feature {

    public class PullReport implements ExecutionReport{

        final boolean success;

        public PullReport(boolean success) {
            this.success = success;
        }

        @Override
        public boolean isSuccess() {
            return success;
        }
    }
    @Override
    public ExecutionReport execute(Project project, Object... params) {
        String path = project.getRootNode().getPath().toAbsolutePath().toString();
        FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();

        try {
            Repository repository = repositoryBuilder.setGitDir(new File(path))
                    .readEnvironment()
                    .findGitDir()
                    .build();

            Git git = new Git(repository);
            PullCommand pull = git.pull();
            pull.setFastForward(null).call();

        } catch (IOException | GitAPIException e) {
            return new PullReport(false);
        }
        return new PullReport(true);
    }

    @Override
    public Type type() {
        return Mandatory.Features.Git.PULL;
    }
}
