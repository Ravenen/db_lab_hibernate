package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.CityDataAccess;
import ua.lviv.iot.hiberlab.model.entity.CityEntity;

public class CityService extends AbstractService<CityEntity> {
    public CityService() {
        super(new CityDataAccess());
    }
}
