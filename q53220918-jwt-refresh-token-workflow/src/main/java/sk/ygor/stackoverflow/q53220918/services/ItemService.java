package sk.ygor.stackoverflow.q53220918.services;

import org.springframework.stereotype.Service;
import sk.ygor.stackoverflow.q53220918.domain.Item;
import sk.ygor.stackoverflow.q53220918.model.exception.ResourceForbiddenException;
import sk.ygor.stackoverflow.q53220918.model.request.ItemCreate;
import sk.ygor.stackoverflow.q53220918.repository.ItemRepository;
import sk.ygor.stackoverflow.q53220918.repository.UserRepository;

import java.util.Optional;

@Service
public class ItemService {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ItemService(UserRepository userRepository,
                       ItemRepository itemRepository) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public Item createItem(long userId, ItemCreate itemCreate) {
        return userRepository.findById(userId)
                .map(user -> {
                    Item entity = new Item(
                            itemCreate.getContent(),
                            itemCreate.getImpact(),
                            itemCreate.getEase(),
                            itemCreate.getConfidence(),
                            System.currentTimeMillis(),
                            user
                    );
                    return itemRepository.save(entity);
                })
                .orElseThrow(IllegalStateException::new);
    }

    public Optional<Item> deleteItem(long loggedUserId, long itemId) {
        return getItem(loggedUserId, itemId).map(item -> {
            itemRepository.delete(item);
            return item;
        });
    }

    public Optional<Item> updateItem(long loggedUserId, long itemId, ItemCreate itemUpdate) {
        return getItem(loggedUserId, itemId)
                .map(item -> {
                    item.setContent(itemUpdate.getContent());
                    item.setImpact(itemUpdate.getImpact());
                    item.setEase(itemUpdate.getEase());
                    item.setConfidence(itemUpdate.getConfidence());
                    return itemRepository.save(item);
                });
    }

    public Optional<Item> getItem(long loggedUserId, long itemId) {
        return itemRepository.findById(itemId).map(item -> {
            if (item.getUser().getId() == loggedUserId) {
                return item;
            } else {
                throw new ResourceForbiddenException();
            }
        });
    }

}
