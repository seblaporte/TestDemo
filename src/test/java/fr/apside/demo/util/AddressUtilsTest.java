package fr.apside.demo.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.datagouv.Feature;
import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;

public class AddressUtilsTest {

    private SearchResponse searchResponseWithOneResult;

    @Before
    public void init() {

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

    @Test
    public void should_return_address_from_properties() {

        Properties properties = searchResponseWithOneResult.getFeatures()[0].getProperties();

        Address result = AddressUtils.getAddressFromProperties(properties);

        assertThat(result.getNumber()).isEqualTo("3");
        assertThat(result.getStreet()).isEqualTo("Rue des Minimes");
        assertThat(result.getPostcode()).isEqualTo("37000");
        assertThat(result.getCity()).isEqualTo("Tours");
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
