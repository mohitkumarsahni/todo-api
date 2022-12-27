#--
#-- Adding is delete column
#--
ALTER TABLE task_lists ADD is_deleted tinyint default 0;
ALTER TABLE tasks ADD is_deleted tinyint default 0;