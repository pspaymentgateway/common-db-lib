
package paysecure.common.db.mysql.model.dashboard.dashboardView;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuButton {

    private String label;
    private String labledId;
    private String path;
    private List<String> visibility;
    private List<MenuButton> submenu;
    private String parentlableId;
    private Boolean header=null;
    private Boolean isV2RouteEnabled;
    private Boolean footer=null;
    

    

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabledId() {
        return labledId;
    }
    public void setLabledId(String labledId) {
        this.labledId = labledId;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public List<String> getVisibility() {
        return visibility;
    }
    public void setVisibility(List<String> visibility) {
        this.visibility = visibility;
    }
    public List<MenuButton> getSubmenu() {
        return submenu;
    }
    public void setSubmenu(List<MenuButton> submenu) {
        this.submenu = submenu;
    }
    public String getParentlableId() {
        return parentlableId;
    }
    public void setParentlableId(String parentlableId) {
        this.parentlableId = parentlableId;
    }

    public Boolean getHeader() {
        return header;
    }

    public void setHeader(Boolean header) {
        this.header = header;
    }

    public Boolean getIsV2RouteEnabled() {
        return isV2RouteEnabled;
    }

    public void setIsV2RouteEnabled(Boolean isV2RouteEnabled) {
        this.isV2RouteEnabled = isV2RouteEnabled;
    }

    public Boolean getFooter() { return footer; }

    public void setFooter(Boolean footer) { this.footer = footer; }


}

