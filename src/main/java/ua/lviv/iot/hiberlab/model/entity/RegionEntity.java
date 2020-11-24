package ua.lviv.iot.hiberlab.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "region", schema = "pavliyk_3")
public class RegionEntity {
  private Integer id;
  private String name;
  private CountryEntity countryByCountryId;

  public RegionEntity() {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RegionEntity that = (RegionEntity) o;

    if (!id.equals(that.id)) return false;
    if (!name.equals(that.name)) return false;
    return countryByCountryId.equals(that.countryByCountryId);
  }

  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + countryByCountryId.hashCode();
    return result;
  }

  @ManyToOne
  @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
  public CountryEntity getCountryByCountryId() {
    return countryByCountryId;
  }

  public void setCountryByCountryId(CountryEntity countryByCountryId) {
    this.countryByCountryId = countryByCountryId;
  }
}
