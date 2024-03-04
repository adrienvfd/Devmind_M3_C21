package devmind.c21.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import devmind.c21.dto.PokemonLiteDto;
import devmind.c21.entity.Pokemon;
import devmind.c21.mapper.PokemonEnricher;
import devmind.c21.mapper.PokemonMapper;
import devmind.c21.repository.PokemonRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@Data
@RequiredArgsConstructor
public class PokemonService {

    private final PokemonRepository pokemonRepository;


    public Optional<Pokemon> getPokemonById(Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon updatePokemon(Pokemon pokemon) {
        if (pokemonRepository.existsById(pokemon.getId())) {
            return pokemonRepository.save(pokemon);
        } else {
            throw new RuntimeException("Pokemon not found");
        }
    }

    public void updatePokemonNameById(Long id, String name) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.updatePokemonNameById(id, name);
        } else {
            throw new RuntimeException("Pokemon not found");
        }
    }

    public Set<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll().stream().collect(toSet());
    }

    public Pokemon createPokemon(Pokemon pokemon) {
        pokemon.setId(null);
        return pokemonRepository.save(pokemon);
    }

    public Pokemon createPokemon(String name) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setId(null);
        return pokemonRepository.save(pokemon);
    }

    public void deletePokemon(Pokemon pokemon) {
        pokemonRepository.delete(pokemon);
    }

    public void deletePokemonById(Long id) {
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pokemon not found");
        }
    }

    public List<Pokemon> init() throws JsonProcessingException {
        String uri = "https://pokeapi.co/api/v2/pokemon/";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        List<PokemonLiteDto> pokemonLiteDtoList = PokemonMapper.toLiteDto(result);

        Set<Pokemon> set = new HashSet<>();
        for (PokemonLiteDto pokemonLiteDto : pokemonLiteDtoList) {
            Optional<Pokemon> pokemon = PokemonEnricher.enrichPokemon(pokemonLiteDto);
            if (pokemon.isPresent()) {
                Pokemon pokemon1 = pokemon.get();
                set.add(pokemon1);
            }
        }
        return pokemonRepository.saveAll(set);
    }
}
