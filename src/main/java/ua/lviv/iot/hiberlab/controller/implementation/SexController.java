package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.SexEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.SexService;

public class SexController extends AbstractController<SexEntity> {
    public SexController() {
        super(new SexService());
    }
}
