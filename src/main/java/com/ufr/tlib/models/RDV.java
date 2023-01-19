package com.ufr.tlib.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class RDV {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    @ManyToOne
    private User client;

    @ManyToOne
    private Artisan artisan;

    @ManyToOne
    private Prestation prestation;




}
