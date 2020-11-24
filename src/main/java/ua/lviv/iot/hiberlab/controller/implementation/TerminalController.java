package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.TerminalEntity;
import ua.lviv.iot.hiberlab.model.service.implementation.TerminalService;

public class TerminalController extends AbstractController<TerminalEntity> {
  public TerminalController() {
    super(new TerminalService());
  }
}
