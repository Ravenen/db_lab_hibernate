package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.WorkmanEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.WorkmanService;

public class WorkmanController extends AbstractController<WorkmanEntity> {
    public WorkmanController() {
        super(new WorkmanService());
    }
}
