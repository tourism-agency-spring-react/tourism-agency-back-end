package com.tourismagency.tourism_agency.service.interfaces;

import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;

import java.util.List;

public interface IUserService {

    List<UserDTO> getUsers();

    UserDTO getUser(Long id);

    UserDTO createUser(LoginRequestDTO loginRequestDTO);

    void updateUser(Long id, LoginRequestDTO loginRequestDTO);

    void deleteUser(Long id);
}
