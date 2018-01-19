package fr.apside.demo.repository;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Repository;

import fr.apside.demo.domain.datagouv.SearchResponse;

@Repository
public class AddressDataGouvRepository {

    public SearchResponse search(String url, String searchAddress) {

        Client client = ClientBuilder.newClient();

        WebTarget searchTarget = client.target(url).queryParam("q", searchAddress);

        Invocation.Builder invocationBuilder = searchTarget.request(MediaType.APPLICATION_JSON);

        return invocationBuilder.get(SearchResponse.class);
    }
}
