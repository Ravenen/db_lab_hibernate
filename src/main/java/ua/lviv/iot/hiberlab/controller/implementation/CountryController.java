package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.CountryEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.CountryService;

public class CountryController extends AbstractController<CountryEntity> {
    public CountryController() {
        super(new CountryService());
    }
}
