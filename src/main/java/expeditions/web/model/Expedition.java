package expeditions.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/10/12
 * Time: 12:02 AM
 */
@Entity
@Table(name="EXPEDITIONS")
public class Expedition implements Serializable {

    @Id
    @Column(name="expid")
    @GeneratedValue
    private Integer expId;

    @Column(name="expname")
    private String name;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="map_id")/*, insertable = false, updatable = false,
            nullable = false)*/
    private ExpeditionMap expMap;

    /*@OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "map_id")
    private List<Registry> registries;*/

    public Expedition() {
    }

    public Expedition(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Integer getExpId() {
        return expId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExpeditionMap getExpMap() {
        return expMap;
    }

    public void setExpMap(ExpeditionMap expMap) {
        this.expMap = expMap;
    }

    public String toString() {
        return String.format("%s (%s)");
    }

}
