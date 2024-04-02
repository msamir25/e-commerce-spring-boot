package com.codic.ecommerceproject.config;

import com.codic.ecommerceproject.entity.Country;
import com.codic.ecommerceproject.entity.Product;
import com.codic.ecommerceproject.entity.ProductCategory;
import com.codic.ecommerceproject.entity.State;
import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.HttpMethods;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired
    public MyDataRestConfig (EntityManager theEntityManger){
        entityManager = theEntityManger;
    }
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);


        HttpMethod[] theUnsupportedActions = {HttpMethod.DELETE , HttpMethod.POST , HttpMethod.PUT};

        //Disable HTTP methods for Product : PUT ,  POST , DELETE

        disableHttpMethods(Product.class ,config, theUnsupportedActions);


        //Disable HTTP methods for ProductCategory : PUT ,  POST , DELETE
        disableHttpMethods(ProductCategory.class ,config, theUnsupportedActions);

        disableHttpMethods(Country.class ,config, theUnsupportedActions);
        disableHttpMethods(State.class ,config, theUnsupportedActions);


        exposeId(config);

    }

    private static void disableHttpMethods(Class theClass ,RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
    }

    private void exposeId(RepositoryRestConfiguration config){
        //get all of entity Classes from entityManger
        Set<EntityType<?>>entities = entityManager.getMetamodel().getEntities();

        //create an array of entity Types

        List<Class> entityClasses = new ArrayList<>();

        for (EntityType tempEntityType : entities){
            entityClasses.add(tempEntityType.getJavaType());

            //expose the entites ids for the array of entity types

            Class[] domainTypes = entityClasses.toArray(new Class[0]);
            config.exposeIdsFor(domainTypes);

        }


    }
}
