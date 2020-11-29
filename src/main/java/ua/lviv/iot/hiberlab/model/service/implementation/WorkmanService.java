package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.WorkmanDataAccess;
import ua.lviv.iot.hiberlab.model.entity.WorkmanEntity;

public class WorkmanService extends AbstractService<WorkmanEntity> {
  public WorkmanService() {
    super(new WorkmanDataAccess());
  }
}
