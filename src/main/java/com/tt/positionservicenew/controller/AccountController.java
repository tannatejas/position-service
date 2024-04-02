package com.tt.positionservicenew.controller;

import com.tt.positionservicenew.dto.AccountDTO;
import com.tt.positionservicenew.dto.AccountRequestDTO;
import com.tt.positionservicenew.dto.AccountResponseDTO;
import com.tt.positionservicenew.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping("/savePosition")
    public ResponseEntity<Void> savePosition(@RequestBody List<AccountRequestDTO> request) {
        accountService.saveAccounts(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/getPosition")
    public ResponseEntity<List<AccountResponseDTO>> getPosition(@RequestBody AccountDTO request) {
        List<String> accountIds = request.getAccountId();
        List<AccountResponseDTO> accounts = accountService.retrieveAccounts(accountIds);
        return ResponseEntity.ok(accounts);
    }
}