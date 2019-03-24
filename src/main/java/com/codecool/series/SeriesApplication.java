package com.codecool.series;

import com.codecool.series.entity.Episode;
import com.codecool.series.entity.Genre;
import com.codecool.series.entity.Season;
import com.codecool.series.entity.Series;
import com.codecool.series.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SeriesApplication {

    @Autowired
    private SeriesRepository seriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SeriesApplication.class, args);
    }

    @Bean
    @Profile("production")
    public CommandLineRunner init() {
        return args -> {

            Episode episode0 = Episode.builder()
                    .title("Pilot")
                    .length(49)
                    .releaseDate(LocalDate.of(1988, 12, 29))
                    .episodeNumber(1)
                    .synopsis("After getting the sack from the music hall, Alf Stokes and his daughter Ivy enter service below stairs at the Meldrum house - but Alf is shocked to see a familiar face.")
                    .build();

            Episode episode1 = Episode.builder()
                    .title("The Phantom Signwriter")
                    .length(49)
                    .releaseDate(LocalDate.of(1990, 1, 14))
                    .episodeNumber(2)
                    .synopsis("The staff discover that a libellous word has been painted on Lord Meldrum's car. Who was the phantom signwriter?")
                    .build();

            Episode episode2 = Episode.builder()
                    .title("The Deed of Gift")
                    .length(49)
                    .releaseDate(LocalDate.of(1990, 1, 21))
                    .episodeNumber(3)
                    .synopsis("The Honourable Teddy creeps upstairs every night and tries to get into Ivy's room; and Lady Lavender is bent on giving away her shares of the family fortune - to the servants.")
                    .build();

            Episode episode3 = Episode.builder()
                    .title("Love or Money")
                    .length(49)
                    .releaseDate(LocalDate.of(1990, 1, 28))
                    .episodeNumber(4)
                    .synopsis("Poppy arranges a secret assignation with James, and a fancy dress party is planned to celebrate Cissy's birthday which brings a few surprises.")
                    .build();

            Episode episode4 = Episode.builder()
                    .title("Fair Shares")
                    .length(50)
                    .releaseDate(LocalDate.of(1990, 2, 4))
                    .episodeNumber(5)
                    .synopsis("Lady Lavender has decided where her shares are going and Ivy is in trouble for what she saw at the fancy dress party the night before.")
                    .build();

            Episode episode5 = Episode.builder()
                    .title("Beg, Borrow or Steal")
                    .length(50)
                    .releaseDate(LocalDate.of(1990, 2, 11))
                    .episodeNumber(6)
                    .synopsis("Alf is being blackmailed and is determined to get his hands on Lady Lavender’s shares and Alf and Ivy’s secret is revealed but to whom?")
                    .build();

            Episode episode6 = Episode.builder()
                    .title("Labour or Love")
                    .length(49)
                    .releaseDate(LocalDate.of(1990, 11, 11))
                    .episodeNumber(1)
                    .synopsis("Lord Meldrum decrees that his brother, The Honourable Teddy, will marry Madge or go to work in the family business to keep his mind off servant girls.")
                    .build();

            Episode episode7 = Episode.builder()
                    .title("Trouble at the Mill")
                    .length(49)
                    .releaseDate(LocalDate.of(1990, 11, 18))
                    .episodeNumber(2)
                    .synopsis("Teddy’s actions cause a mass walk out at the factory and the Prime Minister is coming to dinner. Meanwhile Alf is consumed by thoughts of Lady Lavender -or rather her money.")
                    .build();

            Episode episode8 = Episode.builder()
                    .title("Money Talks")
                    .length(48)
                    .releaseDate(LocalDate.of(1990, 11, 25))
                    .episodeNumber(3)
                    .synopsis("The Bishop is collecting for his charity auction, Lady Lavender throws her money out of the window and light-fingered Alf, resourceful as ever, has a plan.")
                    .build();

            Season season1 = Season.builder()
                    .seasonNumber(1)
                    .episodes(Stream.of(episode0, episode1, episode2, episode3, episode4, episode5, episode6).collect(Collectors.toSet()))
                    .build();

            season1.getEpisodes().forEach(episode -> episode.setSeason(season1));

            Season season2 = Season.builder()
                    .seasonNumber(2)
                    .episodes(Stream.of(episode7, episode8).collect(Collectors.toSet()))
                    .build();

            season2.getEpisodes().forEach(episode -> episode.setSeason(season2));

            Series series = Series.builder()
                    .genre(Genre.COMEDY)
                    .title("You Rang, M'Lord?")
                    .cast(Arrays.asList("Paul Shane", "Jeffrey Holland", "Su Pollard", "Donald Hewlett", "Mavis Pugh"))
                    .season(season1)
                    .season(season2)
                    .build();

            season1.setSeries(series);
            season2.setSeries(series);

            seriesRepository.save(series);
        };
    }

}
