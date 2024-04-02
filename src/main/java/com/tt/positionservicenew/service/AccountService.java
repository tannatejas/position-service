package com.tt.positionservicenew.service;

import com.tt.positionservicenew.dto.AccountRequestDTO;
import com.tt.positionservicenew.dto.AccountResponseDTO;

import java.util.List;

public interface AccountService {
    public void saveAccounts(List<AccountRequestDTO> request);

    public List<AccountResponseDTO> retrieveAccounts(List<String> accountIds);
}
