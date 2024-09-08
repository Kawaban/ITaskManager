package api.assignment.domain;

import api.infrastructure.model.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "itaskmanager_assignments")
class Assignment extends AbstractEntity {
    @Column(nullable = false)
    private UUID developerId;

    @Column(nullable = false)
    private UUID taskId;

    @Builder
    public Assignment(UUID id, long version, Instant createdDate, Instant lastModifiedDate, UUID projectId, UUID developerId, UUID taskId) {
        super(id, version, createdDate, lastModifiedDate);
        this.developerId = developerId;
        this.taskId = taskId;
    }
}
