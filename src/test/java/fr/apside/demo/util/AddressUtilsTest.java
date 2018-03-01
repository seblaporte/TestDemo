package fr.apside.demo.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.datagouv.Feature;
import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;

public class AddressUtilsTest {

    private SearchResponse searchResponseWithTwoResults;

    @Before
    public void init() {

        searchResponseWithTwoResults = new SearchResponse();

        Feature features[] = { new Feature(), new Feature() };
        // First response
        Properties propertiesOne = new Properties();
        propertiesOne.setHousenumber("2");
        propertiesOne.setStreet("Place de la Gare");
        propertiesOne.setPostcode("37700");
        propertiesOne.setCity("Saint-Pierre-des-Corps");
        propertiesOne.setScore(0.9d);
        // Second response
        Properties propertiesTwo = new Properties();
        propertiesTwo.setHousenumber("2");
        propertiesTwo.setStreet("Avenue de Paris");
        propertiesTwo.setPostcode("45000");
        propertiesTwo.setCity("Orléans");
        propertiesTwo.setScore(0.8d);

        features[0].setProperties(propertiesOne);
        features[1].setProperties(propertiesTwo);

        searchResponseWithTwoResults.setFeatures(features);

    }

    @Test
    public void should_return_properties_from_searchResponse_with_highest_score() {

        Properties result = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(searchResponseWithTwoResults);

        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    @Test
    public void should_return_address_from_properties() {

        Properties properties = searchResponseWithTwoResults.getFeatures()[0].getProperties();

        Address result = AddressUtils.getAddressFromProperties(properties);

        assertEquals("2", result.getNumber());
        assertEquals("Place de la Gare", result.getStreet());
        assertEquals("37700", result.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", result.getCity());
    }

    @Test
    public void should_return_inline_address() {
        Address addressExample = new Address();
        addressExample.setNumber("2");
        addressExample.setStreet("Place de la Gare");
        addressExample.setPostcode("37700");
        addressExample.setCity("Saint-Pierre-des-Corps");

        assertThat(AddressUtils.createInlineAddressFromObject(addressExample))
                .isEqualTo("2 Place de la Gare 37700 Saint-Pierre-des-Corps");
    }
}
