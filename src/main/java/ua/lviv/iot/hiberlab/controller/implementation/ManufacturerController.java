package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.ManufacturerEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.ManufacturerService;

public class ManufacturerController extends AbstractController<ManufacturerEntity> {
    public ManufacturerController() {
        super(new ManufacturerService());
    }
}
