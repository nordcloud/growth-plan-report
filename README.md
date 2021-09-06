# growth-plan-report

Simple Clojure snippet to create a report about growth plans.

## Install

- [OpenJDK](https://openjdk.java.net/projects/jdk/11/) 
- [Clojure](https://clojure.org/guides/getting_started#_clojure_installer_and_cli_tools)
- [Leiningen](https://leiningen.org) 


## Usage

- Provide api token to `resources/bob-api-key` file
- Provide list of employees to `resources/input.csv`, (IdString,FirstName,LastName,Manager.FullName)
- Run `lein run`
- A report should appear in `resources/output.csv`