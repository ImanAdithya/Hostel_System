package lk.ijse.hostel.dao;

import java.util.List;

public interface CrudDAO<T> extends SuperDAO{

    List<T> loadAll();
    String save(T t);
    String update(T t);
    String delete(T t);

}
