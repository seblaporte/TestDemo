package fr.apside.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.apside.demo.domain.Address;
import fr.apside.demo.domain.datagouv.Properties;
import fr.apside.demo.domain.datagouv.SearchResponse;
import fr.apside.demo.exception.NoAddressRetrievedException;
import fr.apside.demo.repository.AddressDataGouvRepository;
import fr.apside.demo.util.AddressUtils;

@Service
public class AddressDataGouvService {

    @Value("${addressDataGouv.url}")
    private String url;

    @Value("${addressDataGouv.searchPath}")
    private String searchPath;

    @Autowired
    AddressDataGouvRepository repository;

    public Address searchAddress(String searchAddress) throws NoAddressRetrievedException {

        SearchResponse response = repository.search(url + searchPath, searchAddress);

        if (response.getFeatures() == null || response.getFeatures().length == 0) {
            throw new NoAddressRetrievedException("Aucune adresse approchante retrouvée");
        }

        Properties bestResult = AddressUtils.getPropertiesWithBestScoreFromSearchResponse(response);

        return AddressUtils.getAddressFromProperties(bestResult);
    }

}
