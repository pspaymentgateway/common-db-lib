package paysecure.common.db.mysql.model.configSystemModels;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ServiceConfigUpdates {

    private Long id;
    private Long configId;
    private String name;
    private String description;
    private String value;
    private String status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    private String updatedBy;
    private Integer version;

    public ServiceConfigUpdates() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getConfigId() { return configId; }
    public void setConfigId(Long configId) { this.configId = configId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public Integer getVersion() { return version; }
    public void setVersion(Integer version) { this.version = version; }

    @Override
    public String toString() {
        return "ServiceConfigUpdates{" +
                "id=" + id +
                ", configId=" + configId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                ", status='" + status + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                ", version=" + version +
                '}';
    }
}
