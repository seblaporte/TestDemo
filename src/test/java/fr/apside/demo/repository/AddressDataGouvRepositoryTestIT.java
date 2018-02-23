package fr.apside.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;

public class AddressDataGouvRepositoryTestIT {

    @Test
    public void it_should_return_address() {

        AddressDataGouvRepository addressDataGouvRepository = new AddressDataGouvRepository();

        SearchResponse response = addressDataGouvRepository
                .search("https://api-adresse.data.gouv.fr/search", "2 place de la gare 37700 Saint-Pierre-des-Corps");

        assertTrue("La recherche doit retourner au moins 1 résultat", response.getFeatures().length > 1);

        Properties properties = response.getFeatures()[0].getProperties();

        assertEquals("2", properties.getHousenumber());
        assertEquals("Place de la Gare", properties.getStreet());
        assertEquals("37700", properties.getPostcode());
        assertEquals("Saint-Pierre-des-Corps", properties.getCity());
    }

}
