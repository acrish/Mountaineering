package expeditions.web.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Hac
 * Date: 12/23/12
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "BUNGALOWS")
public class Bungalow implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "bungalow_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "contact")
    private String contact;

    @Column(name = "build_date")
    private Date buildDate;

    @ManyToOne
    @JoinColumn(name="map_id", insertable = false, updatable = false,
            nullable = false)
    private ExpeditionMap expMap;

    @Column(name = "picture_url")
    private String picUrl;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "price")
    private String price;

    public Bungalow(String name, String phone, String contact, Date buildDate, ExpeditionMap expMap,
                    String picUrl, Integer capacity, String price)
    {
        this.name = name;
        this.phone = phone;
        this.contact = contact;
        this.buildDate = buildDate;
        this.expMap = expMap;
        this.picUrl = picUrl;
        this.capacity = capacity;
        this.price = price;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getBuildDate() {
        return buildDate;
    }

    public void setBuildDate(Date buildDate) {
        this.buildDate = buildDate;
    }

    public ExpeditionMap getExpMap() {
        return expMap;
    }

    public void setExpMap(ExpeditionMap expMap) {
        this.expMap = expMap;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
