# GitHubCLI

This project is implementation a GitHub CLI that gets information from the GitHub repository, 
creates a report and saves it locally.

**The project is written using Java 17 and SpringBoot 2.7.0.**

**How to run the project:**

Java 17 RTE should be installed.
The root project folder contains the file "githubcli.jar". 
For running the application download the file and execute the command line command:

`<path_to_java17> -jar <path_to_jar> --help
`

For more comfortable using the part '<path_to_java17> -jar <path_to_jar>" can 
be replaced with the alias "githubcli" (Ubuntu shell example):


_add alias:_

`alias githubcli='java -jar githubcli.jar'
`

_invoke our command with some parameters:_

`githubcli --help
`

_remove our 'githubcli' pseudonym from the current shell environment:_

`unalias githubcli`