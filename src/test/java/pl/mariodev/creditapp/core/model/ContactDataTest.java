package pl.mariodev.creditapp.core.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactDataTest {

    @Test
    @DisplayName("Should set Optional.empty correspondence, when homeAddress is the same")
    public void test1(){
        //given & when
        ContactData contactData = new ContactData.Builder()
                .create()
                .withHomeAddress(new Address("Wrocławska", "Poznań", "51-500", "Wielkopolska","20"))
                .withCorrespondenceAddress(new Address("Wrocławska", "Poznań", "51-500", "Wielkopolska","20"))
                .build();
        //then
        assertTrue(contactData.getCorrespondenceAddress().isEmpty());
    }

    @Test
    @DisplayName("Should set Optional.of correspondence, when homeAddress is NOT the same")
    public void test2(){
        //given
        Address homeAddress = new Address("Gejowksa", "Kraków", "51-500", "Wielkopolska", "20");
        Address correspondenceAddress = new Address("Wrocławska", "Poznań", "51-500", "Wielkopolska", "20");

        //when
        ContactData contactData = new ContactData.Builder()
                .create()
                .withHomeAddress(homeAddress)
                .withCorrespondenceAddress(correspondenceAddress)
                .build();
        //then
        assertTrue(contactData.getCorrespondenceAddress().isPresent());
        assertEquals(correspondenceAddress, contactData.getCorrespondenceAddress().get());
    }
}