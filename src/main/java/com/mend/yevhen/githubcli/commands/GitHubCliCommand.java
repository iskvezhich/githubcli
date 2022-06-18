package com.mend.yevhen.githubcli.commands;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(name = "githubcli",
        subcommands = {
            DownloadsCommand.class,
            StatsCommand.class
        },
        headerHeading = """                
                This tool is used to get some stats from Github for specific repo.
                This tool present the result as a table and write the output for a given
                file or just print it
                """,
        synopsisHeading = "%nUsage:%n   ",
        customSynopsis = {"githubcli [COMMAND] [@|fg(yellow) -h|@] [@|fg(yellow) -o|@=string] [@|fg(yellow) -r|@=string]"},
        optionListHeading = "%nFlags:%n",
        commandListHeading = "%nAvailable commands:%n",
        footer = "%nUse @|fg(green) \"githubCLI [command] --help\"|@ for more information about a command.",
        usageHelpWidth = 120
        )
public class GitHubCliCommand extends ReusableOptions {

}
