package com.codecool.series.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Season {

    @Id
    @GeneratedValue
    private Long id;

    private Integer seasonNumber;

    @ManyToOne
    private Series series;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "season", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Episode> episodes;

    @Transient
    private  Integer numberOfEpisodes;

    public void calculateNumberOfEpisodes() {
        numberOfEpisodes = episodes.size();
    }

}
