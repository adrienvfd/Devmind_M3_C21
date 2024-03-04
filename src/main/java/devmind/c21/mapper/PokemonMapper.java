package devmind.c21.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import devmind.c21.dto.PokemonLiteDto;
import devmind.c21.dto.PokemonResponseDto;

import java.util.Collections;
import java.util.List;

public class PokemonMapper {

    public static List<PokemonLiteDto> toLiteDto(String string) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            PokemonResponseDto pokemonResponseDto = mapper.readValue(string, mapper.getTypeFactory().constructType(PokemonResponseDto.class));
            return pokemonResponseDto.getPokemonLiteDtoList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
