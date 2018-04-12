package com.example.service.impl;

import com.example.dao.PersonRepository;
import com.example.domain.Person;
import com.example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * CacheConfig 类级别的缓存设置,可以配置缓存名称,缓存key策略,缓存管理器
 */
@Service
@CacheConfig(cacheNames="people")
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository ;

    /**
     * 可以指定 condition  unless
     * @param person
     * @return
     */
    @Override
    @CachePut(value = "people",key = "#person.id")
    public Person save(Person person) {
        Person p = personRepository.save(person);
        return p;
    }

    /**
     * allEntries = true: 清空缓存book1里的所有值
     * beforeInvocation ;是否在方法执行前就清空缓存 默认false.
     * @param id
     */
    @Override
    @CacheEvict(value = "people",allEntries = false)
    public void remove(Long id) {
        personRepository.delete(id);
    }

    /**
     * sync 为了防止缓存击穿, 在多线程访问的时候,一个线程根据key 获取缓存值 不存在,其他线程等待.
     * 若不设置,可能很多线程都击穿到数据库.严重的引起缓存雪崩
     *
     * condition和unless 只满足特定条件才进行缓存：condition: 在执行方法前，condition的值为true，则缓存数据
     * unless ：在执行方法后，判断unless ，如果值为true，则不缓存数据
     * conditon和unless可以同时使用，则此时只缓存同时满足两者的记录
     *
     * @param person
     * @return
     */
    @Override
    @Cacheable(value = "people",key = "#person.id" ,sync=true,
          condition = "T(java.lang.Integer).parseInt(#person.id)>3")
    public Person findOne(Person person) {
        return personRepository.findOne(person.getId());
    }
}
