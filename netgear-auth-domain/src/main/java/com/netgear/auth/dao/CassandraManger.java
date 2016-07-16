package com.netgear.auth.dao;

import info.archinnov.achilles.persistence.AsyncManager;
import info.archinnov.achilles.persistence.PersistenceManager;
import info.archinnov.achilles.persistence.PersistenceManagerFactory;
import info.archinnov.achilles.type.ConsistencyLevel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

@Component
public class CassandraManger {

    private static final Logger LOG = LoggerFactory.getLogger(CassandraManger.class);

    private PersistenceManager persistenceManager;
    private AsyncManager asyncPersistenceManager;

    private CassandraClusterConfig config;

    public CassandraManger(CassandraClusterConfig config) {
        init(config);
    }

    void init(CassandraClusterConfig config) {
        try {

            Cluster cluster = Cluster.builder().addContactPoints(config.getClusterIpAddressList().split(",")).withClusterName(config.getClusterName()).withPort(config.getPort())
            /* .withCompression(ProtocolOptions.Compression.valueOf(config.getCompression())) */
            /*
             * .withLoadBalancingPolicy(
             * new TokenAwarePolicy(new
             * DCAwareRoundRobinPolicy(config.getDataCentre(),
             * config.getUsedHostsPerRemoteDc(),
             * config.isAllowRemoteDCsForLocalConsistencyLevel()),
             * config.getShuffleReplicasAmongReplicationNodes())).withReconnectionPolicy
             * (new ConstantReconnectionPolicy(config.getReconnectPeriod()))
             */
            .withCredentials(config.getUsername(), config.getPassword()).build();

            Session session = cluster.newSession();
            final PersistenceManagerFactory persistenceManagerFactory =
                    PersistenceManagerFactory.PersistenceManagerFactoryBuilder.builder(cluster).withNativeSession(session).withKeyspaceName(config.getKeySpace())
                            .withDefaultReadConsistency(ConsistencyLevel.valueOf(config.getReadConsistency())).withDefaultWriteConsistency(ConsistencyLevel.valueOf(config.getWriteConsistency()))
                            .withEntityPackages(config.getEntityPackages()).build();
            persistenceManager = persistenceManagerFactory.createPersistenceManager();
            asyncPersistenceManager = persistenceManagerFactory.createAsyncManager();

        } catch (Exception exception) {
            LOG.error("Exception Creating Cassandra persistence Manager", exception);
            throw new RuntimeException("Exception creating Cassandra persistence Manager", exception);

        }
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public CassandraClusterConfig getConfig() {
        return config;
    }

    public void setConfig(CassandraClusterConfig config) {
        this.config = config;
    }

    public AsyncManager getAsyncPersistenceManager() {
        return asyncPersistenceManager;
    }

    public void setAsyncPersistenceManager(AsyncManager asyncPersistenceManager) {
        this.asyncPersistenceManager = asyncPersistenceManager;
    }

}
