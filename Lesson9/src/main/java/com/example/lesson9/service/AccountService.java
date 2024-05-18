package com.example.lesson9.service;

import com.example.lesson9.dto.AccountDto;
import com.example.lesson9.entity.Account;
import com.example.lesson9.respository.AccountRepository;
import com.example.lesson9.untils.CloudinaryUltil;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountService {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("account.dir"));
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final CloudinaryUltil cloudinaryUltil;
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
//    public Account create(AccountDto accountDto) {
//        Account account= modelMapper.map(accountDto, Account.class);
//        return accountRepository.save(account);
//    }
    public Account create(AccountDto accountDto) throws IOException {
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(Objects.requireNonNull(accountDto.getAvatar().getOriginalFilename()));
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(accountDto.getAvatar().getBytes());
        }
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(accountDto.getPassword());
        account.setAvatar(imagePath.resolve(accountDto.getAvatar().getOriginalFilename()).toString());
        return accountRepository.save(account);
    }
}
