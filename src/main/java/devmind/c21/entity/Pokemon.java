package devmind.c21.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Pokemon {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @JsonProperty("id")
        Long id;

        @JsonProperty("name")
        String name;

        @JsonProperty("base_experience")
        String baseExperience;

        @JsonProperty("weight")
        String weight;

        @JsonProperty("height")
        String height;

}
