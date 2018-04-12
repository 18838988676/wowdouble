package com.example.controller;

import com.example.dao.PersonRepository;
import com.example.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    /**
     * @param name
     * @param address
     * @param age
     * @return
     */
    @RequestMapping("/person/save")
    public Person save(String name, String address, Integer age) {
        Person p = personRepository.save(new Person(null, name, age, address));
        return p;
    }

    /**
     * @param address
     * @return
     */
    @RequestMapping("/person/q1")
    public List<Person> findByAddress(String address) {
        List<Person> persons = personRepository.findByAddress(address);
        return persons;
    }

    //  Iterable<T> findAll(Sort var1);

    //  Page<T> findAll(Pageable var1);

    /**
     * 分页 并且排序
     *
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/person/page")
    public Page<Person> findAll(int page, int size) {
        Page<Person> persons = personRepository.findAll(new PageRequest(page, size, new Sort(Sort.Direction.ASC, "age")));
        return persons;
    }


    //Page<T> findAll(Specification<T> var1, Pageable var2);

    /**
     * 自定义查询条件 分页过滤
     *
     * @param page
     * @param size
     * @param address
     * @return
     */
    @RequestMapping("/person/{address}")
    public Page<Person> findAll(int page, int size, @PathVariable("address") String address) {
        Specification specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("address"), address);
            }
        };
        Page<Person> persons = personRepository.findAll(specification, new PageRequest(page, size, new Sort(Sort.Direction.ASC, "age")));
        return persons;
    }


    @RequestMapping("/person/search")
    public Page<Person> findByAuto(int page,int size, Person person){
        Pageable pageable = new PageRequest(page,size,new Sort(Sort.Direction.ASC,"age"));
        Page<Person> persons = personRepository.findByAuto(person,pageable);
        return  persons;
    }
}
