package api.project.domain;

import api.developer.domain.Developer;
import api.infrastructure.model.AbstractEntity;
import api.task.domain.Task;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "itaskmanager_projects")
@NoArgsConstructor
public class Project extends AbstractEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "itaskmanager_projects_developers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id")
    )
    private List<Developer> projectDevelopers;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder
    public Project(UUID id, long version, Instant createdDate, Instant lastModifiedDate, String name, ArrayList<Developer> projectDevelopers) {
        super(id, version, createdDate, lastModifiedDate);
        this.name = name;
        this.projectDevelopers = projectDevelopers;
    }
}
