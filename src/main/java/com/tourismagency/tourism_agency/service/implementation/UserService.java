package com.tourismagency.tourism_agency.service.implementation;

import com.tourismagency.tourism_agency.enums.RoleEnum;
import com.tourismagency.tourism_agency.persistense.model.UserEntity;
import com.tourismagency.tourism_agency.persistense.repository.IUserRepository;
import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;
import com.tourismagency.tourism_agency.service.interfaces.IRoleService;
import com.tourismagency.tourism_agency.service.interfaces.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ConversionService conversionService;
    private final PasswordEncoder passwordEncoder;
    private final IRoleService roleService;

    public UserService(IUserRepository userRepository, ConversionService conversionService, PasswordEncoder passwordEncoder, IRoleService roleService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public UserDTO getUser(Long id) {
        UserEntity user = userRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario no existe"));

        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream().map(user -> conversionService.convert(user, UserDTO.class)).toList();
    }

    @Override
    public UserDTO createUser(LoginRequestDTO user) {

        UserEntity userEntity = UserEntity
                .builder()
                .email(user.email())
                .password(passwordEncoder.encode(user.password()))
                .credentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .isEnabled(true)
                .roles(Set.of(roleService.getRole(RoleEnum.ADMIN)))
                .build();

        userRepository.save(userEntity);

        return UserDTO
                .builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .build();
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
