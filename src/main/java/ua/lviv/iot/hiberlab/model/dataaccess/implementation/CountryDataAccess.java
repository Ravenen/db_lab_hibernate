package ua.lviv.iot.hiberlab.model.dataaccess.implementation;

import ua.lviv.iot.hiberlab.model.entity.CountryEntity;

public class CountryDataAccess extends AbstractDataAccess<CountryEntity> {
  public CountryDataAccess() {
    super(CountryEntity.class);
  }
}
