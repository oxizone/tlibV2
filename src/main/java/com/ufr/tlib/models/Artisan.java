package com.ufr.tlib.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Artisan {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String firstName;
    private String lastName;
    private String avatar;
    private boolean deleted = false;
    @ManyToOne
    private Local local;

    @OneToMany(mappedBy = "artisan", fetch = FetchType.LAZY)
    private List<Absence> absences;

    @OneToMany(mappedBy = "artisan", fetch = FetchType.LAZY)
    private List<RDV> RDVs;

}
