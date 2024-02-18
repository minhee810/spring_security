package com.example.testSecurity.service;

import com.example.testSecurity.dto.JoinDto;
import com.example.testSecurity.entity.User;
import com.example.testSecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {


    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void joinProcess(JoinDto joinDto) {

        // db에 이미 동일한 username 을 가진 회원이 존재하는지 확인
        boolean isUser = userRepository.existsByUsername(joinDto.getUsername());

        if (isUser) {
            return;
        }

        User user = new User();

        user.setUsername(joinDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(joinDto.getPassword()));
        user.setRole("ROLE_ADMIN"); // 강제적으로 넣어줘야함.

        userRepository.save(user);

    }
}
