package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.RegionDataAccess;
import ua.lviv.iot.hiberlab.model.entity.RegionEntity;

public class RegionService extends AbstractService<RegionEntity> {
    public RegionService() {
        super(new RegionDataAccess());
    }
}
