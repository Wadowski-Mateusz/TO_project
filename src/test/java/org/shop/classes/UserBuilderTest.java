package org.shop.classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UserBuilderTest {

    @Test
    void build() {

        User.getBuilder().setName("test_name")
                .setSurname("test_surname")
                .setEmail("test@email.test")
                .setPassword("test_password")
                .setPhoneNUmber("000111000")
                .setIsAdmin(false)
                .build().convertToRecord();
        assert true;
    }
}