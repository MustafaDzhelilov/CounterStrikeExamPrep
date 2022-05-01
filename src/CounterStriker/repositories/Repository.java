package CounterStriker.repositories;

import java.util.Collection;
import java.util.List;

public interface Repository<T> {
    Collection<T> getModels();

    void add(T model);

    boolean remove(T model);

    T findByName(String name);
}
