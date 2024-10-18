package org.example.staffmaster2.service;

import org.example.staffmaster2.dao.CondidatureDao;
import org.example.staffmaster2.entity.Candidature;
import org.example.staffmaster2.util.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CandidatureServiceTest {

    @Mock
    private CondidatureDao condidatureDao;

    @Mock
    private EmailSender emailSender;

    @InjectMocks
    private CandidatureService candidatureService;

    private Candidature candidature;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        candidature = new Candidature(1L, 3L, "john.d@test.com", "Java", false);
    }

    @Test
    public void testAddCandidature_Success() {
        doNothing().when(condidatureDao).addCondidature(candidature);
        candidatureService.addCandidature(candidature);
        verify(condidatureDao).addCondidature(candidature);
    }

    @Test
    public void testAddCandidature_NullCandidature_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidatureService.addCandidature(null);
        });
        assertEquals("Candidature cannot be null", exception.getMessage());
    }
    

    @Test
    public void testGetPendingCandidatures_WithCompetenceFilter_ShouldReturnFilteredResults() {
        Candidature javaCandidature = new Candidature(2L, 3L, "jane.d@test.com", "Java", false);
        Candidature pythonCandidature = new Candidature(3L, 3L, "jack.d@test.com", "Python", false);
        List<Candidature> allCandidatures = List.of(javaCandidature, pythonCandidature);

        when(condidatureDao.getCandidatures()).thenReturn(allCandidatures);

        List<Candidature> filteredResult = candidatureService.getPendingCandidatures("Java");
        assertEquals(1, filteredResult.size());
        assertEquals("Java", filteredResult.get(0).getCompetance());
    }


    @Test
    public void testAddCandidature_EmptyEmail_ShouldThrowException() {
        candidature.setEmail("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidatureService.addCandidature(candidature);
        });
        assertEquals("Candidature email cannot be null or empty", exception.getMessage());
    }


    @Test
    public void testConfirmCandidature_NullId_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidatureService.confirmCandidature(null);
        });
        assertEquals("Candidature ID cannot be null", exception.getMessage());
    }


    @Test
    public void testGetPendingCandidatures_Success() {
        List<Candidature> pendingCandidatures = List.of(candidature);
        when(condidatureDao.getCandidatures()).thenReturn(pendingCandidatures);
        List<Candidature> result = candidatureService.getPendingCandidatures(null);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetPendingCandidatures_NoCandidatures_ShouldThrowException() {
        when(condidatureDao.getCandidatures()).thenReturn(null);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            candidatureService.getPendingCandidatures(null);
        });
        assertEquals("No candidatures found in the database", exception.getMessage());
    }

    @Test
    public void testGetCandidatureById_Success() {
        when(condidatureDao.getCandidatureById(1L)).thenReturn(candidature);
        Candidature result = candidatureService.getCandidatureById(1L);
        assertEquals(candidature, result);
    }

    @Test
    public void testGetCandidatureById_NullId_ShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidatureService.getCandidatureById(null);
        });
        assertEquals("Candidature ID cannot be null", exception.getMessage());
    }

    @Test
    public void testGetCandidatureById_NotFound_ShouldThrowException() {
        when(condidatureDao.getCandidatureById(1L)).thenReturn(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            candidatureService.getCandidatureById(1L);
        });
        assertEquals("Candidature not found for ID: 1", exception.getMessage());
    }
}
