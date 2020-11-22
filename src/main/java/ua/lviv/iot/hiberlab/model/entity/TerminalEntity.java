package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "terminal", schema = "pavliyk_3")
public class TerminalEntity {
    private Integer id;
    private String gpsCoordinates;
    private Date commissioningDate;
    private ManufacturerEntity manufacturerByManufacturerId;
    private AddressEntity addressByAddressId;

    public TerminalEntity() {
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
    @Column(name = "gps_coordinates")
    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    @Basic
    @Column(name = "commissioning_date")
    public Date getCommissioningDate() {
        return commissioningDate;
    }

    public void setCommissioningDate(Date commissioningDate) {
        this.commissioningDate = commissioningDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TerminalEntity that = (TerminalEntity) o;

        if (!id.equals(that.id)) return false;
        if (!gpsCoordinates.equals(that.gpsCoordinates)) return false;
        if (!commissioningDate.equals(that.commissioningDate)) return false;
        if (!manufacturerByManufacturerId.equals(that.manufacturerByManufacturerId)) return false;
        return addressByAddressId.equals(that.addressByAddressId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + gpsCoordinates.hashCode();
        result = 31 * result + commissioningDate.hashCode();
        result = 31 * result + manufacturerByManufacturerId.hashCode();
        result = 31 * result + addressByAddressId.hashCode();
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    public ManufacturerEntity getManufacturerByManufacturerId() {
        return manufacturerByManufacturerId;
    }

    public void setManufacturerByManufacturerId(ManufacturerEntity manufacturerByManufacturerId) {
        this.manufacturerByManufacturerId = manufacturerByManufacturerId;
    }

    @ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    public AddressEntity getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(AddressEntity addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
