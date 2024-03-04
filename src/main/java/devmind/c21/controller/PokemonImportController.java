package devmind.c21.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import devmind.c21.dto.PokemonLiteDto;
import devmind.c21.entity.Pokemon;
import devmind.c21.mapper.PokemonEnricher;
import devmind.c21.mapper.PokemonMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/import")
public class PokemonImportController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private List<Pokemon> importData() throws JsonProcessingException {
        String uri = "https://pokeapi.co/api/v2/pokemon/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        List<PokemonLiteDto> pokemonLiteDtoList = PokemonMapper.toLiteDto(result);

        List<Pokemon> list = new ArrayList<>();
        for (PokemonLiteDto pokemonLiteDto : pokemonLiteDtoList) {
            Optional<Pokemon> pokemon = PokemonEnricher.enrichPokemon(pokemonLiteDto);
            if (pokemon.isPresent()) {
                Pokemon pokemon1 = pokemon.get();
                list.add(pokemon1);
            }
        }
        return list;
    }
}
