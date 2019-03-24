package com.codecool.series.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Series {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Singular
    @OneToMany(mappedBy = "series", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @EqualsAndHashCode.Exclude
    public Set<Season> seasons;

    @ElementCollection
    private List<String> cast;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Transient
    private Integer numberOfEpisodes;

    @Transient
    private Integer numberOfSeasons;

    public void calculateNumberOfSeasons() {
        numberOfSeasons = seasons.size();
    }

    public void calculateNumberOfEpisodes() {

        numberOfEpisodes = seasons.stream()
                .mapToInt(season -> {
                    season.calculateNumberOfEpisodes();
                    return season.getNumberOfEpisodes();
                })
                .sum();

    }

}
