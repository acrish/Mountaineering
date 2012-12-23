package expeditions.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/23/12
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "REGISTRIES")
public class Registry implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "registry_id")
    private Integer id;

    /*@ManyToOne
    @JoinColumn(name="expedition_id", insertable = false, updatable = false,
            nullable = false)
    private Expedition exp;*/

    @ManyToOne
    @JoinColumn(name = "participant_id")
    private Participant participant;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    public Registry(/*Expedition exp,*/ Participant participant, Date startDate, Date endDate) {
        /*this.exp = exp;*/
        this.participant = participant;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getId() {
        return id;
    }

    /*public Expedition getExp() {
        return exp;
    }

    public void setExp(Expedition exp) {
        this.exp = exp;
    }*/

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
