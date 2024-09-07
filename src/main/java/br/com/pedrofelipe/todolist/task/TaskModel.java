package br.com.pedrofelipe.todolist.task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;
    
    @CreationTimestamp
    private LocalDateTime createAt;
    private UUID idUser;
    
    
    public void setTitle(String title) throws Exception{
        if(title.length() > 50){
            throw new Exception("Passou de 50 caracteres");
        }
        if(title.length()==0){
            throw new Exception("Vazio");
        }
        this.title = title;
    }
}
