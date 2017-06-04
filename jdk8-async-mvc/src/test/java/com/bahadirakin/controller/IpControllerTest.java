package com.bahadirakin.controller;

import com.bahadirakin.model.Ip;
import com.bahadirakin.service.HttpBinService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.CompletableFuture;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class IpControllerTest {

    MockMvc mockMvc;

    @Mock
    HttpBinService httpBinService;

    @InjectMocks
    IpController ipController;

    @Before
    public void setUp() throws Exception {
        mockMvc = new MockMvcBuilder().withService(ipController).build();
    }

    @Test
    public void ip() throws Exception {
        // Given
        final Ip ip = new Ip("127.0.0.1");
        given(httpBinService.ip()).willReturn(CompletableFuture.completedFuture(ip));

        // When
        ResponseEntity<Ip> responseEntity = ipController.ip().get();

        // Then
        then(httpBinService).should().ip();
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody(), is(ip));
    }

}