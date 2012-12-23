package expeditions.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/23/12
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "ExpeditionMaps")
public class ExpeditionMap implements Serializable{

    @Id
    @Column(name = "map_id")
    @GeneratedValue
    private Integer id;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "picture_url")
    private String picUrl;

    /*@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "map_id")
    private List<Expedition> expeditions;*/

    public ExpeditionMap(Date issueDate, String picUrl) {
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
