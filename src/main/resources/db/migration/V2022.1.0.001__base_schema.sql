#---
#--- Table structure for task_lists
#---
CREATE TABLE IF NOT EXISTS task_lists (
	uuid VARCHAR(36) NOT NULL,
	name VARCHAR(64) NOT NULL,
	description VARCHAR(256),
	created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (uuid)
);

#---
#--- Table structure for tasks
#---
CREATE TABLE IF NOT EXISTS tasks (
	uuid VARCHAR(36) NOT NULL,
	name VARCHAR(64) NOT NULL,
	description VARCHAR(256),
	status VARCHAR(36) NOT NULL,
	task_list_uuid VARCHAR(36) NOT NULL,
	created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (uuid),
    FOREIGN KEY (task_list_uuid) REFERENCES task_lists(uuid)
);