package com.ufr.tlib.models;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;


    @Column(unique = true, length = 20)
    @NotBlank(message = "This field is required")
    @Size(min=5, max=20, message = "Length should be between 5 and 20")
    private String username; // Entre 5 et 20 caractères, doit être unique entre tous les utilisateurs


    @Column(length = 20)
    @NotBlank(message = "This field is required")
    private String password; // Entre 8 et 24 caractères, au moins une minuscule, une majuscule, un chiffre

    @Column
    @Email(message = "Invalide email")
    private String email; // Adresse email valide (format X@Y.Z)

    @Column
    @NotBlank(message = "This field is required")
    private String lastname;

    @Column
    @NotBlank(message = "This field is required")
    private String firstname;

    @Column
    @NotBlank(message = "This field is required")
    private String phone;

    private boolean enabled = false;

    @OneToMany(mappedBy = "manager")
    private List<Local> managedLocals;

    @OneToMany(mappedBy = "client")
    private List<RDV> RDVs;

    @ManyToOne
    private Role role;

}

