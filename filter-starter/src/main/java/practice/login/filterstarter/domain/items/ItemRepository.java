package practice.login.filterstarter.domain.items;

import practice.login.filterstarter.domain.repositories.CommonRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends CommonRepository<Item, Long> {
    List<Item> findAll();

    Optional<Item> findById(Long aLong);

    <S extends Item> S save(S entity);

    <S extends Item> void delete(S entity);
}
