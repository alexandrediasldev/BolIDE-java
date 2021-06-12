package fr.epita.assistants.myide.domain.entity.feature.git;

import fr.epita.assistants.myide.domain.entity.Feature;
import fr.epita.assistants.myide.domain.entity.Mandatory;
import fr.epita.assistants.myide.domain.entity.Project;
import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class Add implements Feature {
    public Add()
    {

    }

    public class AddReport implements ExecutionReport {

        final private boolean success;

        public AddReport(boolean success) {
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
            AddCommand add = git.add();

            for (var file : params) {
                if (file instanceof String)
                    add = add.addFilepattern((String) file);
                else
                    return new AddReport(false);
            }

            add.call();
        } catch (IOException | GitAPIException e) {
            return new AddReport(false);
        }

        return new AddReport(true);
    }

    @Override
    public Type type() {
        return Mandatory.Features.Git.ADD;
    }
}
