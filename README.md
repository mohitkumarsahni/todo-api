## ToDo-API
An API for creating and managing tasks.

### The API can perform following operations:

- [x] Create a new task-list.
- [x] Update a task-list.
- [x] Fetch a task-list.
- [x] Delete a task-list.
- [x] Create a new task in a task-list.
- [x] Update a task in a task-list.
- [x] Fetch a task.
- [x] Fetch all tasks in a task-list.
- [x] Delete a task in a task-list.

Please see the [openapi contract](./docs/api-contract.yaml) for API endpoints and details.

### Prerequisites

#### Install Java 11
- This application requires Java 11.
- Please refer to the following link to download and install Java-11: [Java-11](https://www.oracle.com/in/java/technologies/javase/jdk11-archive-downloads.html)

#### Install MySQL-8
- This application requires MySQL 8.
- Please refer to the following link to download and install MySQL-8: [MySQL-8](https://dev.mysql.com/doc/refman/8.0/en/installing.html)

#### Create database
- Create a database.
- Create a user and grant access to the user on database created in previous step.

### Running the application
- Clone the repository.
- Open the project in IntelliJ IDEA or any suitable IDE of your choice.
- Add following environment variables in configurations:
  - DB_HOST
  - DB_NAME
  - DB_PASSWORD
  - DB_PORT
  - DB_USERNAME
- In IntelliJ IDEA, environment variables can be added from Run -> Edit Configurations -> Environment Variables
- Run the project.