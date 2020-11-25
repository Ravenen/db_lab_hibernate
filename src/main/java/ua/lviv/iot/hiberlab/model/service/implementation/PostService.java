package ua.lviv.iot.hiberlab.model.service.implementation;

import ua.lviv.iot.hiberlab.model.dataaccess.implementation.PostDataAccess;
import ua.lviv.iot.hiberlab.model.entity.PostEntity;

public class PostService extends AbstractService<PostEntity> {
  public PostService() {
    super(new PostDataAccess());
  }
}
