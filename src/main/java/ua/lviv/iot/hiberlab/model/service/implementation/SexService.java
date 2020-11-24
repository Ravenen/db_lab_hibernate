package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.SexDataAccess;
import ua.lviv.iot.hiberlab.model.entity.SexEntity;

public class SexService extends AbstractService<SexEntity> {
    public SexService() {
        super(new SexDataAccess());
    }
}
