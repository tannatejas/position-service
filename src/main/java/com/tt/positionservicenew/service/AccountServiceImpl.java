package com.tt.positionservicenew.service;

import com.tt.positionservicenew.dto.AccountRequestDTO;
import com.tt.positionservicenew.dto.AccountResponseDTO;
import com.tt.positionservicenew.dto.PositionDTO;
import com.tt.positionservicenew.entity.Account;
import com.tt.positionservicenew.entity.Position;
import com.tt.positionservicenew.repository.AccountRepository;
import com.tt.positionservicenew.repository.PositionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Transactional
    @Override
    public void saveAccounts(List<AccountRequestDTO> request) {
        List<Account> accounts = new ArrayList<>();

        // Process each account in the request
        for (AccountRequestDTO accountRequestDTO : request) {
            Account account = getAccount(accountRequestDTO);

            accounts.add(account);
        }
        // Save accounts and positions
        accountRepository.saveAll(accounts);

        logger.info("Accounts and Positions saved successfully");
    }

    /**
     * Get Accounts
     * @param accountRequestDTO {@link AccountRequestDTO} Account Request
     * @return {@link Account}
     */
    private Account getAccount(AccountRequestDTO accountRequestDTO) {
        Account account = new Account();
        account.setAccountId(accountRequestDTO.getAccountId());

        List<Position> positions = new ArrayList<>();
        for (PositionDTO positionDTO : accountRequestDTO.getPositions()) {
            Position position = new Position();
            position.setAssetId(positionDTO.getAssetId());
            position.setQuantity(positionDTO.getQuantity());
            positions.add(position);
        }
        account.setPositions(positions);
        return account;
    }

    @Override
    public List<AccountResponseDTO> retrieveAccounts(List<String> accountIds) {
        logger.info("Retrieving accounts for accountIds: {}", accountIds);
        List<Account> accounts = accountRepository.findByAccountIdIn(accountIds);
        logger.info("Retrieved accounts: {}", accounts);

        return accounts.stream()
                .map(this::convertToAccountResponseDTO)
                .collect(Collectors.toList());
    }

    private AccountResponseDTO convertToAccountResponseDTO(Account account) {
        AccountResponseDTO responseDTO = new AccountResponseDTO();
        responseDTO.setAccountId(account.getAccountId());
        List<PositionDTO> positionDTOs = account.getPositions().stream()
                .map(position -> {
                    PositionDTO positionDTO = new PositionDTO();
                    positionDTO.setAssetId(position.getAssetId());
                    positionDTO.setQuantity(position.getQuantity());
                    return positionDTO;
                })
                .collect(Collectors.toList());
        responseDTO.setPositions(positionDTOs);
        return responseDTO;
    }
}