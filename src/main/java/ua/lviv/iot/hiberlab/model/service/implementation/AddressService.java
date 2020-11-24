package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.AddressDataAccess;
import ua.lviv.iot.hiberlab.model.entity.AddressEntity;

public class AddressService extends AbstractService<AddressEntity> {
    public AddressService() {
        super(new AddressDataAccess());
    }
}
