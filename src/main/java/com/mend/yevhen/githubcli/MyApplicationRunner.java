package com.mend.yevhen.githubcli;

import com.mend.yevhen.githubcli.commands.GitHubCliCommand;
import com.mend.yevhen.githubcli.resultHandlers.ExecutionExceptionMessageHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

import java.util.ArrayList;
import java.util.List;

import static picocli.CommandLine.Model.UsageMessageSpec.*;

@Component
public class MyApplicationRunner implements CommandLineRunner, ExitCodeGenerator {

	private final GitHubCliCommand command;

	private final IFactory factory;

	private int exitCode;

	public MyApplicationRunner(GitHubCliCommand command, IFactory factory) {
		this.command = command;
		this.factory = factory;
	}

	@Override
	public void run(String... args) throws Exception {
		CommandLine cmd = new CommandLine(command, factory);
		List<String> copy = new ArrayList<>(cmd.getHelpSectionKeys());
		List<String> flags = List.of(SECTION_KEY_OPTION_LIST_HEADING, SECTION_KEY_OPTION_LIST);
		copy.removeAll(flags);
		copy.addAll(copy.indexOf(SECTION_KEY_FOOTER), flags);
		cmd.setHelpSectionKeys(copy);
		cmd.setExecutionExceptionHandler(new ExecutionExceptionMessageHandler());
		exitCode = cmd.execute(args);
	}

	@Override
	public int getExitCode() {
		return exitCode;
	}
}