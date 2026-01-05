# 4. Runtime View

The runtime view describes concrete behavior and interactions of the system’s building blocks in
form of scenarios.

## 4.1. Feature: [Feature Name]

These scenarios describe the interactions for the [feature] of the application.

### 4.1.1. Scenario: [Scenario Name]

```mermaid
sequenceDiagram
    actor User
    participant Internal_Network as [Internal Network]
    participant System as [System Name]
    User ->> Internal_Network: 1. Authenticate (username, password)
    activate Internal_Network
    Internal_Network -->> User: 2. Return Token
    deactivate Internal_Network
    User ->> System: 3. Request resource (with Token in Authorization header)
    activate System
    System ->> System: 4. Validate Token
    alt Token is valid and authorized
        System -->> User: 5. Return requested resource
    else Token is invalid or unauthorized
        System -->> User: 5. Return 401 Unauthorized or 403 Forbidden
    end
    deactivate System
```
