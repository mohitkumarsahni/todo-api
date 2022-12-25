package com.sahni.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
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
    private String status;

    @ManyToOne
    @JoinColumn(name="task_list_uuid")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private TaskLists taskList;

}
