package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.controller.Controller;
import ua.lviv.iot.hiberlab.model.service.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

public abstract class AbstractController<T> implements Controller<T> {

    protected static final String ERROR_NO_MATCHES_FOUND = "[Error] No matches found";
    protected static final String ERROR_INVALID_VALUE = "[Error] Entered invalid value";
    protected static final String ERROR_MESSAGE_FORMAT = "[Error] Message: %s %n";

    protected static final String ENTER_DATA_FORMAT = "Enter %s %s: %s %n";
    protected static final String ENTER_COLUMN_NAME = "Choose column name to edit:";

    private final Service<T> service;

    public AbstractController(Service<T> service) {
        this.service = service;
    }

    @Override
    public List<T> findAll() {
        return service.findAll();
    }

    @Override
    public T findById(Integer id) {
        return service.findById(id);
    }

    @Override
    public T create(T entity) {
        return service.create(entity);
    }

    @Override
    public T update(Integer id, T entity) {
        return service.update(id, entity);
    }

    @Override
    public T delete(Integer id) {
        return service.delete(id);
    }

    protected List<String> entityToList(T entity) {
        return null;
    }

    @SuppressWarnings("unchecked")
    protected <R> void enterValueForColumn(T entity, BiConsumer<T, R> setter, String columnName, Class<R> columnType, boolean optional, int maxLength) {
        String limitation = "";
        Scanner input = new Scanner(System.in, "UTF-8");
        if (columnType == Integer.class) {
            limitation = "(max value - " + Integer.MAX_VALUE + ")";
        } else if (columnType == Date.class) {
            limitation = "(format yyyy-mm-dd)";
        } else if (columnType == BigDecimal.class) {
            limitation = "(format xxx.xx)";
        } else if (columnType == String.class) {
            limitation = "(max length - " + maxLength + ")";
        }
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, columnName, limitation, optional ? "(optional)" : "");
            String inputText = input.nextLine();
            try {
                if (columnType == Integer.class) {
                    Integer value = Integer.parseInt(inputText);
                    setter.accept(entity, (R) value);
                    break;
                } else if (columnType == String.class) {
                    if (inputText.equals("")) {
                        if (optional) {
                            setter.accept(entity, null);
                            break;
                        } else {
                            System.out.println(ERROR_INVALID_VALUE);
                        }
                    } else {
                        if (inputText.length() <= maxLength) {
                            setter.accept(entity, (R) inputText);
                            break;
                        } else {
                            System.out.println(ERROR_INVALID_VALUE);
                        }
                    }
                } else if (columnType == Date.class) {
                    Date date = Date.valueOf(inputText);
                    setter.accept(entity, (R) date);
                    break;
                } else if (columnType == BigDecimal.class) {
                    BigDecimal value = new BigDecimal(inputText);
                    if (value.compareTo(BigDecimal.valueOf(0)) >= 0) {
                        setter.accept(entity, (R) value);
                        break;
                    } else {
                        System.out.println(ERROR_INVALID_VALUE);
                    }
                }
            } catch (IllegalArgumentException e) {
                if (optional && inputText.equals("")) {
                    break;
                }
                System.out.println(ERROR_INVALID_VALUE);
                System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
            }
        }
    }

    protected <R> void enterEntityValueForColumn(T entity, BiConsumer<T, R> setter, String columnName, Service<R> service, boolean optional) {
        Scanner input = new Scanner(System.in, "UTF-8");
        while (true) {
            System.out.printf(ENTER_DATA_FORMAT, columnName, "", optional ? "(optional)" : "");
            String inputText = input.nextLine();
            try {
                Integer value = Integer.parseInt(inputText);
                R entityColumn = service.findById(value);
                if (entityColumn != null) {
                    setter.accept(entity, entityColumn);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                }
            } catch (IllegalArgumentException e) {
                if (inputText.equals("") && optional) {
                    setter.accept(entity, null);
                    break;
                } else {
                    System.out.println(ERROR_INVALID_VALUE);
                    System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
                }
            }
        }
    }
}
