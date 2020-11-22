package ua.lviv.iot.hiberlab.model.dataaccess.implementation;

import ua.lviv.iot.hiberlab.model.entity.ServiceEntity;

public class ServiceDataAccess extends AbstractDataAccess<ServiceEntity> {
    public ServiceDataAccess() {
        super(ServiceEntity.class);
    }
}
