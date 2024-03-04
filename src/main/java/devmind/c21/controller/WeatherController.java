package devmind.c21.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@Data
public class WeatherController {
    private final String OPENWEATHERMAP_API_URL = "https://api.openweathermap.org/data/2.5/onecall";

    @Value("${WEATHER_API_KEY}")
    private String API_KEY;


    @GetMapping("/weather")
    public String getWeather(@RequestParam("lat") String lat,
                             @RequestParam("lon") String lon) {

        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = String.format("%s?lat=%s&lon=%s&appid=%s",
                OPENWEATHERMAP_API_URL, lat, lon, API_KEY);

        return restTemplate.getForObject(apiUrl, String.class);
    }
}
