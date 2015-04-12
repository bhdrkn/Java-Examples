package com.bahadirakin.hazelcast;

import com.bahadirakin.hazelcast.domain.User;
import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.MapStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringHazelcastApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringHazelcastApplication.class, args);
    }

    @Bean
    @Autowired
    public HazelcastInstance getHazelcastInstance(MapStore<String, User> userMapStore) {
        final Config config = new Config();

        final NetworkConfig networkConfig = new NetworkConfig();
        networkConfig.setPort(5701);
        networkConfig.setPortAutoIncrement(true);
        config.setNetworkConfig(networkConfig);

        final JoinConfig joinConfig = new JoinConfig();
        networkConfig.setJoin(joinConfig);

        final MulticastConfig multicastConfig = new MulticastConfig();
        multicastConfig.setEnabled(false);
        joinConfig.setMulticastConfig(multicastConfig);

        final TcpIpConfig tcpIpConfig = new TcpIpConfig();
        tcpIpConfig.setEnabled(false);
        joinConfig.setTcpIpConfig(tcpIpConfig);

        final AwsConfig awsConfig = new AwsConfig();
        awsConfig.setEnabled(false);
        joinConfig.setAwsConfig(awsConfig);

        final SSLConfig sslConfig = new SSLConfig();
        sslConfig.setEnabled(false);
        networkConfig.setSSLConfig(sslConfig);

        final MapConfig mapConfig = new MapConfig();
        mapConfig.setName("userMap");

        final MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setEnabled(true);
        mapStoreConfig.setWriteDelaySeconds(60);
        mapStoreConfig.setInitialLoadMode(MapStoreConfig.InitialLoadMode.LAZY);
        mapStoreConfig.setImplementation(userMapStore);
        mapConfig.setMapStoreConfig(mapStoreConfig);
        config.addMapConfig(mapConfig);


        return Hazelcast.newHazelcastInstance(config);
    }
}
