
# Notification Service System

## Overview

The Notification Service System is designed to manage notifications, handle events, process commands, and interact with entities through queries. It follows an event-driven architecture to ensure decoupling and scalability.

## Components

### Notification Service

The `Notification Service` executes commands related to notifications and processes events. It interacts with repositories to fetch, save, or delete notification logs.

### Event-Driven Architecture

The system uses an event-driven architecture where events trigger specific actions within the system. This design enhances scalability and maintainability.

## How to Run the Application

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-repo/notification-service.git
   cd notification-service
   ```

2. **Set Up Environment**
   Ensure you have Java JDK 11+ and Maven installed. Configure your environment variables and database settings as needed.

3. **Build the Application**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
    - **API Endpoints:**
        - `GET /api/notifications/query/all` - Fetch all notification logs.
        - `GET /api/notifications/query/{userId}` - Fetch notification logs by user ID.

6. **Event Handling**
    - Ensure Kafka is configured to send `BudgetExceedEvent` and `DeleteUserEvent` to the service.

## Process Flow Diagram

```mermaid
graph TD
    subgraph Notification Commands
        NC[Notification Log Controller] -->|Fetch All| FNLC[FetchAllNotificationLogsCommand]
        NC -->|Fetch by User ID| FNLUIC[FetchAllNotificationLogsByUserIdCommand]
        
        FNLC --> NS[Notification Service]
        FNLUIC --> NS

        NS --> FNLCExec[FetchAllNotificationLogsCommandExecutor]
        NS --> FNLUICExec[FetchAllNotificationLogsByUserIdCommandExecutor]

        FNLCExec -->|Fetch from| NLR[NotificationLog Repository]
        FNLUICExec -->|Fetch from| NLR

        NLR --> DB1[(NotificationLog Entity)]
    end

    subgraph Events and Executors
        EC[Event Listener] -->|Budget Exceed Event| BEE[BudgetExceedEvent]
        EC -->|Delete User Event| DUE[DeleteUserEvent]

        BEE --> NS2[Notification Service]
        DUE --> NS2

        NS2 --> BEEExec[BudgetExceedEventExecutor]
        NS2 --> DUEExec[DeleteUserEventExecutor]

        BEEExec -->|Save to| NLR2[NotificationLog Repository]
        DUEExec -->|Delete from| NLR2

        NLR2 --> DB2[(NotificationLog Entity)]
    end

    subgraph Notification Command Flow
        BEEExec -->|Trigger| NCMD[NotificationCommand]
        NCMD --> NS3[Notification Service]

        NS3 --> NCMDExec[NotificationCommandExecutor]
        NCMDExec -->|Save to| NLR3[NotificationLog Repository]
        NCMDExec -->|Trigger| EN[Email Notification]

        NLR3 --> DB3[(NotificationLog Entity)]
    end
```
### Notification Commands

1. **Notification Log Controller** receives HTTP requests and creates commands.
2. Commands are sent to the `Notification Service`.
3. The `Notification Service` invokes the corresponding Command Executors.
4. Executors interact with the `NotificationLog Repository` to fetch or save data.
5. The `NotificationLog Repository` performs operations on the `NotificationLog Entity` (DB).

### Events and Executors

1. **Event Listener** receives events and invokes the appropriate Event Executors.
2. The `BudgetExceedEvent` and `DeleteUserEvent` trigger specific actions within the `Notification Service`.
3. Executors interact with the `NotificationLog Repository` to perform operations based on the events.
4. The `NotificationLog Repository` updates or deletes records in the `NotificationLog Entity` (DB).

### Notification Command Flow

1. **Event Executors** trigger `NotificationCommand`.
2. The `NotificationCommand` is processed by the `Notification Service`.
3. The `NotificationCommandExecutor` handles the command, triggers email notifications, and interacts with the `NotificationLog Repository`.
4. The `NotificationLog Repository` updates the `NotificationLog Entity` (DB) with the notification details.

## Class Diagrams

### 1. Event Class Diagram

```mermaid
classDiagram
    class BudgetExceedEvent {
        +Long expenseId
        +Long userId
        +String expenseName
        +String expenseDescription
        +String expenseType
        +Double amount
        +Double actualAmount
        +String recordType
        +Long budgetId
        +String budgetType
        +Double budgetAmount
        +Double budgetSpent
        +String emailId
    }

    class DeleteUserEvent {
        +Long userId
    }
```

### 2. Entity Class Diagram

```mermaid
classDiagram
    class NotificationLog {
        +Long notificationId
        +Long userId
        +String eventType
        +String payload
        +String notificationStatus
        +String email
    }
```

### 3. Query Class Diagram

```mermaid
classDiagram
    class FetchAllNotificationLogsCommand {
    }

    class FetchAllNotificationLogsByUserIdCommand {
        +Long userId
    }
```

### 4. Command Class Diagram

```mermaid
classDiagram
    class NotificationCommand {
        +Long userId
        +String eventName
        +String payload
        +Map<String, Object> content
        +String templateId
        +String emailId
        +String subject
    }
```



