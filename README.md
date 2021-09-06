# growth-plan-report

Simple Clojure snippet to create a report about growth plans.

- Provide api token to `resources/bob-api-key` file
- Provide list of employees to `resources/input.csv`, (IdString,FirstName,LastName,Manager.FullName)
- Run `lein run`
- A report should appear in `resources/output.csv`