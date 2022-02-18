package io.github.arthurgmr.service.implementation;

import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.github.arthurgmr.domain.entity.Client;
import io.github.arthurgmr.domain.repository.IClientRepository;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private IClientRepository clientRepository;
    private ClientServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new ClientServiceImpl(clientRepository);
    }

    @Test
    void testSaveClinet() {
        Client client = new Client("testLogin", "11895092612");
        underTest.saveClinet(client);

        ArgumentCaptor<Client> clientArgumentCaptor = ArgumentCaptor.forClass(Client.class);

        verify(clientRepository).save(clientArgumentCaptor.capture());

        Client capturedClient = clientArgumentCaptor.getValue();

        AssertionsForClassTypes.assertThat(capturedClient).isEqualTo(client);
    }


    @Test
    void testDeleteClient() {

        UUID id = UUID.randomUUID();

        given(clientRepository.existsById(id)).willReturn(true);

        underTest.deleteClient(id);

        verify(clientRepository).deleteById(id);

    }

    @Test
    void testFindClient() {

    }

    @Test
    void testGetClient() {
        UUID id = UUID.randomUUID();

        given(clientRepository.existsById(id)).willReturn(false);

        underTest.getClient(id);

        verify(clientRepository).findById(id);

    }


    @Test
    void testUpdateClient() {

    }
}
