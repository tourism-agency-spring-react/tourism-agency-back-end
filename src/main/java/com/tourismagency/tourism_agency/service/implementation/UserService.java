package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.persistense.model.UserEntity;
import com.tourismagency.tourism_agency.persistense.repository.IUserRepository;
import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;
import com.tourismagency.tourism_agency.service.interfaces.IUserService;
import com.tourismagency.tourism_agency.util.mapper.UserMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO getUser(Long id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        return UserMapper.entityToDto(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return UserMapper.entityToDto(userRepository.findAll());
    }

    @Override
    public void saveUser(LoginRequestDTO user) {
        userRepository.save(UserMapper.dtoToEntity(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, LoginRequestDTO user) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        userEntity.setEmail(user.email());
        userEntity.setPassword(user.password());
        userRepository.save(userEntity);
    }
}
