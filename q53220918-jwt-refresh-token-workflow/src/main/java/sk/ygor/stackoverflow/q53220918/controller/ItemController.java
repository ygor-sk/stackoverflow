package sk.ygor.stackoverflow.q53220918.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sk.ygor.stackoverflow.q53220918.domain.Item;
import sk.ygor.stackoverflow.q53220918.model.exception.ItemNotFoundException;
import sk.ygor.stackoverflow.q53220918.model.request.ItemCreate;
import sk.ygor.stackoverflow.q53220918.security.SecurityService;
import sk.ygor.stackoverflow.q53220918.services.ItemService;

import javax.validation.Valid;

@RestController
public class ItemController {

    private static final String PATH_POST_CREATE = "/item";
    private static final String PATH_DELETE_DELETE = "/item/{id}";
    private static final String PATH_GET_SINGLE = "/item/{id}";
    private static final String PATH_PUT_UPDATE = "/item/{id}";

    private final ItemService itemService;
    private final SecurityService securityService;

    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @RequestMapping(path = PATH_POST_CREATE, method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Item itemPostCreate(@Valid @RequestBody ItemCreate itemCreate) {
        return itemService.createItem(securityService.getLoggedUserId(), itemCreate);
    }

    @RequestMapping(path = PATH_DELETE_DELETE, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void itemDeleteDelete(@PathVariable(name = "id") long itemId) {
        itemService.deleteItem(securityService.getLoggedUserId(), itemId)
                .orElseThrow(ItemNotFoundException::new);
    }

    @RequestMapping(path = PATH_GET_SINGLE, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Item itemGetSingle(@PathVariable(name = "id") long itemId) {
        return itemService.getItem(securityService.getLoggedUserId(), itemId)
                .orElseThrow(ItemNotFoundException::new);
    }

    @RequestMapping(path = PATH_PUT_UPDATE, method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Item itemPutUpdate(@PathVariable(name = "id") long itemId,
                                            @Valid @RequestBody ItemCreate itemUpdate) {
        return itemService.updateItem(securityService.getLoggedUserId(), itemId, itemUpdate)
                .orElseThrow(ItemNotFoundException::new);
    }

}
