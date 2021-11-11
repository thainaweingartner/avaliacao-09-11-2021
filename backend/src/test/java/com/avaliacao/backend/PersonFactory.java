package com.avaliacao.backend;

import com.avaliacao.backend.entities.Person;

public class PersonFactory {

    public static Person buildDefault(){
        return Person.builder()
                .id(1L)
                .name("John Don")
                .phone("9899929348")
                .email("john.don@gmail.com")
                .build();
    }

    public static Person buildPersonUpdateInfo(){
        return Person.builder()
                .name("John Donh")
                .phone("9899929345")
                .email("john.donh@gmail.com")
                .build();
    }

    public static Person buildPersonUpdated(){
        return Person.builder()
                .id(1L)
                .name("John Donh")
                .phone("9899929345")
                .email("john.donh@gmail.com")
                .build();
    }


}
