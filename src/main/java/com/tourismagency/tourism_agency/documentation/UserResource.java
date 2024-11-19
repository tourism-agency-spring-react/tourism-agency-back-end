package com.tourismagency.tourism_agency.documentation;

import com.tourismagency.tourism_agency.presentation.dto.LoginRequestDTO;
import com.tourismagency.tourism_agency.presentation.dto.UserDTO;
import com.tourismagency.tourism_agency.presentation.payload.AuthResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "User", description = "Operations related with user entity")
public interface UserResource {

    @Operation(
            description = "Get information of one User",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the information of one user in format json.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "idUser",
                            description = "id of the user to search",
                            example = "1"
                    )
            }
    )
    ResponseEntity<UserDTO> getById(@PathVariable Long idUser);

    @Operation(
            description = "Create new User",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return information of user created in format Json",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "user",
                            description = "Object of the new user to save",
                            example = "{email: email@gmail.com, password: password123}"
                    )
            }
    )
    ResponseEntity<UserDTO> register(@RequestBody @Valid LoginRequestDTO user);

    @Operation(
            description = "Create new session to the user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return information of Access",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AuthResponse.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "user",
                            description = "Object with access credentials of user",
                            example = "{email: email@gmail.com, password: password123}"
                    )
            }
    )
    ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequestDTO user);
}
