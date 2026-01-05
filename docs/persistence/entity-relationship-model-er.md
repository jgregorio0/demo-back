# Entity relationship model (ER)

This ER diagram outlines the relationships between various entities involved in the system.

> Notes: not all table and columns are represented here only basics

# Main Entities

```mermaid
erDiagram
    e["ENTITY_1 (SCHEMA.TABLE_1)"] {
        int ID PK
        string FIELD_1
    }

    pla["ENTITY_2 (SCHEMA.TABLE_2)"] {
        int ID PK
        int ENTITY_1_ID FK "SCHEMA.TABLE_1.ID"
        date DATE_FIELD
        string TEXT_FIELD
    }

    e ||--o{ pla: "relates to"
```