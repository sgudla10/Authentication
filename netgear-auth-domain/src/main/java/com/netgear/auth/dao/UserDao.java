package com.netgear.auth.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.util.concurrent.ListenableFuture;

public class UserDao<UserDO> implements GenericDao<UserDO> {
    private static final Logger LOG = LoggerFactory.getLogger(CassandraManger.class);

    @Autowired
    private CassandraManger manager;

    public void persist(UserDO entity) {

        manager.getPersistenceManager().insert(entity);
    }

    public UserDO find(Class<UserDO> type, Object key) {
        return manager.getPersistenceManager().find(type, key);
    }

    public ListenableFuture<UserDO> persistAsync(UserDO entity) {
        return manager.getAsyncPersistenceManager().insert(entity);

    }

    public ListenableFuture<UserDO> updateAsync(UserDO entity) {
        return manager.getAsyncPersistenceManager().update(entity);
    }

    public ListenableFuture<UserDO> findMultiAsync(Class<UserDO> type, Object key) {
        return manager.getAsyncPersistenceManager().find(type, key);
    }

    public Boolean remove(UserDO entity) {
        manager.getPersistenceManager().delete(entity);
        return Boolean.TRUE;
    }
}
