package com.uniandes.abcjobsgrp23;

import static org.junit.Assert.assertTrue;

import com.uniandes.abcjobsgrp23.view.candidato.CandidatoLoginActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CandidatoLoginActivityUnitTest {

    @Mock
    private CandidatoLoginActivity candidatoLoginActivity;

    @Before
    public void setUp() {
//        AutoCloseable closeable = MockitoAnnotations.openMocks(this);

        // Inicializa una instancia real de CandidatoLoginActivity
        candidatoLoginActivity = new CandidatoLoginActivity();
    }

    @Test
    public void testIsValidLogin_WithValidCredentials_ReturnsTrue() {
        // Arrange
//        Mockito.when(candidatoLoginActivity.isValidLogin("usuario", "12345")).thenReturn(true);

        // Act
        boolean result = candidatoLoginActivity.isValidLogin("usuario", "12345");

        // Assert
        assertTrue(result);
    }

}