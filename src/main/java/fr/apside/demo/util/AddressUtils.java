package fr.apside.demo.util;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.datagouv.Feature;
import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;

public class AddressUtils {

    public static Properties getPropertiesWithBestScoreFromSearchResponse(SearchResponse searchResponse) {

        Properties properties = new Properties();
        double highestScore = 0L;

        if (searchResponse != null && searchResponse.getFeatures() != null) {
            for (Feature feature : searchResponse.getFeatures()) {
                if (feature.getProperties() != null && feature.getProperties().getScore() > highestScore) {
                    highestScore = feature.getProperties().getScore();
                    properties = feature.getProperties();
                }
            }
        }

        return properties;
    }

    public static Address getAddressFromProperties(Properties properties) {

        Address address = new Address();

        address.setNumber(properties.getHousenumber());
        address.setStreet(properties.getStreet());
        address.setPostcode(properties.getPostcode());
        address.setCity(properties.getCity());

        return address;
    }

    public static String createInlineAddressFromObject(Address address) {
        return String.format(
                "%s %s %s %s",
                    address.getNumber(),
                    address.getStreet(),
                    address.getPostcode(),
                    address.getCity());
    }
}
