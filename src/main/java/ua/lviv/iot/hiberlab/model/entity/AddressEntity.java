package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.*;

@Entity @Table(name = "address", schema = "pavliyk_3") public class AddressEntity {
    private Integer id;
    private String street;
    private Integer house;
    private CityEntity cityByCityId;

    public AddressEntity() {
    }

    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY) public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic @Column(name = "street") public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic @Column(name = "house") public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    @ManyToOne @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false) public CityEntity getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(CityEntity cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AddressEntity that = (AddressEntity) o;

        if (!id.equals(that.id))
            return false;
        if (!street.equals(that.street))
            return false;
        if (!house.equals(that.house))
            return false;
        return cityByCityId.equals(that.cityByCityId);
    }

    @Override public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + house.hashCode();
        result = 31 * result + cityByCityId.hashCode();
        return result;
    }
}
