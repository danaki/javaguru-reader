#!/bin/bash
# @see http://forums.gradle.org/gradle/topics/how_can_i_provide_command_line_args_to_application_started_with_gradle_run

# Join arguments in a string. But not quite like you'd expect... see next line.
printf -v var "'%s', " "$@"

# Remove trailing ", "
var=${var%??}

gradle fetcher:run -PappArgs="[$var]"