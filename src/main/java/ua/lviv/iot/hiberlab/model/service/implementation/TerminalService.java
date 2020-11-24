package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.TerminalDataAccess;
import ua.lviv.iot.hiberlab.model.entity.TerminalEntity;

public class TerminalService extends AbstractService<TerminalEntity> {
    public TerminalService() {
        super(new TerminalDataAccess());
    }
}
