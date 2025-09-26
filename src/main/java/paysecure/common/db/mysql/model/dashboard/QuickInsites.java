package paysecure.common.db.mysql.model.dashboard.dashboardView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.math.BigDecimal;



public class QuickInsites {

    private String nameId;
    private String name;
    private String type;
    private String category;
    private Object value;
    private Float  changePercentage;
    private String trend;
    private String lastPeriodComparision;
    private Boolean selected = false;


    @Override
    public String toString() {
        return "QuickInsites [nameId=" + nameId + ", name=" + name + ", type=" + type + ", category=" + category
                + ", value=" + value + ", changePercentage=" + changePercentage + ", trend=" + trend
                + ", lastPeriodComparision=" + lastPeriodComparision + ", selected=" + selected + "]";
    }
    public Boolean getSelected() {
        return selected;
    }
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNameId() {
        return nameId;
    }
    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getTrend() {
        return trend;
    }
    public void setTrend(String trend) {
        this.trend = trend;
    }
    public String getLastPeriodComparision() {
        return lastPeriodComparision;
    }
    public void setLastPeriodComparision(String lastPeriodComparision) {
        this.lastPeriodComparision = lastPeriodComparision;
    }

    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }
    public Float getChangePercentage() {
        return changePercentage;
    }
    public void setChangePercentage(Float changePercentage) {
        this.changePercentage = changePercentage;
    }

    private static double roundValue(double value){
        return Math.round(value * 100.0) / 100.0;
    }
   

}

