package fr.epita.assistants.myide.domain.entity.feature.git;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class Push implements Feature{

    public class PushReport implements Feature.ExecutionReport {

        final boolean success;

        public PushReport(boolean success) {
            this.success = success;
        }

        @Override
        public boolean isSuccess() {
            return success;
        }
    }
    @Override
    public Feature.ExecutionReport execute(Project project, Object... params) {
        String path = project.getRootNode().getPath().toAbsolutePath().toString();
        FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
        try {
            Repository repository = repositoryBuilder.setGitDir(new File(path))
                    .readEnvironment()
                    .findGitDir()
                    .build();

            Git git = new Git(repository);
            if (params.length == 0){
                PushCommand push = git.push();
                push.call();
            }
            else{
                PushCommand push = git.push(); //handle with parameters
                push.call();
            }
        } catch (IOException | GitAPIException e) {
            return new PushReport(false);
        }
        return new PushReport(true);
    }

    @Override
    public Feature.Type type() {
        return Mandatory.Features.Git.PUSH;
    }
}
