# Contributing Guide

Thank you for your interest in contributing to our project! Your help is very valuable to us. To
ensure a smooth and efficient collaboration, we ask you to follow these guidelines.

---

## 📝 Code Style

To maintain consistency and readability throughout the project, we follow the **Google Style Guide
for Java**. You can check
the [official documentation](https://google.github.io/styleguide/javaguide.html) to see all the
rules in detail.

Please ensure your code complies with these rules before creating a merge request. To make the
process easier, we have prepared a
[guide](docs/guidelines/code-style.md) on how to automatically apply the style rules.

### Local Code Quality Analysis with SonarQube

To ensure code quality before committing, it is highly recommended to use the **SonarQube for IDE**
plugin in your IDE. SonarQube for IDE provides on-the-fly feedback on new bugs and quality issues as
you code.

It can be connected to the project's SonarQube server to use the same rules and quality profiles,
ensuring consistency between local analysis and the continuous integration pipeline.

---

## 🚀 Workflow (Git Flow)

This project uses **Git Flow** as the mandatory workflow. This allows us to manage the development
lifecycle, features, and fixes in an organized manner.

Below are the main branches and their purposes:

* **`main`**: Contains production-ready code. Only `release` and `hotfix` branches can be merged
  into it.
* **`develop`**: Contains the latest development code. It is the base for new features.

To contribute, you must use the auxiliary branches as follows:

* **`feature/feature-name`**: For the development of new features. They must branch from `develop`
  and, once finished, merge back into `develop`.
* **`bugfix/bug-name`**: For fixing bugs in the `develop` branch. They must branch from `develop`
  and merge back into `develop`.
* **`hotfix/bug-name`**: For fixing critical bugs in the `main` branch that cannot wait for the next
  `release`. They must branch from `main` and merge into both `main` and `develop`.

For more details on the Git Flow workflow, please refer to
the [documentation](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).

### 📦 Releasing, Tagging, and Changelog

Currently, the project does not have an automated versioning or changelog system. Therefore, a manual process is
mandatory for every new version released to the main branch.

1. Manual Tagging: Every new version merged into main (e.g., from a release or hotfix branch) must be manually tagged
   using Git.
    1. Format: Tags must follow Semantic Versioning (SemVer) and be prefixed with v.
    2. Examples: v0.1.0, v1.2.0.
2. Pushing Tags: After creating the tag locally, you must push it to the remote repository to make the release official.

```
# Ensure you are on the main branch with the latest changes
git checkout main
git pull origin main

# Create the new version tag (e.g., v1.2.0)
git tag v1.2.0

# Push the tag to the remote repository
git push origin v1.2.0
```

3. Changelog: All changes must be documented in the [CHANGELOG](./CHANGELOG.md) file. The title for each version in the
   changelog should include a link to the GitLab comparison URL, showing the diff between the new tag and the previous
   tag on main.

We aim for small, frequent deliveries and use the "Releases" feature in Jira for better version organization.

---

## ✍️ Commit Message Format (Conventional Commits)

To maintain a clean, readable, and automation-friendly commit history, it is mandatory to follow
the **Conventional Commits** standard.

Each commit message must have the following structure:

```
<type>(<scope>): <subject>
```

* **`type`**: Describes the category of the change. The most common are:
    * **`feat`**: A new feature for the user.
    * **`fix`**: A bug fix.
    * **`docs`**: Documentation only changes.
    * **`style`**: Changes that do not affect the meaning of the code (white-space, formatting,
      etc).
    * **`refactor`**: A code change that neither fixes a bug nor adds a feature.
    * **`test`**: Adding missing tests or correcting existing tests.
    * **`chore`**: Changes to the build process or auxiliary tools.
* **`scope`** (optional): Specifies the part of the codebase affected (e.g., `security`, `api`,
  `user-module`).
* **`subject`**: A short, concise description of the change in the imperative mood.

For more details, you can consult
the [official documentation](https://www.conventionalcommits.org/en/v1.0.0/).

---

## 🎣 Git Hooks

We use Git Hooks to automate quality checks at different stages of the development workflow. These
scripts are located in the `scripts/git-hooks` directory.

**To install the hooks, run the following command once after cloning the project:**

 ```sh
 ./gradlew installGitHooks
 ```

This will copy the hooks into your local `.git/hooks` directory and make them executable. The
following hooks are configured:

* **`pre-commit`**:
    * Runs `linter` to verify that the code complies with the style guide.
    * **Aborts the commit if the style check fails.**
* **`commit-msg`**:
    * Validates that your commit message follows the Conventional Commits standard.
    * **Aborts the commit if the message format is incorrect.**
* **`pre-push`**:
    * Runs all project tests via `./gradlew test`.
    * **Aborts the push if any test fails.**

 ---

## ✅ Merge Request Process

1. **Ensure your branch is up-to-date:** Before submitting, rebase your feature branch onto the
   `develop` branch to incorporate the latest changes. This helps avoid merge conflicts.
   ```bash
   git checkout develop
   git pull origin develop
   git checkout your-feature-branch
   git rebase develop
   ```
2. **Submit the Merge Request:** Push your branch and create a Merge Request against the `develop`
   branch.
    * Use a clear and descriptive title.
    * In the description, indicate to the issue you are solving.
    * Provide a summary of your changes and any necessary context for the reviewer.
3. **Code Review:** A project maintainer will review your code. Be prepared to make adjustments
   based on the feedback. Once approved, your changes will be merged.
