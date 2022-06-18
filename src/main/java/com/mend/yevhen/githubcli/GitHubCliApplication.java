package com.mend.yevhen.githubcli;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GitHubCliApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(new SpringApplicationBuilder(GitHubCliApplication.class)
				.logStartupInfo(false)
				.bannerMode(Banner.Mode.OFF)
				.web(WebApplicationType.NONE)
				.run(args)));
	}

}
