package ua.lviv.iot.hiberlab.controller.implementation;

import ua.lviv.iot.hiberlab.model.entity.PostEntity;
import ua.lviv.iot.hiberlab.model.service.Service;
import ua.lviv.iot.hiberlab.model.service.implementation.PostService;

public class PostController extends AbstractController<PostEntity> {
    public PostController() {
        super(new PostService());
    }
}
