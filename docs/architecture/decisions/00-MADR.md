---
status: "accepted"
date: 2025-10-01
---

# Use MADR

## Context and Problem Statement

The team needs a simple, low-overhead method for documenting architectural decisions that is
suitable for a small, early-stage project and won't slow down development.

This approach aims to avoid the following risks:

* **Loss of architectural knowledge:** Ensures that decisions, their context, and consequences are
  explicitly recorded and not lost over time or with team changes.
* **Inconsistent architectural decisions:** Provides a central, version-controlled record that
  promotes consistency across the project.

## Considered Options

* **MADR (Markdown Architectural Decision Records):** A lightweight approach using Markdown files to
  record architectural decisions.
* **ADR (Architectural Decision Records):** A more general concept of documenting architectural
  decisions, often implemented with various tools and templates.
* **Wiki/Confluence pages:** Using a wiki or Confluence to document decisions, offering rich text
  editing and collaboration features.
* **Dedicated architecture documentation tools:** Tools like Archi or Sparx Enterprise Architect,
  which provide comprehensive modeling and documentation capabilities.

## Decision Outcome

Chosen Option: "MADR"

MADR was chosen due to its simplicity and low overhead. This approach allows us to:

* **Keep documentation close to the code:** MADR files can be stored directly within the project
  repository, making them easily accessible and version-controlled alongside the source code.
* **Leverage existing tools:** Developers are already familiar with Markdown and Git, minimizing the
  learning curve.
* **Focus on content over format:** The lightweight nature of Markdown encourages concise and
  focused decision documentation.
* **Facilitate collaboration:** Changes to MADR files can be reviewed and discussed through standard
  pull request workflows.

While other options offer more advanced features, they were deemed too heavy for our current needs
and team size.
Dedicated tools would introduce additional complexity and licensing costs, and wiki pages might lead
to fragmentation of documentation outside the version control system.

### Consequences

* Good, because all architectural decisions will be documented using the MADR format in Markdown
  files.
* Good, because these files will be stored in a dedicated `docs/architecture/decisions` directory
  within the project repository.
* Good, because a consistent naming convention for MADR files will be established (e.g.,
  `NN-short-description.md`).
* Good, because a consistent template will be used (ADR Template
  Minimal)[https://github.com/adr/madr/blob/develop/template/adr-template.md]
* Bad, because the simplicity of MADR means it might lack some advanced features for complex
  diagrams or cross-referencing that dedicated tools offer.
