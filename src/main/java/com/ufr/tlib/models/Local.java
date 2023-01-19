package com.ufr.tlib.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Date;
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

    @Enumerated(EnumType.STRING)
    private Etat etat;

    @CreationTimestamp
    private Date createdAt;

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

    @OneToOne(mappedBy = "local")
    @JoinColumn(name = "address_id")
    private Address adresse;
}
