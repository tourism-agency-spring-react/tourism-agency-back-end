package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.UserEntity;
import com.tourismagency.tourism_agency.persistense.repository.IUserRepository;
import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;
import com.tourismagency.tourism_agency.service.interfaces.IUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    private final ConversionService conversionService;

    public UserService(IUserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    public List<UserDTO> getUsers() {
        return Collections.singletonList(conversionService.convert(userRepository.findAll(), UserDTO.class));
    }

    @Override
    public UserDTO getUser(Long id) {
        return conversionService.convert(userRepository.findById(id), UserDTO.class);
    }

    @Override
    public UserDTO createUser(LoginRequestDTO loginRequestDTO) {

        UserEntity newUser = UserEntity
                .builder()
                .email(loginRequestDTO.email())
                .password(loginRequestDTO.password())
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .build();

        return conversionService.convert(newUser, UserDTO.class);
    }

    @Override
    public void updateUser(Long id, LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
        userEntity.setEmail(loginRequestDTO.email());
        userEntity.setPassword(loginRequestDTO.password());
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado."));
        userRepository.deleteById(id);
    }
}
