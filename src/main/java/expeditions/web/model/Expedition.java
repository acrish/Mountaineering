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

    @Column(name="start_village", nullable = true)
    private String startVillage;

    @Column(name="end_village", nullable = true)
    private String endVillage;

    @ManyToOne (optional = true)
    @JoinColumn(name="map_id",nullable = false)
    private ExpeditionMap expMap;

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    @Transient
    private String mountainName;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "map_id")
    private List<Registry> registries;

    public Expedition() {
    }

    public Expedition(String name, String description, String startVillage, String endVillage,
                      ExpeditionMap expMap)
    {
        this.name = name;
        this.description = description;
        this.startVillage = startVillage;
        this.endVillage = endVillage;
        this.expMap = expMap;
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
        return String.format("%d) %s (%s)", expId, name, description);
    }

    public List<Registry> getRegistries() {
        return registries;
    }

    public void setRegistries(List<Registry> registries) {
        this.registries = registries;
    }

    public String getStartVillage() {
        return startVillage;
    }

    public void setStartVillage(String startVillage) {
        this.startVillage = startVillage;
    }

    public String getEndVillage() {
        return endVillage;
    }

    public void setEndVillage(String endVillage) {
        this.endVillage = endVillage;
    }
}
