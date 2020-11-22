package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.DataAccess;
import ua.lviv.iot.hiberlab.model.dataaccess.implementation.CountryDataAccess;
import ua.lviv.iot.hiberlab.model.entity.CountryEntity;

public class CountryService extends AbstractService<CountryEntity> {
    public CountryService() {
        super(new CountryDataAccess());
    }
}
