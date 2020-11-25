package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.ServiceTypeDataAccess;
import ua.lviv.iot.hiberlab.model.entity.ServiceTypeEntity;

public class ServiceTypeService extends AbstractService<ServiceTypeEntity> {
  public ServiceTypeService() {
    super(new ServiceTypeDataAccess());
  }
}
