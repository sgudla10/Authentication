package com.netgear.auth.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.netgear.auth.entity.UserDO;

@ContextConfiguration(locations = {"classpath:domain-context.xml"})
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao<UserDO> userDao;

    @Autowired
    private CassandraManger manager;

    @Test
    public void testInsert() {
        UserDO user = new UserDO();
        user.setEmail("swati.malla@gmail.com");
        user.setPassword("11");
        user.setFirstname("swati");
        user.setLastname("gudla");
        user.setName("swati gudla");
        userDao.persist(user);
        UserDO fromDataStore = userDao.find(UserDO.class, "swati.malla@gmail.com");
        Assert.assertNotNull(fromDataStore);
        Assert.assertEquals(fromDataStore.getEmail(), "swati.malla@gmail.com");
        Assert.assertEquals(fromDataStore.getPassword(), "11");
        Assert.assertEquals(fromDataStore.getName(), "swati gudla");
        Assert.assertEquals(fromDataStore.getFirstname(), "swati");
        Assert.assertEquals(fromDataStore.getLastname(), "gudla");
        userDao.remove(fromDataStore);

    }

    @Test
    public void testUpdate() {
        UserDO user = new UserDO();
        user.setEmail("swati.malla@gmail.com");
        user.setPassword("11");
        user.setFirstname("swati");
        user.setLastname("gudla");
        user.setName("swati gudla");
        userDao.persist(user);
        UserDO fromDataStore = userDao.find(UserDO.class, "swati.malla@gmail.com");
        Assert.assertNotNull(fromDataStore);
        Assert.assertEquals(fromDataStore.getEmail(), "swati.malla@gmail.com");
        Assert.assertEquals(fromDataStore.getPassword(), "11");
        Assert.assertEquals(fromDataStore.getName(), "swati gudla");
        Assert.assertEquals(fromDataStore.getFirstname(), "swati");
        Assert.assertEquals(fromDataStore.getLastname(), "gudla");
        user.setName("swati malla");
        userDao.persist(user);
        fromDataStore = userDao.find(UserDO.class, "swati.malla@gmail.com");
        Assert.assertNotNull(fromDataStore);
        Assert.assertEquals(fromDataStore.getEmail(), "swati.malla@gmail.com");
        Assert.assertEquals(fromDataStore.getPassword(), "11");
        Assert.assertEquals(fromDataStore.getName(), "swati malla");
        Assert.assertEquals(fromDataStore.getFirstname(), "swati");
        Assert.assertEquals(fromDataStore.getLastname(), "gudla");
        userDao.remove(fromDataStore);

    }
}
