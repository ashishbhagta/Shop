package com.DCMetal.Shop.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank
    @Size(min = 5, message = "Street name should be atleast 5 character")
    private String street;

    @NotBlank
    @Size(min = 5, message = "Building name should be atleast 5 character")
    private String buildingName;

    @NotBlank
    @Size(min = 4, message = "City name should be atleast 4 character")
    private String city;

    @NotBlank
    @Size(min = 2, message = "State name should be atleast 2 character")
    private String state;

    @NotBlank
    @Size(min = 2, message = "Country name should be atleast 2 character")
    @Value("India")
    private String country;

    @NotBlank
    @Size(min = 5, message = "Pincode name should be atleast 5 character")
    private String pincode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    public Address(Long addressId, String street, String buildingName, String city, String state, String country, String pincode) {
        this.addressId = addressId;
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
        this.pincode = pincode;
    }
}
