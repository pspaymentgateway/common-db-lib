package paysecure.common.db.mysql.model.configSystemModels;

public class ServiceConfigDTO {

    private String name;
    private String description;
    private String value;
    private String updatedBy;
    private String createdBy;

//    public ServiceConfigDTO(String name, String description, String value, String updatedBy, String createdBy) {
//        this.name = name;
//        this.description = description;
//        this.value = value;
//        this.updatedBy = updatedBy;
//        this.createdBy = createdBy;
//    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }

    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }

    @Override
    public String toString() {
        return "ServiceConfigDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value='" + value + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceConfigDTO)) return false;

        ServiceConfigDTO that = (ServiceConfigDTO) o;

        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        if (!value.equals(that.value)) return false;
        if (!updatedBy.equals(that.updatedBy)) return false;
        return createdBy.equals(that.createdBy);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + updatedBy.hashCode();
        result = 31 * result + createdBy.hashCode();
        return result;
    }
}
