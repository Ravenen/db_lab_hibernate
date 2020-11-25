package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.TerminalEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.AddressService;
import ua.lviv.iot.hiberlab.model.service.implementation.ManufacturerService;
import ua.lviv.iot.hiberlab.model.service.implementation.TerminalService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TerminalController extends AbstractController<TerminalEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "gps_coordinates", "commissioning_date", "manufacturer_id", "address_id");

    public TerminalController() {
        super(new TerminalService());
    }

    @Override
    public List<TerminalEntity> findAll() {
        List<TerminalEntity> entities = super.findAll();
        if (entities != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            for (TerminalEntity entity : entities) {
                body.add(entityToList(entity));
            }
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entities;
    }

    @Override
    public TerminalEntity findById(Integer id) {
        TerminalEntity entity = super.findById(id);
        if (entity != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            body.add(entityToList(entity));
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entity;
    }

    @Override
    public TerminalEntity create(TerminalEntity entity) {
        super.enterValueForColumn(entity, TerminalEntity::setGpsCoordinates, "gps_coordinates", String.class, true, 24);
        super.enterValueForColumn(entity, TerminalEntity::setCommissioningDate, "commissioning_date", Date.class, true, -1);
        super.enterEntityValueForColumn(entity, TerminalEntity::setManufacturerByManufacturerId, "manufacturer_id", new ManufacturerService(), false);
        super.enterEntityValueForColumn(entity, TerminalEntity::setAddressByAddressId, "address_id", new AddressService(), false);
        TerminalEntity createdEntity = super.create(entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(createdEntity));
        Formatter.formatTable(headerList, body);
        return createdEntity;
    }

    @Override
    public TerminalEntity update(Integer id, TerminalEntity entity) {
        Scanner input = new Scanner(System.in, "UTF-8");
        String column;
        while (true) {
            System.out.println(ENTER_COLUMN_NAME);
            for (String columnName : COLUMNS_NAMES) {
                System.out.println("\t-" + columnName);
            }
            String inputText = input.nextLine();
            if (COLUMNS_NAMES.contains(inputText)) {
                column = inputText;
                break;
            } else {
                System.out.println(ERROR_INVALID_VALUE);
            }
        }
        switch (column) {
            case ("gps_coordinates"):
                super.enterValueForColumn(entity, TerminalEntity::setGpsCoordinates, "gps_coordinates", String.class, true, 24);
                break;
            case ("commissioning_date"):
                super.enterValueForColumn(entity, TerminalEntity::setCommissioningDate, "commissioning_date", Date.class, true, -1);
                break;
            case ("manufacturer_id"):
                super.enterEntityValueForColumn(entity, TerminalEntity::setManufacturerByManufacturerId, "manufacturer_id", new ManufacturerService(), false);
                break;
            case ("address_id"):
                super.enterEntityValueForColumn(entity, TerminalEntity::setAddressByAddressId, "address_id", new AddressService(), false);
                break;
        }
        TerminalEntity oldEntity = super.update(id, entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(oldEntity));
        body.add(entityToList(entity));
        Formatter.formatTable(headerList, body);
        return entity;
    }

    @Override
    public TerminalEntity delete(Integer id) {
        TerminalEntity entity = super.delete(id);
        if (entity != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            body.add(entityToList(entity));
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entity;
    }

    @Override
    protected List<String> entityToList(TerminalEntity entity) {
        String id = entity.getId().toString();
        String gpsCoordinates = entity.getGpsCoordinates() == null ? "-" : entity.getGpsCoordinates();
        String commissioningDate = entity.getCommissioningDate() == null ? "-" : entity.getCommissioningDate().toString();
        String manufacturerId = entity.getManufacturerByManufacturerId().getId().toString();
        String addressId = entity.getAddressByAddressId().getId().toString();
        return new LinkedList<>(List.of(id, gpsCoordinates, commissioningDate, manufacturerId, addressId));
    }
}
