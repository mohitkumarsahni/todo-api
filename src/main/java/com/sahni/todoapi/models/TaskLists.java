package com.sahni.todoapi.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "task_lists")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskLists extends Auditable {

    @Id
    @Column(name = "uuid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_deleted", insertable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean isDeleted;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @OneToMany(mappedBy = "taskList")
    private List<Tasks> tasks;
}
