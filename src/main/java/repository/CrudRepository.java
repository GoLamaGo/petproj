package repository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {

    Optional<T> getById(Long id) throws IOException;

    List<T> getAllUsers() throws IOException;

    boolean deleteById(Long id);

    T updateById(Long id);

    T add(T data) throws IOException;

}
