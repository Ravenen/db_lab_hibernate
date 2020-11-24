package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.ServiceTypeEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.ServiceTypeService;

public class ServiceTypeController extends AbstractController<ServiceTypeEntity> {
  public ServiceTypeController() {
    super(new ServiceTypeService());
  }
}
