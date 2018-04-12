package com.example.dao.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.SingularAttribute;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BaseSpecs<T> {

   public static <T> Specification<T> auto(EntityManager entityManager , T entity ){
        Class<T> clazz = (Class <T> )entity.getClass();
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                EntityType<T> entityType = entityManager.getMetamodel().entity(clazz);
                Set<Attribute<T, ?>> attributes =  entityType.getDeclaredAttributes();
                for (Attribute<T, ?> attr :attributes){
                   Object attrValue =  getValue(entity,attr);
                   if(attrValue!=null){
                       if(attr.getJavaType()==String.class){
                           SingularAttribute singularAttribute = attributes(entityType,attr.getName(),String.class);
                           Expression expression = root.get(singularAttribute);
                           Predicate predicate = null ;
                           if(!StringUtils.isEmpty(attrValue)){
                               predicate =  criteriaBuilder.like(expression,pattern((String)attrValue));
                           }else{
                               predicate =  criteriaBuilder.equal(expression,(String)attrValue);
                           }
                           predicateList.add(predicate);
                       }
                   }
                }
                return  predicateList.isEmpty()?criteriaBuilder.conjunction():toArray(predicateList,Predicate.class);
            }

        };
    }

    private static  <T> Object getValue(T e,Attribute<T,?> attr){
        return ReflectionUtils.getField((Field)attr.getJavaMember(),e);
    }

    private static <E,T>SingularAttribute<T, E> attributes(EntityType<T> entityType , String fileName , Class<E> fieldClass){
        return entityType.getDeclaredSingularAttribute(fileName,fieldClass);
    }

    private static String pattern(String string){
        return "%"+string+"%";
    }

    /**
     * 把多个条件合并起来
     * @param predicateList
     * @param clazz
     * @return
     */
    private static Predicate toArray( List<Predicate> predicateList , Class<Predicate> clazz){
        //TODO
        for(Predicate predicate :predicateList){
            predicate.getExpressions();
            Predicate.BooleanOperator p =   predicate.getOperator();
            System.out.println(p);
        }
        return null;
    }
}
