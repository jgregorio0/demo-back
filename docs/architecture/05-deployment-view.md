# 5. Deployment View

## 5.1 [Environment Name] environment

```mermaid
graph TD
    subgraph "Cloud Provider"
        subgraph "Kubernetes Cluster"
            direction LR
            subgraph "Namespace [namespace]"
                sso["SSO (POD)"]
                system["[System Name] (POD)"]
                client["[Client Name] (POD)"]
                core_app["[Core App] (POD)"]
                service_a["[External Service A] (POD)"]
                service_b["[External Service B] (POD)"]
            end
        end
        db[("Database")]
        volume_shared["shared-storage /path/to/resources"]
    end

    user["User"]
    user -- HTTPS --> sso
    user -- HTTPS --> core_app
    core_app -- Embedded --> client
    client -- HTTPS --> system
    system -- HTTPS --> service_a
    system -- HTTPS --> service_b
    system -- JDBC --> db
    system -- File I/O --> volume_shared

```

### Motivation

The [Environment Name] environment diagram illustrates the deployment of the `[System Name]` application
and its dependencies within the Kubernetes Cluster, specifically in the `[namespace]`
namespace.

### Mapping

| Node                     | Description                             |                                                                                                                
|:-------------------------|:----------------------------------------|
| sso (POD)                | POD for `SSO` application.              |
| core_app (POD)           | POD for `[Core App]` application.         |
| client (POD)             | POD for `[Client Name]` application.|
| system (POD)             | POD for `[System Name]` application.|
| service_a (POD)          | POD for `[External Service A]` application.          |
| service_b (POD)          | POD for `[External Service B]` application.          |
| db (POD)           | Database.                               |                         
| volume_shared           | Mounted shared folder.                  |

## 5.2 [Environment Name 2] environment

```mermaid
graph TD
    subgraph "Cloud Provider"
        subgraph "Kubernetes Cluster"
            direction LR
            subgraph "Namespace [namespace 2]"
                sso["sso (POD)"]
                system["[System Name] (POD)"]
                client["[Client Name] (POD)"]
                core_app["[Core App] (POD)"]
                service_a["[External Service A] (POD)"]
                service_b["[External Service B] (POD)"]
            end
        end
        db[("Database (POD)")]
        volume_shared["shared-storage /path/to/resources"]
    end
    user["User"]
    user -- HTTPS --> sso
    user -- HTTPS --> core_app
    core_app -- Embedded --> client
    client -- HTTPS --> system
    system -- HTTPS --> service_a
    system -- HTTPS --> service_b
    system -- JDBC --> db
    system -- File I/O --> volume_shared
```

### Motivation

The [Environment Name 2] environment diagram illustrates the deployment of the `[System Name]`
application and its dependencies within the Kubernetes Cluster, specifically in the
`[namespace 2]` namespace.

### Mapping

| Node                     | Description                             |                                                                                                                
|:-------------------------|:----------------------------------------|
| sso (POD)                | POD for `SSO` application.              |
| core_app (POD)           | POD for `[Core App]` application.         |
| client (POD)             | POD for `[Client Name]` application.|
| system (POD)             | POD for `[System Name]` application.|
| service_a (POD)          | POD for `[External Service A]` application.          |
| service_b (POD)          | POD for `[External Service B]` application.          |
| Database (POD)           | Database.                               |                         
| shared-storage           | Mounted shared folder.                  |
