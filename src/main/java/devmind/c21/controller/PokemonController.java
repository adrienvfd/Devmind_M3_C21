package devmind.c21.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import devmind.c21.entity.Pokemon;
import devmind.c21.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping("/init")
    public List<Pokemon> init() throws JsonProcessingException {
        return pokemonService.init();
    }

    @RequestMapping("/")
    public Set<Pokemon> getAllPokemon() {
        return pokemonService.getAllPokemon();
    }

    @RequestMapping("/{id}")
    public Optional<Pokemon> getPokemonById(@PathVariable Long id) {
        return pokemonService.getPokemonById(id);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public Pokemon updatePokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.updatePokemon(pokemon);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public void updatePokemonNameById(@PathVariable Long id, @RequestParam("name") String name) {
        pokemonService.updatePokemonNameById(id, name);
    }


    @RequestMapping(path = "/", method = RequestMethod.POST)
    public Pokemon createPokemon(@RequestBody Pokemon pokemon) {
        return pokemonService.createPokemon(pokemon);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public Pokemon createPokemon(@RequestParam("name") String name) {
        return pokemonService.createPokemon(name);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    public void deletePokemon(@RequestBody Pokemon pokemon) {
        pokemonService.deletePokemon(pokemon);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deletePokemonById(@PathVariable Long id) {
        pokemonService.deletePokemonById(id);
    }
}
