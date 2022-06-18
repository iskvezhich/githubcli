package com.mend.yevhen.githubcli.commands;

import picocli.CommandLine;

import static picocli.CommandLine.*;
import static picocli.CommandLine.Command;
import static picocli.CommandLine.Model.*;
import static picocli.CommandLine.Option;

@Command
public class ReusableOptions {

    @Spec CommandSpec spec;

    @Option(names = {"-r", "--repo"},
            paramLabel = "string",
            description = "The repository to analyze")
    protected String repo;

    @Option(names = {"-o", "--output"},
            paramLabel = "string",
            description = "The output path of the txt file")
    protected String output;

    @Option(names = {"-h", "--help"},
            description = "Print information about the command",
            usageHelp = true)
    protected boolean help;

    public void validateArgs(){
        if (!help && repo == null) {
            throw new ParameterException(spec.commandLine(), "Missing required option");
        }
    }
}
