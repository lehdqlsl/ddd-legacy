package kitchenpos;

import kitchenpos.domain.Menu;
import kitchenpos.domain.MenuRepository;

import java.util.*;
import java.util.stream.Collectors;

class InMemoryMenuRepository implements MenuRepository {
    private final Map<UUID, Menu> menus = new HashMap<>();

    @Override
    public List<Menu> findAllByIdIn(List<UUID> ids) {
        return null;
    }

    @Override
    public List<Menu> findAllByProductId(UUID productId) {
        return menus.values()
                .stream()
                .filter(menu -> menu.getMenuProducts().stream().anyMatch(menuProduct -> menuProduct.getProduct().getId().equals(productId)))
                .collect(Collectors.toList());
    }

    @Override
    public Menu save(Menu menu) {
        if (Objects.isNull(menu.getId())) {
            menu.setId(UUID.randomUUID());
            menus.put(menu.getId(), menu);
        } else {
            menus.put(menu.getId(), menu);
        }
        return menu;
    }

    @Override
    public Optional<Menu> findById(UUID menuId) {
        return Optional.ofNullable(menus.get(menuId));
    }

    @Override
    public List<Menu> findAll() {
        return new ArrayList<>(menus.values());
    }
}
