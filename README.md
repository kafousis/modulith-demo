# 🧩 Spring Modulith + Hexagonal Architecture Demo

A demo project that shows how to combine **Spring Modulith** and **Hexagonal Architecture** to build a well-structured, modular monolith in Java.

---

## 🌿 What is Spring Modulith?

[Spring Modulith](https://docs.spring.io/spring-modulith/docs/current/reference/html/) is a framework for well-structured modular monoliths in Java:

- **Enforced module boundaries** — each top-level package is an isolated module.
- **Event publication for module communication** — modules interact exclusively through events via `ApplicationEventPublisher` and `@ApplicationModuleListener`.

---

## 🔷 What is Hexagonal Architecture?

Hexagonal Architecture (also known as _Ports & Adapters_) separates the application into three concentric layers:

| Layer | Responsibility |
|---|---|
| **Domain** | Pure business logic and domain model — no framework dependencies |
| **Application** | Use cases and ports (interfaces). Orchestrates the domain |
| **Adapters** | Entry points (REST, events) and exit points (DB, messaging) |

The **ports** are interfaces defined by the application layer. The **adapters** are their implementations. This keeps the domain and application core free of any framework or infrastructure concerns.

---

## 📦 Modules Overview

### `customers` — Customer Management

Exposes a REST API for full CRUD operations on customers (individuals and businesses). When a customer is created or updated, it publishes a domain event via Spring's `ApplicationEventPublisher`.

**Public API (accessible by other modules):**
- `customers.api.events` — `CustomerCreatedEvent`, `CustomerUpdatedEvent` (annotated with `@NamedInterface`)

### `notifications` —  Notification

Listens to customer domain events (via `@ApplicationModuleListener`) and sends notifications (welcome emails, update confirmations). It has no dependency on the `customers` module internals — only on the shared events API.

---

## 🗂️ Project Layout (Hexagonal Structure)

```
src/main/java/com/demo/
│
├── ModulithDemoApplication.java          # Spring Boot entry point
│
├── customers/                            # MODULE: Customer management
│   ├── api/
│   │   └── events/                       # Public API: domain events published to other modules
│   │       └── package-info.java         # @NamedInterface("customer-events") — exposes this package
│   ├── adapter/
│   │   ├── in/
│   │   │   └── rest/                     # [Driving Adapter] REST controllers, DTOs, mappers
│   │   └── out/
│   │       └── persistence/              # [Driven Adapter] JPA entities, repositories, persistence mapper
│   ├── application/
│   │   ├── port/
│   │   │   ├── in/                       # [Input Ports] Use case interfaces (what the app can do)
│   │   │   └── out/                      # [Output Ports] Interfaces the app depends on (DB, etc.)
│   │   ├── service/                      # Use case implementations; publishes domain events
│   │   ├── model/
│   │   │   ├── command/                  # Input commands (write operations)
│   │   │   └── view/                     # Read models / projections
│   │   └── exception/                    # Application-level exceptions
│   └── domain/
│       ├── model/                        # Pure domain entities (Customer, Address, CustomerType)
│       ├── policy/                       # Business rules (e.g. CustomerValidationPolicy)
│       └── exception/                    # Domain-level exceptions
│
└── notifications/                        # MODULE: Notifications
    ├── adapter/
    │   ├── in/
    │   │   └── events/                   # [Driving Adapter] Listens to domain events from customers module
    │   └── out/
    │       └── email/                    # [Driven Adapter] Implements NotificationDeliveryPort (logs to console)
    ├── application/
    │   ├── port/
    │   │   ├── in/                       # [Input Ports] Notification use case interfaces
    │   │   └── out/                      # [Output Port] Notification delivery abstraction
    │   └── service/                      # Builds notification messages and delegates to delivery port
    └── domain/
        └── model/                        # Notification domain model
```

---

## 🔄 End-to-End Flow

A `POST /rest/v1/customers` request travels through the following layers:

```
[HTTP Client]
    │  POST /rest/v1/customers
    ▼
[CustomerController]              adapter/in/rest
    │  delegates to
    ▼
[CustomerService]                 application/service
    │
    ├──► saves via CustomerRepositoryPort  →  H2 database
    │
    └──► publishes CustomerCreatedEvent
                │
                ▼  routed by Spring Modulith
        [CustomerEventsListener]  notifications/adapter/in/events
                │  delegates to
                ▼
        [NotificationService]     notifications/application/service
                │  sends via NotificationDeliveryPort
                ▼
             notification logged to console
```

The same flow applies to `PUT /rest/v1/customers/{id}`, which publishes a `CustomerUpdatedEvent` instead.

---

## 🌐 Trying it Out

1. Run `ModulithDemoApplication` from your IDE or via `mvn spring-boot:run`.
2. Open `src/test/resources/customers.http` in IntelliJ IDEA and execute any request using the built-in HTTP Client.
3. To see the full event-driven flow in action, send a **Create** or **Update** customer request and watch the console — the `notifications` module will log a notification triggered by the domain event.
