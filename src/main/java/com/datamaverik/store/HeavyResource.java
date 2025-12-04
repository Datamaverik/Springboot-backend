package com.datamaverik.store;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//  Lazy annotation allows to initialize it when needed
//  Lazy annotation can be used in AppConfig also for Beans
@Component
@Lazy
public class HeavyResource {
    HeavyResource() {
        System.out.println("Heavy Resource created");
    }
}
