package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.ServiceDataAccess;
import ua.lviv.iot.hiberlab.model.entity.ServiceEntity;

public class ServiceService extends AbstractService<ServiceEntity> {
    public ServiceService() {
        super(new ServiceDataAccess());
    }
}
