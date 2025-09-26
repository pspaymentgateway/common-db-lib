package paysecure.common.db.mysql.model.configSystemModels;

public class ServiceConfigCompareDTO{

    private Long configId;
    private String name;
    private Integer version1;
    private Integer version2;
    private String value1;
    private String value2;

    public ServiceConfigCompareDTO(Long configId, String name, Integer version1, Integer version2, String value1, String value2) {
        this.configId = configId;
        this.name = name;
        this.version1 = version1;
        this.version2 = version2;
        this.value1 = value1;
        this.value2 = value2;
    }

    // Getters and setters
    public Long getConfigId() { return configId; }
    public void setConfigId(Long configId) { this.configId = configId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getVersion1() { return version1; }
    public void setVersion1(Integer version1) { this.version1 = version1; }

    public Integer getVersion2() { return version2; }
    public void setVersion2(Integer version2) { this.version2 = version2; }

    public String getValue1() { return value1; }
    public void setValue1(String value1) { this.value1 = value1; }

    public String getValue2() { return value2; }
    public void setValue2(String value2) { this.value2 = value2; }

    @Override
    public String toString() {
        return "ServiceConfigCompareDTO{" +
                "configId=" + configId +
                ", name='" + name + '\'' +
                ", version1=" + version1 +
                ", version2=" + version2 +
                ", value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
}
