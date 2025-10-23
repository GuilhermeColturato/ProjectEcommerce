package com.xco.spactshop.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "users")
@Data
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password; // INSEGURO: Armazenar em texto plano

    private String type; // "cliente" | "admin"

    public User() {
    }

    public User(String name, String email, String password, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.type = type;
    }
}

