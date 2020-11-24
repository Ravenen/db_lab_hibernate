package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "workman", schema = "pavliyk_3")
public class WorkmanEntity {
    private Integer id;
    private String name;
    private String surname;
    private BigDecimal pricePerHourUah;
    private String contactNumber;
    private SexEntity sexBySexId;
    private PostEntity postByPostId;

    public WorkmanEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "price_per_hour_uah")
    public BigDecimal getPricePerHourUah() {
        return pricePerHourUah;
    }

    public void setPricePerHourUah(BigDecimal pricePerHourUah) {
        this.pricePerHourUah = pricePerHourUah;
    }

    @Basic
    @Column(name = "contact_number")
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        WorkmanEntity that = (WorkmanEntity) o;

        if (!id.equals(that.id))
            return false;
        if (!name.equals(that.name))
            return false;
        if (!surname.equals(that.surname))
            return false;
        if (!pricePerHourUah.equals(that.pricePerHourUah))
            return false;
        if (!Objects.equals(contactNumber, that.contactNumber))
            return false;
        if (!Objects.equals(sexBySexId, that.sexBySexId))
            return false;
        return postByPostId.equals(that.postByPostId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + pricePerHourUah.hashCode();
        result = 31 * result + (contactNumber != null ? contactNumber.hashCode() : 0);
        result = 31 * result + (sexBySexId != null ? sexBySexId.hashCode() : 0);
        result = 31 * result + postByPostId.hashCode();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "sex_id", referencedColumnName = "id")
    public SexEntity getSexBySexId() {
        return sexBySexId;
    }

    public void setSexBySexId(SexEntity sexBySexId) {
        this.sexBySexId = sexBySexId;
    }

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    public PostEntity getPostByPostId() {
        return postByPostId;
    }

    public void setPostByPostId(PostEntity postByPostId) {
        this.postByPostId = postByPostId;
    }
}
