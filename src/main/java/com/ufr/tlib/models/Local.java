package com.ufr.tlib.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Local {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(length = 50)
    @NotBlank(message = "This field is required")
    private String name;

    @Column(length = 100)
    @NotBlank(message = "This field is required")
    private String address;

    @Column(length = 200)
    @NotBlank(message = "This field is required")
    private String description;

    @Column(length = 15)
    @NotBlank(message = "This field is required")
    private String phoneNumber;

    @Column(length = 50)
    @NotBlank(message = "This field is required")
    @Email
    private String email;

    private boolean enabled;

    @Enumerated(EnumType.STRING)
    private Service service;

    @OneToMany(mappedBy = "local", fetch = FetchType.LAZY)
    private List<Artisan> artisans;

    @OneToMany(mappedBy = "local")
    private List<Prestation> prestations;
    @ManyToOne
    private User manager;

    @OneToMany(mappedBy = "local", fetch = FetchType.LAZY)
    private List<Image> images;
}
