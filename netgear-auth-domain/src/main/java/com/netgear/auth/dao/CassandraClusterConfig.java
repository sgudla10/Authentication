package com.netgear.auth.dao;

import com.datastax.driver.core.ProtocolOptions.Compression;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.DowngradingConsistencyRetryPolicy;
import com.datastax.driver.core.policies.RetryPolicy;

/**
 * 
 * @author sgudla
 *
 */
public class CassandraClusterConfig {

    private String clusterName;
    private String clusterIpAddressList;
    private String cfsClusterIpAddressList;
    private int port;
    private int cfsPort;
    private String dataCentre;

    private int initConnectionsPerHost = 2; //There is bug is datastax code, no sliding window supported for min-max connections
    private int maxHostsPerConnection = 2;

    private int initRemoteConnectionsPerHost = 1; //There is bug is datastax code, no sliding window supported for min-max connections
    private int maxRemoteHostsPerConnection = 1;
    private int usedHostsPerRemoteDc = 1;
    private boolean allowRemoteDCsForLocalConsistencyLevel = true;

    private int simultaneousRequestsPerConnection = 1500;

    private int newConnectionThreshold = 800;

    private Boolean shuffleReplicasAmongReplicationNodes = true; //Takes a hit on caching if set to true
    private Boolean keepTCPConnectionAlive = true;

    private Long reconnectPeriod = 5L;

    private String username;
    private String password;
    private Boolean downgradableConsistency = false;
    private String keySpace;
    private String readConsistency;
    private String writeConsistency;
    private String compression = Compression.LZ4.name();
    private String entityPackages;
    private RetryPolicy retryPolicy = getDowngradableConsistency() == true ? DowngradingConsistencyRetryPolicy.INSTANCE : DefaultRetryPolicy.INSTANCE;

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterIpAddressList() {
        return clusterIpAddressList;
    }

    public void setClusterIpAddressList(String clusterIpAddressList) {
        this.clusterIpAddressList = clusterIpAddressList;
    }

    public String getCfsClusterIpAddressList() {
        return cfsClusterIpAddressList;
    }

    public void setCfsClusterIpAddressList(String cfsClusterIpAddressList) {
        this.cfsClusterIpAddressList = cfsClusterIpAddressList;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getCfsPort() {
        return cfsPort;
    }

    public void setCfsPort(int cfsPort) {
        this.cfsPort = cfsPort;
    }

    public String getDataCentre() {
        return dataCentre;
    }

    public void setDataCentre(String dataCentre) {
        this.dataCentre = dataCentre;
    }

    public int getInitConnectionsPerHost() {
        return initConnectionsPerHost;
    }

    public void setInitConnectionsPerHost(int initConnectionsPerHost) {
        this.initConnectionsPerHost = initConnectionsPerHost;
    }

    public int getMaxHostsPerConnection() {
        return maxHostsPerConnection;
    }

    public void setMaxHostsPerConnection(int maxHostsPerConnection) {
        this.maxHostsPerConnection = maxHostsPerConnection;
    }

    public int getInitRemoteConnectionsPerHost() {
        return initRemoteConnectionsPerHost;
    }

    public void setInitRemoteConnectionsPerHost(int initRemoteConnectionsPerHost) {
        this.initRemoteConnectionsPerHost = initRemoteConnectionsPerHost;
    }

    public int getMaxRemoteHostsPerConnection() {
        return maxRemoteHostsPerConnection;
    }

    public void setMaxRemoteHostsPerConnection(int maxRemoteHostsPerConnection) {
        this.maxRemoteHostsPerConnection = maxRemoteHostsPerConnection;
    }

    public int getUsedHostsPerRemoteDc() {
        return usedHostsPerRemoteDc;
    }

    public void setUsedHostsPerRemoteDc(int usedHostsPerRemoteDc) {
        this.usedHostsPerRemoteDc = usedHostsPerRemoteDc;
    }

    public boolean isAllowRemoteDCsForLocalConsistencyLevel() {
        return allowRemoteDCsForLocalConsistencyLevel;
    }

    public void setAllowRemoteDCsForLocalConsistencyLevel(boolean allowRemoteDCsForLocalConsistencyLevel) {
        this.allowRemoteDCsForLocalConsistencyLevel = allowRemoteDCsForLocalConsistencyLevel;
    }

    public int getSimultaneousRequestsPerConnection() {
        return simultaneousRequestsPerConnection;
    }

    public void setSimultaneousRequestsPerConnection(int simultaneousRequestsPerConnection) {
        this.simultaneousRequestsPerConnection = simultaneousRequestsPerConnection;
    }

    public int getNewConnectionThreshold() {
        return newConnectionThreshold;
    }

    public void setNewConnectionThreshold(int newConnectionThreshold) {
        this.newConnectionThreshold = newConnectionThreshold;
    }

    public Boolean getShuffleReplicasAmongReplicationNodes() {
        return shuffleReplicasAmongReplicationNodes;
    }

    public void setShuffleReplicasAmongReplicationNodes(Boolean shuffleReplicasAmongReplicationNodes) {
        this.shuffleReplicasAmongReplicationNodes = shuffleReplicasAmongReplicationNodes;
    }

    public Boolean getKeepTCPConnectionAlive() {
        return keepTCPConnectionAlive;
    }

    public void setKeepTCPConnectionAlive(Boolean keepTCPConnectionAlive) {
        this.keepTCPConnectionAlive = keepTCPConnectionAlive;
    }

    public Long getReconnectPeriod() {
        return reconnectPeriod;
    }

    public void setReconnectPeriod(Long reconnectPeriod) {
        this.reconnectPeriod = reconnectPeriod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getDowngradableConsistency() {
        return downgradableConsistency;
    }

    public void setDowngradableConsistency(Boolean downgradableConsistency) {
        this.downgradableConsistency = downgradableConsistency;
    }

    public String getKeySpace() {
        return keySpace;
    }

    public void setKeySpace(String keySpace) {
        this.keySpace = keySpace;
    }

    public String getReadConsistency() {
        return readConsistency;
    }

    public void setReadConsistency(String readConsistency) {
        this.readConsistency = readConsistency;
    }

    public String getWriteConsistency() {
        return writeConsistency;
    }

    public void setWriteConsistency(String writeConsistency) {
        this.writeConsistency = writeConsistency;
    }

    public String getCompression() {
        return compression;
    }

    public void setCompression(String compression) {
        this.compression = compression;
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public String getEntityPackages() {
        return entityPackages;
    }

    public void setEntityPackages(String entityPackages) {
        this.entityPackages = entityPackages;
    }

}
