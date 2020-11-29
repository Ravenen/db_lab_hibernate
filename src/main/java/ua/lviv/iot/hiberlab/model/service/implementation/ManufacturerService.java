package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.ManufacturerDataAccess;
import ua.lviv.iot.hiberlab.model.entity.ManufacturerEntity;

public class ManufacturerService extends AbstractService<ManufacturerEntity> {
  public ManufacturerService() {
    super(new ManufacturerDataAccess());
  }
}
