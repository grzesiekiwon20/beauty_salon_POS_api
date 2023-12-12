package com.beautysalon.repository;


import com.beautysalon.repository.model.Client;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClientRepositoryTest {

    private final ClientRepository repository = mock(ClientRepository.class);
    private final Client client = new Client(1,"Greg","greg@wp.pl",new ArrayList<>());


    @Test
    public void verifyIfClientSavesCorrectlyInTheRepository(){

        repository.save(client);

        verify(repository).save(client);
    }
    @Test
    public void verifyIfClientCanBeFoundByEmail(){
        repository.save(client);

        final String email = client.getEmail();
        when(repository.findByEmail(email)).thenReturn(client);

        final Client expectedClient = repository.findByEmail(email);

        assertEquals(email, expectedClient.getEmail());
    }
}
