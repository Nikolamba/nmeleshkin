package ru.job4j.nonblocking;

import javax.management.OperationsException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class NonBlockingCash {

    private final Map<Integer, Model> map = new ConcurrentHashMap<>();

    public void add(Model model) {
        this.map.put(model.getId(), model);
    }

    public void update(Model newModel) {
        this.map.computeIfPresent(newModel.getId(), (integer, model) -> {
            Model result;
            if (newModel.getVersion() == map.get(newModel.getId()).getVersion()) {
                newModel.setVersion(newModel.getVersion() + 1);
                result = newModel;
            } else {
                throw new OptimisticException("Несовпадение версий");
            }
            return result;
        });
    }

    public void delete(Model newModel) {
        this.map.remove(newModel.getId());
    }

    public Model getModelById(int id) {
        return this.map.get(id);
    }
}


