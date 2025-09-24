package paysecure.common.db.mysql.model.dashboard.dashboardView;

import java.math.BigDecimal;

public class ChartSettings {

    // TODO - Make frequency, graphType, infoType and groupByField as ENUM


    private String frequency;
    private String graphType;
    private String infoType;
    private String groupByField;
    private String userName;
    private Long id;

    @Override
    public String toString() {
        return "ChartSettings [frequency=" + frequency + ", graphType=" + graphType + ", infoType=" + infoType
                + ", groupByField=" + groupByField + ", userName=" + userName + ", id=" + id + "]";
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getFrequency() {
        return frequency;
    }
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
    public String getGraphType() {
        return graphType;
    }
    public void setGraphType(String graphType) {
        this.graphType = graphType;
    }
    public String getInfoType() {
        return infoType;
    }
    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
    public String getGroupByField() {
        return groupByField;
    }
    public void setGroupByField(String groupByField) {
        this.groupByField = groupByField;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    
}

