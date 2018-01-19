package fr.apside.demo.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.datagouv.Feature;
import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;

public class AddressUtilsTest {

    @Test
    public void should_return_properties_from_searchResponse_with_highest_score() {

        SearchResponse searchResponse = createSearchResponseExampleWithTwoResults();

        Properties result = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(searchResponse);

        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    @Test
    public void should_return_address_from_properties() {

        Properties properties = createPropertiesExample();

        Address result = AddressUtils.getAddressFromProperties(properties);

        assertEquals("2", result.getNumber());
        assertEquals("Place de la Gare", result.getStreet());
        assertEquals("37700", result.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    private Properties createPropertiesExample() {
        return createSearchResponseExampleWithTwoResults().getFeatures()[0].getProperties();
    }

    private SearchResponse createSearchResponseExampleWithTwoResults() {

        SearchResponse searchResponse = new SearchResponse();

        Feature features[] = { new Feature(), new Feature() };

        Properties propertiesOne = new Properties();
        propertiesOne.setHousenumber("2");
        propertiesOne.setStreet("Place de la Gare");
        propertiesOne.setPostcode("37700");
        propertiesOne.setCity("Saint-Pierre-des-Corps");
        propertiesOne.setScore(0.9);

        Properties propertiesTwo = new Properties();
        propertiesTwo.setHousenumber("2");
        propertiesTwo.setStreet("Avenue de Paris");
        propertiesTwo.setPostcode("45000");
        propertiesTwo.setCity("Orléans");
        propertiesTwo.setScore(0.8);

        features[0].setProperties(propertiesOne);
        features[1].setProperties(propertiesTwo);

        searchResponse.setFeatures(features);

        return searchResponse;
    }
}
