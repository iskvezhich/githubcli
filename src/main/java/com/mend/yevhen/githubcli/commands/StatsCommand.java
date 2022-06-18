package com.mend.yevhen.githubcli.commands;

import com.mend.yevhen.githubcli.github.GitHubService;
import com.mend.yevhen.githubcli.model.Stat;
import com.mend.yevhen.githubcli.resultHandlers.ResultPrinter;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.List;
import java.util.concurrent.Callable;

@Component
@Command(name = "stats",
        description = "Present the stats of the repo (stars, forks, language, contributors)",
        exitCodeOnExecutionException = 42)
public class StatsCommand extends ReusableOptions implements Callable<Integer> {

    private final GitHubService gitHubService;

    private final ResultPrinter resultPrinter;

    public StatsCommand(GitHubService gitHubService, ResultPrinter resultPrinter) {
        this.gitHubService = gitHubService;
        this.resultPrinter = resultPrinter;
    }


    @Override
    public Integer call() {
        validateArgs();
        List<Stat> stats = gitHubService.stats(repo);
        resultPrinter.printStats(stats, output);
        return 0;
    }


}