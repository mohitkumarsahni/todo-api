package com.sahni.todoapi.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tasks extends Auditable {

    @Id
    @Column(name = "uuid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private TaskStatus status;

    @Column(name = "is_deleted", insertable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    Boolean isDeleted;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name="task_list_uuid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private TaskLists taskList;

}
