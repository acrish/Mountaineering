package expeditions.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/23/12
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Participants")
public class Participant implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "participant_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "map_id")
    private List<Registry> registries;

    public Participant(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Registry> getRegistries() {
        return registries;
    }

    public void setRegistries(List<Registry> registries) {
        this.registries = registries;
    }
}
