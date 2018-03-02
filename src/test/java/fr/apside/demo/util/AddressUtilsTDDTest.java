package fr.apside.demo.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.apside.demo.domain.datagouv.Feature;
import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;

public class AddressUtilsTDDTest {

    private SearchResponse searchResponseWithTwoResults;

    private SearchResponse searchResponseWithOneResult;

    @Before
    public void init() {

        // Deux reponse
        searchResponseWithTwoResults = new SearchResponse();
        Feature multipleFeatures[] = { new Feature(), new Feature() };
        // Premiere reponse
        Properties propertiesOne = new Properties();
        propertiesOne.setHousenumber("2");
        propertiesOne.setStreet("Place de la Gare");
        propertiesOne.setPostcode("37700");
        propertiesOne.setCity("Saint-Pierre-des-Corps");
        propertiesOne.setScore(0.9d);
        // Seconde reponse
        Properties propertiesTwo = new Properties();
        propertiesTwo.setHousenumber("2");
        propertiesTwo.setStreet("Avenue de Paris");
        propertiesTwo.setPostcode("45000");
        propertiesTwo.setCity("Orléans");
        propertiesTwo.setScore(0.8d);
        // Array
        multipleFeatures[0].setProperties(propertiesTwo);
        multipleFeatures[1].setProperties(propertiesOne);
        searchResponseWithTwoResults.setFeatures(multipleFeatures);

        // Une reponse
        searchResponseWithOneResult = new SearchResponse();
        Feature features[] = { new Feature() };
        // Reponse unique
        Properties properties = new Properties();
        properties.setHousenumber("3");
        properties.setStreet("Rue des Minimes");
        properties.setPostcode("37000");
        properties.setCity("Tours");
        properties.setScore(0.95d);
        // Array
        features[0].setProperties(properties);
        searchResponseWithOneResult.setFeatures(features);
    }

    // STEP 1
    @Test
    public void should_return_properties_from_unique_search_response() {

        Properties result = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(searchResponseWithOneResult);

        assertThat(result).isNotNull();
        assertThat(result.getCity()).isEqualTo("Tours");
    }

    // STEP 2
    @Test
    public void should_return_empty_properties_from_null_search_response() {

        Properties result = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(null);

        assertThat(result).isNotNull();
        assertThat(result.getCity()).isNullOrEmpty();
    }

    // STEP 3
    @Test
    public void should_return_empty_properties_from_empty_search_response() {

        Properties result = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(new SearchResponse());

        assertThat(result).isNotNull();
        assertThat(result.getCity()).isNullOrEmpty();
    }

    // STEP 4
    @Test
    public void should_return_highest_scored_properties_from_multiple_search_responses() {

        Properties result = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(searchResponseWithTwoResults);

        assertThat(result).isNotNull();
        assertThat(result.getCity()).isEqualTo("Saint-Pierre-des-Corps");
    }

}
