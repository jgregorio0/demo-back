#!/bin/sh
#
# commit-msg.sh
# 
# This hook validates that the commit message header follows the Conventional Commits format (https://www.conventionalcommits.org/en/v1.0.0/).
# It runs after the message is entered, but before the commit is created.

GIT_ROOT=$(git rev-parse --show-toplevel)
source "$GIT_ROOT/scripts/git-hooks/utils.sh"

print_status "✍️" "Validating commit message..."

COMMIT_MSG_FILE=$1
FIRST_LINE=$(head -n 1 "$COMMIT_MSG_FILE")

# Simplified regex for Conventional Commits, allowing standard commits, merge commits, and the initial commit.
# It enforces a non-empty subject.
CONVENTIONAL_COMMIT_REGEX="^((build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test)(\([a-z-]+\))?!?: .+|Merge .*|Initial commit)$"

if ! echo "$FIRST_LINE" | grep -qE "$CONVENTIONAL_COMMIT_REGEX"; then
  print_status "❌" "Error: Commit message header does not follow the Conventional Commits format." >&2
  echo "See: https://www.conventionalcommits.org/" >&2
  echo "Allowed types: build, chore, ci, docs, feat, fix, perf, refactor, revert, style, test" >&2
  echo "Example: 'feat(user): add new endpoint for user creation'" >&2
  exit 1
fi

print_status "✅" "Commit message follows the Conventional Commits format."
exit 0
