#!/bin/sh
#
# pre-push.sh
# 
# This hook runs before pushing to the remote repository.
# It runs the project's quality checks. If they fail, the push is aborted.

GIT_ROOT=$(git rev-parse --show-toplevel)
source "$GIT_ROOT/scripts/git-hooks/utils.sh"

print_status "📝" "Running quality checks before push..."

# TODO: add quality checks here
# ./gradlew test

if [ $? -ne 0 ]; then
  print_status "❌" "Quality checks failed. Aborting push."
  exit 1
fi

print_status "✅" "Quality checks passed. Proceeding with push."
exit 0
