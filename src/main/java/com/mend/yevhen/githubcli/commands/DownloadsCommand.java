package com.mend.yevhen.githubcli.commands;

import com.mend.yevhen.githubcli.github.GitHubService;
import com.mend.yevhen.githubcli.model.Release;
import com.mend.yevhen.githubcli.resultHandlers.ResultPrinter;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.List;
import java.util.concurrent.Callable;

@Component
@Command(name = "downloads",
        description = "Present the entire downloads for each asset",
        exitCodeOnExecutionException = 42)
public class DownloadsCommand extends ReusableOptions implements Callable<Integer> {

    private final GitHubService gitHubService;
    private final ResultPrinter printer;


    public DownloadsCommand(GitHubService gitHubService, ResultPrinter printer) {
        this.gitHubService = gitHubService;
        this.printer = printer;
    }

    @Override
    public Integer call() {
        validateArgs();
        List<Release> downloads = gitHubService.downloads(repo);
        return printer.printReleases(downloads, output);
    }
}