package expeditions.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/23/12
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "EXPEDITIONMAPS")
public class ExpeditionMap implements Serializable{

    @Id
    @Column(name = "map_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "mountain")
    private String mountain;

    public String getMountain() {
        return mountain;
    }

    public void setMountain(String mountain) {
        this.mountain = mountain;
    }

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "picture_url")
    private String picUrl;

    public Set<Expedition> getExpeditions() {
        return expeditions;
    }

    public void setExpeditions(Set<Expedition> expeditions) {
        this.expeditions = expeditions;
    }

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "map_id")
    private Set<Expedition> expeditions;

    public ExpeditionMap(String mountain, Date issueDate, String picUrl) {
        this.mountain = mountain;
        this.issueDate = issueDate;
        this.picUrl = picUrl;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


}
