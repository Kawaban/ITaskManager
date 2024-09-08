package api.developer.domain;


import api.infrastructure.model.AbstractEntity;
import api.infrastructure.model.Rank;
import api.infrastructure.model.Specialization;
import api.project.domain.Project;
import api.task.domain.Task;
import api.task.domain.TaskLog;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "itaskmanager_developers")
public class Developer extends AbstractEntity {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "itaskmanager_projects_developers",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @OneToMany(mappedBy = "developer", fetch = FetchType.LAZY)
    private List<TaskLog> tasksLogs;

    @Builder
    public Developer(UUID id, long version, Instant createdDate, Instant lastModifiedDate, Specialization specialization, String username) {
        super(id, version, createdDate, lastModifiedDate);
        this.specialization = specialization;
        this.username = username;
    }

    public Rank calculateRank(int estimation) {
        Rank rank = new Rank(estimation, super.getUuid());
        for (TaskLog taskLog : tasksLogs) {
            if (taskLog.getEstimation() == estimation)
                rank.updateRank(taskLog.calculateRank());
        }
        if (rank.getPower() == 0)
            rank.setDefaultRank();
        return rank;
    }
}
