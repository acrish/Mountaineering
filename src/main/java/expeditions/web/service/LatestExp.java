package expeditions.web.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 1/5/13
 * Time: 11:43 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="LATEST_EXP")
public class LatestExp implements Serializable {

    @Id
    @GeneratedValue
    Integer id;


            String mname;
            String ename;
            String expstart;
            String expend;
            String pname;
            Date pdate;

    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s", mname, ename, expstart, expend, pname,
                pdate.toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getExpstart() {
        return expstart;
    }

    public void setExpstart(String expstart) {
        this.expstart = expstart;
    }

    public String getExpend() {
        return expend;
    }

    public void setExpend(String expend) {
        this.expend = expend;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
}
