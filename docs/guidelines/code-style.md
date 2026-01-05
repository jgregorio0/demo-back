# Code Style Guide

This document outlines the coding standards, tools, and automated workflows we use to maintain code
quality and consistency. Following these guidelines is essential for every developer on the project.

---

## ☕ Code Conventions

We use the **Google Style Guide for Java**. You can check
the [official documentation](https://google.github.io/styleguide/javaguide.html) to see all the
rules in detail.

---

## 🤖 Spotless

`Spotless` is our chosen code formatting tool. It is configured in `build.gradle` to automatically
apply the **Google Java Format** rules to all our Java files.

This ensures that all code follows the same style, regardless of the developer's IDE or local
settings. The format can be ensured automatically through Git Hooks.

You can find more information about the tool and its capabilities in its
official [GitHub repository](https://github.com/diffplug/spotless).

---

## ⚙️ IDE Configuration

### IntelliJ IDEA

Configuring your IDE to match provides the best developer experience, with real-time feedback and auto-formatting on
save.

**Google Java Format** plugin.

1. **Install the Plugin**: Go to `File` -> `Settings` -> `Plugins`. Search for
   `google-java-format` in the Marketplace and install it.
2. **Enable the Plugin**: After restarting, go to `File` -> `Settings` -> `Tools` ->
   `Google Java Format` and check `[x] Enable google-java-format`.
3. **Enable "Format on Save" (Recommended)**: Go to `File` -> `Settings` -> `Tools` ->
   `Actions on Save` and check:
    * `[x] Reformat code`
    * `[x] Optimize imports`
    * `[x] Rearrange code`
