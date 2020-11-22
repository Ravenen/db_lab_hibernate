package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.DataAccess;
import ua.lviv.iot.hiberlab.model.dataaccess.implementation.TerminalTypeDataAccess;
import ua.lviv.iot.hiberlab.model.entity.TerminalTypeEntity;

public class TerminalTypeService extends AbstractService<TerminalTypeEntity> {
    public TerminalTypeService() {
        super(new TerminalTypeDataAccess());
    }
}
