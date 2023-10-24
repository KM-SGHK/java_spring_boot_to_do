package com.firstJavaCrud.entity;

import com.firstJavaCrud.request.CreateToDoRequest;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="to_do_v2")
public class ToDo {

    @Id
    @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator= "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native", // create sequence with the same name in db
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name="task_id")
    private Long taskId;

    @Column(name="task_title")
    private String taskTitle;

    @Column(name="task_body")
    private String taskBody;

    public ToDo (CreateToDoRequest createToDoRequest) {

        this.taskTitle = createToDoRequest.getTaskTitle();
        this.taskBody = createToDoRequest.getTaskBody();
    }

}
