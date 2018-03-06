package fr.apside.demo.service;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.apside.demo.domain.datagouv.SearchResponse;
import fr.apside.demo.exception.NoAddressRetrievedException;
import fr.apside.demo.repository.AddressDataGouvRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressDataGouvServiceTest {

    @InjectMocks
    AddressDataGouvService    addressDataGouvService;

    @Mock
    AddressDataGouvRepository addressDataGouvRepository;

    @Test(expected = NoAddressRetrievedException.class)
    public void it_should_not_find_address() throws NoAddressRetrievedException {

        SearchResponse emptySearchResponse = new SearchResponse();
        emptySearchResponse.setFeatures(null);

        when(addressDataGouvRepository.search(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(emptySearchResponse);

        String badAddress = "Adresse incorrecte";

        addressDataGouvService.searchAddress(badAddress);
    }

}
