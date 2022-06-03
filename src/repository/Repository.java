package repository;

import domain.GenericEntity;

import java.util.List;

/*
  @param <T>
 */
public interface Repository<T> {

    List<T> getAll(T param) throws Exception;

    Long add(T param) throws Exception;

    Long addWithBlob(T param) throws Exception;

    void edit(T param) throws Exception;

    void deactivate(T param) throws Exception;

    List<T> getAll() throws Exception;

    T get(T param) throws Exception;

    GenericEntity getByID(GenericEntity entity) throws Exception;

    List<GenericEntity> getByCondition(GenericEntity entity) throws Exception;

    List<GenericEntity> getBySpecificCondition(GenericEntity entity) throws Exception;
}
