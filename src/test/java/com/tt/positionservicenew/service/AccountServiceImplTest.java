package com.tt.positionservicenew.service;

import com.tt.positionservicenew.dto.AccountRequestDTO;
import com.tt.positionservicenew.dto.AccountResponseDTO;
import com.tt.positionservicenew.dto.PositionDTO;
import com.tt.positionservicenew.entity.Account;
import com.tt.positionservicenew.entity.Position;
import com.tt.positionservicenew.repository.AccountRepository;
import com.tt.positionservicenew.repository.PositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PositionRepository positionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRetrieveAccounts_NoAccountsFound() {
        // Mock data
        when(accountRepository.findByAccountIdIn(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call method
        List<AccountResponseDTO> responseDTOs = accountService.retrieveAccounts(Collections.emptyList());

        // Verify results
        assertEquals(0, responseDTOs.size());
    }
}
