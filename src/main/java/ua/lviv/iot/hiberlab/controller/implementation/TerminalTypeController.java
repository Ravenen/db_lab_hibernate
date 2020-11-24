package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.TerminalTypeEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.TerminalTypeService;

public class TerminalTypeController extends AbstractController<TerminalTypeEntity> {
    public TerminalTypeController() {
        super(new TerminalTypeService());
    }
}
