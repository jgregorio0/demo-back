#!/bin/sh
#
# pre-commit.sh
# 
# This hook runs before a commit is created.
# 1. Checks that the code adheres to the style rules.
# 2. If the check fails, the commit is aborted.

GIT_ROOT=$(git rev-parse --show-toplevel)
source "$GIT_ROOT/scripts/git-hooks/utils.sh"

print_status "🔍" "Running Spotless to check code format in project files..."

./gradlew spotlessCheck --quiet > /dev/null 2>&1

if [ $? -ne 0 ]; then
  print_status "❌" "Spotless check failed. Code formatting issues detected."
  print_status "👉" "Run './gradlew spotlessCheck' to get more information or run ./gradlew spotlessApply' to fix issues automatically."
  exit 1
fi

print_status "✅" "Spotless check passed."
exit 0
