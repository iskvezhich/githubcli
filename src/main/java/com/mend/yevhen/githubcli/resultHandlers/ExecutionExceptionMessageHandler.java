package com.mend.yevhen.githubcli.resultHandlers;

import picocli.CommandLine;

import static picocli.CommandLine.*;

public class ExecutionExceptionMessageHandler implements IExecutionExceptionHandler {
    public int handleExecutionException(Exception ex,
                                        CommandLine cmd,
                                        ParseResult parseResult) {

        // bold red error message
        cmd.getErr().println(cmd.getColorScheme().errorText(ex.getMessage() +
                (ex.getCause() == null ? "" : ". Cause: " + ex.getCause().getClass() + ": " + ex.getCause().getMessage())));

        return cmd.getExitCodeExceptionMapper() != null
                    ? cmd.getExitCodeExceptionMapper().getExitCode(ex)
                    : cmd.getCommandSpec().exitCodeOnExecutionException();
    }
}