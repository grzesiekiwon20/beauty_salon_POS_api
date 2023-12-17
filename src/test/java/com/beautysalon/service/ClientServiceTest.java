//package com.beautysalon.service;
//
//
//import com.beautysalon.controller.dto.ClientRequest;
//import com.beautysalon.repository.ClientRepository;
//import com.beautysalon.repository.model.Client;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//public class ClientServiceTest {
//    private ClientRepository repository;
//    private ClientService service;
//
//    @BeforeEach
//    public void setup(){
//        repository = mock(ClientRepository.class);
//        ClientMapper mapper = mock(ClientMapper.class);
//        service = new ClientService(repository, mapper);
//    }
//
////    @Test
////    public void verifyIfClientIsSavedProperly() {
//////        Client client = mock(Client.class);
//////        ClientRequest clientRequest = mock(ClientRequest.class);
//////        verify(service, times(1)).save(clientRequest);
//////
////
////
////    }
////
////    @Test
////    public void verifyIfClientCanBeSavedWithIncorrectEmailFormat() {
////
////    }
//}
