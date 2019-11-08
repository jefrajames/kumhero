package io.superherosample.api;

import com.kumuluz.ee.graphql.annotations.GraphQLClass;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;

import io.superherosample.db.DuplicateSuperHeroException;
import io.superherosample.db.HeroDatabase;
import io.superherosample.db.UnknownTeamException;
import io.superherosample.model.SuperHero;
import io.superherosample.model.Team;

@RequestScoped
@GraphQLClass
public class SuperHeroApi {

    @Inject
    HeroDatabase heroDB;

    @GraphQLQuery(description = "Retrieves all heroes")
    public Collection<SuperHero> allHeroes() {
        return heroDB.getAllHeroes();
    }

    @GraphQLQuery(description = "Retrieves heroes from a given city")
    public Collection<SuperHero> allHeroesIn(@GraphQLArgument(name = "city") String city) {
        return allHeroesByFilter(hero -> {
            return city.equals(hero.getPrimaryLocation());
        });
    }

    @GraphQLQuery(description = "Rerieves heroes with a given power")
    public Collection<SuperHero> allHeroesWithPower(@GraphQLArgument(name = "power", description = "The power") String power) {
        return allHeroesByFilter(hero -> {
            return hero.getSuperPowers().contains(power);
        });
    }

    @GraphQLQuery(description = "Retrieves all heroes in a given team")
    public Collection<SuperHero> allHeroesInTeam(@GraphQLArgument(name = "team", description = "The team name") String teamName) throws UnknownTeamException {
        return heroDB.getTeam(teamName).getMembers();
    }

    @GraphQLQuery(description = "Retrieves all teams")
    public Collection<Team> allTeams() {
        return heroDB.getAllTeams();
    }

    @GraphQLMutation(description = "Creates a new hero")
    public SuperHero createNewHero(@GraphQLArgument(name = "hero", description = "The hero to be created") SuperHero newHero) throws DuplicateSuperHeroException {
        heroDB.addHero(newHero);
        return heroDB.getHero(newHero.getName());
    }

    @GraphQLMutation(description = "Adds a hero to the specified team")
    public Team addHeroToTeam(@GraphQLArgument(name = "hero", description = "The hero name") String heroName,
            @GraphQLArgument(name = "team") String teamName)
            throws UnknownTeamException {

        return heroDB.getTeam(teamName)
                .addMembers(heroDB.getHero(heroName));
    }

    @GraphQLMutation(description = "Removes a hero from a specified team")
    public Team removeHeroFromTeam(@GraphQLArgument(name = "hero", description = "The hero name") String heroName,
            @GraphQLArgument(name = "team") String teamName)
            throws UnknownTeamException {

        return heroDB.getTeam(teamName)
                .removeMembers(heroDB.getHero(heroName));
    }

    private Collection<SuperHero> allHeroesByFilter(Predicate<SuperHero> predicate) {
        return heroDB.getAllHeroes()
                .stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @GraphQLQuery(description = "Retrieves a hero")
    public SuperHero findHeroByName(@GraphQLArgument(name = "name", description = "The name of the hero") String name) {
        SuperHero hero = heroDB.getHero(name);
        return hero;
    }
}
