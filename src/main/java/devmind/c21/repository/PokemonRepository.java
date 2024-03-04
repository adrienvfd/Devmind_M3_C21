package devmind.c21.repository;

import devmind.c21.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    @Modifying
    @Query("UPDATE Pokemon p SET p.name = :name WHERE p.id = :id")
    void updatePokemonNameById(@Param("id") Long id, @Param("name") String name);
}