package com.doeveryday.doeverydayweather.urlcreator;

import tk.plogitech.darksky.forecast.APIKey;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.Timeouts;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ForecastUrlBuild {

    private static final Timeouts DEFAULT_TIMEOUTS = new Timeouts(Duration.ofSeconds(6), Duration.ofSeconds(6));
    private static final String URL = "https://api.darksky.net/forecast/##key##/##latitude##,##longitude####time##";
    private final List<Block> exclusion = new ArrayList<>();
    private Language language = Language.en;
    private Units units = Units.si;
    private String overrideUrl;
    private boolean extendHourly;
    private GeoCoordinates geoCoordinates;
    private APIKey apiKey;
    private Instant time;
    private Timeouts timeouts = DEFAULT_TIMEOUTS;

    /**
     * @param apiKey Your Dark Sky secret key. (Your secret key must be kept secret; in particular, do not embed it in JavaScript source code that you
     * transmit to clients.)
     * @return This for fluent API.
     */
    public ForecastUrlBuild key(APIKey apiKey) {
        if (apiKey == null){
            throw new NullPointerException("APIKey cannot be null.");
        }

        this.apiKey = apiKey;
        return this;
    }

    /**
     * @param geoCoordinates The Geo coordinates of a location for which the weather forecast is requested.
     * @return This for fluent API.
     */
    public ForecastUrlBuild location(GeoCoordinates geoCoordinates) {
        if (geoCoordinates == null){
            throw new NullPointerException("GeoCoordinates cannot be null.");
        }
        else if (geoCoordinates.latitude() == null || geoCoordinates.longitude() == null){
            throw new NullPointerException("Latitude or longitude cannot be null");
        }


        this.geoCoordinates = geoCoordinates;
        return this;
    }

    /**
     * @param url Override the default DarksSky API Url. The URL must contain the following patterns for the key and gelocation:<br>
     *
     * ##key## ##latitude## ##longitude##
     *
     * @return This for fluent API.
     */
    public ForecastUrlBuild url(String url) {
        if (url == null){
            throw new NullPointerException("url cannot be null.");
        }

        this.overrideUrl = url;
        return this;
    }

    /**
     * @param language The summary properties are returned in the desired language. (Note that units in the summary will be set according to the units
     * parameter, so be sure to set both parameters appropriately.)
     * @return This for fluent API.
     */
    public ForecastUrlBuild language(Language language) {
        if (language == null){
            throw new NullPointerException("language cannot be null.");
        }

        this.language = language;
        return this;
    }

    /**
     * When set, return hour-by-hour data for the next 168 hours, instead of the next 48. When using this option, we strongly recommend enabling HTTP
     * compression.
     *
     * @return This for fluent API.
     */
    public ForecastUrlBuild extendHourly() {
        this.extendHourly = true;
        return this;
    }

    /**
     * @param units Return weather conditions in the requested units.
     * @return This for fluent API.
     */
    public ForecastUrlBuild units(Units units) {
        if (units == null){
            throw new NullPointerException("units cannot be null.");
        }

        this.units = units;
        return this;
    }

    /**
     * @param time The Time for which the historical weather data is returned.
     * @return This for fluent API.
     */
    public ForecastUrlBuild time(Instant time) {
        if (time == null){
            throw new NullPointerException("time cannot be null.");
        }

        this.time = time;
        return this;
    }

    /**
     * @param timeouts Override the default timeouts (6 seconds) for connection to the API and reading from the API.
     * @return This for fluent API.
     */
    public ForecastUrlBuild timeouts(Timeouts timeouts) {
        if (timeouts == null){
            throw new NullPointerException("timeouts cannot be null.");
        }

        this.timeouts = timeouts;
        return this;
    }

    /**
     * @return The Request with the given parameters set.
     */
    public java.net.URL build() {
        try {
            return new URL(getUrl());
        } catch (MalformedURLException ex) {
            throw new IllegalArgumentException("Cannot create Forecast Request. The provided URL is invalid!", ex);
        }
    }

    /**
     * @return The Url build from the given paramters.
     */
    private String getUrl() {

        String forecastUrlString = URL;
        if (overrideUrl != null) {
            forecastUrlString = overrideUrl;
        }

        String unixTimestamp = "";
        if (time != null) {
            unixTimestamp = "," + time.getEpochSecond();
        }

        forecastUrlString = forecastUrlString.replaceAll("##key##", apiKey.value())
                .replaceAll("##latitude##", String.valueOf(geoCoordinates.latitude().toString()).replace(',', '.'))
                .replaceAll("##longitude##", String.valueOf(geoCoordinates.longitude().toString()).replace(',', '.'))
                .replaceAll("##time##", unixTimestamp)
                + requuestParamtersAsString();

        return forecastUrlString;
    }

    /**
     * @return The RequestParamters as String formatted so that they can be added to the vase forecast url.
     */
    private String requuestParamtersAsString() {
        StringBuilder paramBuilder = new StringBuilder("?");
        if (language != null) {
            paramBuilder.append(RequestParmaterType.lang.name());
            paramBuilder.append("=");
            paramBuilder.append(language.name());
            paramBuilder.append("&");
        }
        if (units != null) {
            paramBuilder.append(RequestParmaterType.units.name());
            paramBuilder.append("=");
            paramBuilder.append(units.name());
            paramBuilder.append("&");
        }
        if (!exclusion.isEmpty()) {
            paramBuilder.append(RequestParmaterType.exclude.name());
            paramBuilder.append("=");
            StringJoiner joiner = new StringJoiner(",");
            exclusion.stream().forEach(s -> joiner.add(s.name()));
            paramBuilder.append(joiner.toString());
            paramBuilder.append("&");
        }
        if (extendHourly) {
            paramBuilder.append(RequestParmaterType.extend.name());
            paramBuilder.append("=");
            paramBuilder.append(Block.hourly.name());
            paramBuilder.append("&");
        }
        return paramBuilder.substring(0, paramBuilder.length() - 1);
    }

    /**
     * The available Languages in which the forecast response is translated.
     */
    public enum Language {
        /**
         * Arabic
         */
        ar,
        /**
         * Azerbaijani
         */
        az,
        /**
         * Belarusian
         */
        be,
        /**
         * Bosnian
         */
        bs,
        /**
         * Catalan
         */
        ca,
        /**
         * Czech
         */
        cs,
        /**
         * German
         */
        de,
        /**
         * Greek
         */
        el,
        /**
         * English (which is the default)
         */
        en,
        /**
         * Spanish
         */
        es,
        /**
         * Estonian
         */
        et,
        /**
         * French
         */
        fr,
        /**
         * Croatian
         */
        hr,
        /**
         * Hungarian
         */
        hu,
        /**
         * Indonesian
         */
        id,
        /**
         * Italian
         */
        it,
        /**
         * Icelandic
         */
        is,
        /**
         * Cornish
         */
        kw,
        /**
         * Norwegian Bokmål
         */
        nb,
        /**
         * Dutch
         */
        nl,
        /**
         * Polish
         */
        pl,
        /**
         * Portuguese
         */
        pt, /**
         * Russian
         */
        ru,
        /**
         * Slovak
         */
        sk,
        /**
         * Slovenian
         */
        sl,
        /**
         * Serbian
         */
        sr,
        /**
         * Swedish
         */
        sv,
        /**
         * Tetum
         */
        tet,
        /**
         * Turkish
         */
        tr,
        /**
         * Ukrainian
         */
        uk,
        /**
         * simplified Chinese
         */
        zh,
    }

    /**
     * The blocks of the forecast response which can be excluded.
     */
    public enum Block {
        currently,
        minutely,
        hourly,
        daily,
        alerts,
        flags,
    }

    public enum Units {
        /**
         * automatically select units based on geographic location
         */
        auto,
        /**
         * same as si, except that windSpeed is in kilometers per hour
         */
        ca,
        /**
         * SI units are as follows: summary: Any summaries containing temperature or snow accumulation units will have their values in degrees Celsius
         * or in centimeters (respectively). <br>
         * summary: Any summaries containing temperature or snow accumulation units will have their values in degrees Celsius or in centimeters
         * (respectively).<br>
         * nearestStormDistance: Kilometers.<br>
         * precipIntensity: Millimeters per hour.<br>
         * precipIntensityMax: Millimeters per hour.<br>
         * precipAccumulation: Centimeters.<br>
         * temperature: Degrees Celsius. <br>
         * temperatureMin: Degrees Celsius. <br>
         * temperatureMax: Degrees Celsius. <br>
         * apparentTemperature: Degrees Celsius. <br>
         * dewPoint: Degrees Celsius. <br>
         * windSpeed: Meters per second. <br>
         * windGust: Meters per second. pressure: Hectopascals.<br>
         * visibility: Kilometers.<br>
         */
        si,
        /**
         * same as si, except that nearestStormDistance and visibility are in miles and windSpeed is in miles per hour
         */
        uk2,
        /**
         * Imperial units (the default)
         */
        us
    }

    private enum RequestParmaterType {
        exclude,
        extend,
        lang,
        units
    }
}
