package repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<T> getById(Long id) throws IOException;

    List<T> getAllUsers() throws IOException;

    boolean deleteById(Long id) throws IOException;

    T updateById(T t) throws IOException;

    List<T> add(List<T> data) throws IOException;

}
