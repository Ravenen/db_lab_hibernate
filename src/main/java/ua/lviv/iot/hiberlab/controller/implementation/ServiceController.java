package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.ServiceEntity;
import ua.lviv.iot.hiberlab.model.entity.ServiceTypeEntity;
import ua.lviv.iot.hiberlab.model.entity.TerminalEntity;
import ua.lviv.iot.hiberlab.model.entity.WorkmanEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.ServiceService;
import ua.lviv.iot.hiberlab.model.service.implementation.ServiceTypeService;
import ua.lviv.iot.hiberlab.model.service.implementation.TerminalService;
import ua.lviv.iot.hiberlab.model.service.implementation.WorkmanService;
import ua.lviv.iot.hiberlab.view.Formatter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ServiceController extends AbstractController<ServiceEntity> {

    private static final List<String> COLUMNS_NAMES = List.of("id", "date", "terminal_id", "workman_id", "service_type_id", "duration_in_hours", "total_price_uah");

    public ServiceController() {
        super(new ServiceService());
    }


    @Override
    public List<ServiceEntity> findAll() {
        List<ServiceEntity> entities = super.findAll();
        if (entities != null) {
            List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
            List<List<String>> body = new LinkedList<>();
            for (ServiceEntity entity : entities) {
                body.add(entityToList(entity));
            }
            Formatter.formatTable(headerList, body);
        } else {
            System.out.println(ERROR_NO_MATCHES_FOUND);
        }
        return entities;
    }

    @Override
    public ServiceEntity findById(Integer id) {
        ServiceEntity entity = super.findById(id);
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
    public ServiceEntity create(ServiceEntity entity) {
        super.enterValueForColumn(entity, ServiceEntity::setDate, "date", Date.class, false, -1);
        super.enterValueForColumn(entity, ServiceEntity::setDurationInHours, "duration_in_hours", BigDecimal.class, false, -1);
        Scanner input = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, "terminal_id", "", "");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                Service<TerminalEntity> terminalService = new TerminalService();
                TerminalEntity terminal = terminalService.findById(value);
                if (terminal != null) {
                    entity.setTerminalByTerminalId(terminal);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_INVALID_VALUE);
                System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
            }
        }
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, "workman_id", "", "");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                Service<WorkmanEntity> workmanService = new WorkmanService();
                WorkmanEntity workman = workmanService.findById(value);
                if (workman != null) {
                    entity.setWorkmanByWorkmanId(workman);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ERROR_INVALID_VALUE);
                System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
            }
        }
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, "service_type_id", "", "(optional)");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                Service<ServiceTypeEntity> serviceTypeService = new ServiceTypeService();
                ServiceTypeEntity serviceType = serviceTypeService.findById(value);
                if (serviceType != null) {
                    entity.setServiceTypeByServiceTypeId(serviceType);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                if (!inputText.equals("")) {
                    System.out.println(ERROR_INVALID_VALUE);
                    System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                } else {
                    break;
                }
            }
        }
        ServiceEntity createdEntity = super.create(entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(createdEntity));
        Formatter.formatTable(headerList, body);
        return createdEntity;
    }

    @Override
    public ServiceEntity update(Integer id, ServiceEntity entity) {
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
            case ("date"):
                super.enterValueForColumn(entity, ServiceEntity::setDate, "date", Date.class, false, -1);
                break;
            case ("duration_in_hours"):
                super.enterValueForColumn(entity, ServiceEntity::setDurationInHours, "duration_in_hours", BigDecimal.class, false, -1);
                break;
            case ("terminal_id"):
                while (true) {
                    System.out.printf(ENTER_DATA_FORMAT, "terminal_id", "", "");
                    String inputText = input.nextLine();
                    try {
                        Integer value = Integer.parseInt(inputText);
                        Service<TerminalEntity> terminalService = new TerminalService();
                        TerminalEntity terminal = terminalService.findById(value);
                        if (terminal != null) {
                            entity.setTerminalByTerminalId(terminal);
                            break;
                        } else {
                            System.out.println(ERROR_INVALID_VALUE);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(ERROR_INVALID_VALUE);
                        System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                    }
                }
                break;
            case ("workman_id"):
                while (true) {
                    System.out.printf(ENTER_DATA_FORMAT, "workman_id", "", "");
                    String inputText = input.nextLine();
                    try {
                        Integer value = Integer.parseInt(inputText);
                        Service<WorkmanEntity> workmanService = new WorkmanService();
                        WorkmanEntity workman = workmanService.findById(value);
                        if (workman != null) {
                            entity.setWorkmanByWorkmanId(workman);
                            break;
                        } else {
                            System.out.println(ERROR_INVALID_VALUE);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(ERROR_INVALID_VALUE);
                        System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                    }
                }
                break;
            case ("service_type_id"):
                while (true) {
                    System.out.printf(ENTER_DATA_FORMAT, "service_type_id", "", "(optional)");
                    String inputText = input.nextLine();
                    try {
                        Integer value = Integer.parseInt(inputText);
                        Service<ServiceTypeEntity> serviceTypeService = new ServiceTypeService();
                        ServiceTypeEntity serviceType = serviceTypeService.findById(value);
                        if (serviceType != null) {
                            entity.setServiceTypeByServiceTypeId(serviceType);
                            break;
                        } else {
                            System.out.println(ERROR_INVALID_VALUE);
                        }
                    } catch (IllegalArgumentException e) {
                        if (!inputText.equals("")) {
                            System.out.println(ERROR_INVALID_VALUE);
                            System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                        } else {
                            entity.setServiceTypeByServiceTypeId(null);
                            break;
                        }
                    }
                }
                break;
        }
        ServiceEntity oldEntity = super.update(id, entity);
        List<String> headerList = new LinkedList<>(COLUMNS_NAMES);
        List<List<String>> body = new LinkedList<>();
        body.add(entityToList(oldEntity));
        body.add(entityToList(entity));
        Formatter.formatTable(headerList, body);
        return entity;
    }

    @Override
    public ServiceEntity delete(Integer id) {
        ServiceEntity entity = super.delete(id);
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
    protected List<String> entityToList(ServiceEntity entity) {
        String id = entity.getId().toString();
        String date = entity.getDate().toString();
        String terminalId = entity.getTerminalByTerminalId().getId().toString();
        String workmanId = entity.getWorkmanByWorkmanId().getId().toString();
        String serviceTypeId = entity.getServiceTypeByServiceTypeId() == null ? "-" : entity.getServiceTypeByServiceTypeId().getId().toString();
        String durationInHours = entity.getDurationInHours().toString();
        String totalPriceUah = entity.getTotalPriceUah().toString();
        return new LinkedList<>(List.of(id, date, terminalId, workmanId, serviceTypeId, durationInHours, totalPriceUah));
    }
}
