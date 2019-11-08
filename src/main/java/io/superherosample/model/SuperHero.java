package io.superherosample.model;

import io.leangen.graphql.annotations.GraphQLInputField;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.List;

public class SuperHero {

    List<Team> teamAffiliations;
    List<String> superPowers;

    @GraphQLInputField(description = "Primary Location Input")
    String primaryLocation;

    @GraphQLInputField(description = "Name Input")
    String name;

    @GraphQLInputField(description = "Real Name Input")
    String realName;

    public List<Team> getTeamAffiliations() {
        return teamAffiliations;
    }

    public void setTeamAffiliations(List<Team> teamAffiliations) {
        this.teamAffiliations = teamAffiliations;
    }

    public List<String> getSuperPowers() {
        return superPowers;
    }

    public void setSuperPowers(List<String> superPowers) {
        this.superPowers = superPowers;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public void setPrimaryLocation(String primaryLocation) {
        this.primaryLocation = primaryLocation;
    }

    @GraphQLQuery(description = "XXXXXXXXXXXX")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    
}
