package paysecure.common.db.mysql.model.configSystemModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ServiceConfigVersion {

    private Long id;
    private Long configId;
    private Integer version;
    private String value;
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime createdAt;

    private String updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    public ServiceConfigVersion() {}

    public ServiceConfigVersion(Long id, Long configId, Integer version, String value,
                                String status, LocalDateTime createdAt, String updatedBy,
                                LocalDateTime updatedAt) {
        this.id = id;
        this.configId = configId;
        this.version = version;
        this.value = value;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getConfigId() { return configId; }
    public void setConfigId(Long configId) { this.configId = configId; }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "ServiceConfigVersion{" +
                "id=" + id +
                ", configId=" + configId +
                ", version=" + version +
                ", value='" + value + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
