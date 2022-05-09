package fr.epita.assistants.myide.domain.entity.feature.git;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Commit implements Feature {

    public Commit()
    {

    }

    public class CommitReport implements Feature.ExecutionReport {

        final boolean success;

        public CommitReport(boolean success) {
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

            Git git = Git.open(new File(path));
            CommitCommand commit = git.commit();
            StringBuilder message = new StringBuilder();
            Arrays.stream(params).forEach(msg -> message.append(msg.toString()));
            commit.setMessage(message.toString());
            commit.call();
        } catch (IOException | GitAPIException e) {
            return new CommitReport(false);
        }

        return new CommitReport(true);
    }

    @Override
    public Type type() {
        return Mandatory.Features.Git.COMMIT;
    }
}
