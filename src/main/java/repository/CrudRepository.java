package repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    T add(T data);

    Optional<T> getById(Long id);

    List<T> getAllUsers();

    T updateById(T t);

    boolean deleteById(Long id);

}
