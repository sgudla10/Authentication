package com.netgear.auth.dao;

import com.google.common.util.concurrent.ListenableFuture;

public interface GenericDao<T> {
    /**
     * Persist the entity annotated with achilles annotations with basic options.
     * Persist will override all entries in the database.
     * If entity field value is null, it will override the existing column associated with this
     * field as null.
     *
     * @param entity
     * @return
     **/
    public void persist(T entity);

    /**
     * Retrieve an entity using the primary key.
     *
     * @param type
     * @param key
     * @return T
     */

    public T find(Class<T> type, Object key);

    /**
     * Persist the entity annotated with achilles annotations with basic options (performs
     * Asynchronous write).
     * Returns Guava ListenableFuture future.
     *
     * @param entity
     * @return T
     */
    public ListenableFuture<T> persistAsync(T entity);

    /**
     * Async Updates
     * For merging data first find the entity using find and then call update for merging.
     * If want to update with out read first call findReference and then change data and call update
     * (This is the recommended pattern to update).
     * This update is done Async and returns guava ListenableFuture.
     *
     * @param entity
     * @return Boolean
     */
    public ListenableFuture<T> updateAsync(T entity);

    /**
     * Find result set asynchronously with the help of entity mapper (achilles support).
     *
     * @param entity
     * @param key
     * @return List<ListenableFuture<T>>
     */
    public ListenableFuture<T> findMultiAsync(Class<T> type, Object key);

    /**
     * Remove the entity from Cassandra
     *
     * @param entity
     * @return Boolean
     */
    public Boolean remove(T entity);
}
