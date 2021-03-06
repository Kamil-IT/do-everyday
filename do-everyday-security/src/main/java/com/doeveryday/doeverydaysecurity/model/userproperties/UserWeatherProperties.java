package com.doeveryday.doeverydaysecurity.model.userproperties;

import com.doeveryday.doeverydaysecurity.model.AppUser;
import com.doeveryday.doeverydayweather.model.WeatherProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class UserWeatherProperties extends WeatherProperties {

//    @Builder()
//    public UserWeatherProperties(Double longitude, Double latitude, ForecastUrlBuild.Language language, ForecastUrlBuild.Units units, String id, AppUser user) {
//        super(longitude, latitude, language, units);
//        this.id = id;
//        this.user = user;
//    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private String id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
