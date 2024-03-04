package devmind.c21.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import devmind.c21.dto.PokemonLiteDto;
import devmind.c21.entity.Pokemon;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

public class PokemonEnricher {

    public static Optional<Pokemon> enrichPokemon(PokemonLiteDto pokemonLite) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String uri = pokemonLite.getUrl();
        String string = restTemplate.getForObject(uri, String.class);
        ObjectMapper mapper = new ObjectMapper();

        Pokemon pokemon = mapper.readValue(string, mapper.getTypeFactory().constructType(Pokemon.class));
        return Optional.of(pokemon);
    }
}

