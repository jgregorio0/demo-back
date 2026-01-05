#!/bin/sh
#
# utils.sh
# 
# Contains utility functions

# ANSI Color Codes
C_RESET='\033[0m'
C_RED='\033[0;31m'
C_GREEN='\033[0;32m'
C_YELLOW='\033[0;33m'

print_status() {
  local emoji="$1"
  local message="$2"
  local color="$C_RESET"

  # Assign color based on the emoji
  case "$emoji" in
    "✅") color="$C_GREEN" ;;
    "❌") color="$C_RED" ;;
    *) color="$C_YELLOW" ;;
  esac

  # The -e flag for echo enables interpretation of backslash escapes (for colors)
  # We use printf for consistent padding and then echo to apply colors.
  status_line=$(printf "%s  %-40s" "$emoji" "$message")
  echo -e "${color}${status_line}${C_RESET}"
}
