package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.ServiceEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.ServiceService;

public class ServiceController extends AbstractController<ServiceEntity> {
    public ServiceController() {
        super(new ServiceService());
    }
}
