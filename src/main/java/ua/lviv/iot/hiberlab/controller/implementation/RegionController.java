package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.RegionEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.RegionService;

public class RegionController extends AbstractController<RegionEntity> {
    public RegionController() {
        super(new RegionService());
    }
}
