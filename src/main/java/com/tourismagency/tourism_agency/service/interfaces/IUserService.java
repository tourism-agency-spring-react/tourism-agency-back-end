package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;

import java.util.List;

public interface IUserService {

    UserDTO getUser(Long id);

    List<UserDTO> getAllUsers();

    void saveUser(LoginRequestDTO user);

    void deleteUser(Long id);

    void updateUser(Long id, LoginRequestDTO user);
}
