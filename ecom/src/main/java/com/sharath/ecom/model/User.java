package com.sharath.ecom.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String password; // store hashed password
    private String phoneNumber;
    private String street;
    private String houseNumber;
    private String postcode;
    private String location;
    private String gender;
    private String profileImage; // URL or filename

}
