package com.tourismagency.tourism_agency.documentation;

import com.tourismagency.tourism_agency.presentation.advice.response.ErrorApiResponse;
import com.tourismagency.tourism_agency.presentation.dto.CustomerDTO;
import com.tourismagency.tourism_agency.presentation.payload.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name="customer", description = "Operations related with customer entity")
public interface CustomerResource {

    @Operation(
            description = "Get information of one customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the information of one customer in format json.",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = CustomerDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens with the reservation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "id of the customer to search",
                            example = "1"
                    )
            }
    )
    ResponseEntity<CustomerDTO> get(@Min(1) @PathVariable("id") Long id);

    @Operation(
            description = "Get information of all customers",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the information of all customers",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = List.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Return nothing"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens with the customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    )

            }
    )
    ResponseEntity<List<CustomerDTO>> getAll();


    @Operation(
            description = "Create one customer",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "Return successfully message in the body",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = CustomerDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Customer are exists in the database",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens with the customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    )
            }
    )
    ResponseEntity<MessageResponse> create (@RequestBody @Valid CustomerDTO customerDTO);

    @Operation(
            description = "Update one customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return successfully message in the body"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorApiResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens with the reservation",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorApiResponse.class)
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "id of the customer to update",
                            example = "1"
                    )
            }
    )
    ResponseEntity<MessageResponse> update (@Min(1) @PathVariable ("id") Long id, @RequestBody @Valid CustomerDTO customerDTO);

    @Operation(
            description = "Deleted one customer",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return nothing",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = Void.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Customer not found",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorApiResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Something bad happens with the customer",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(
                                            implementation = ErrorApiResponse.class
                                    )
                            )
                    )
            },
            parameters = {
                    @Parameter(
                            in = ParameterIn.PATH,
                            name = "id",
                            description = "id of the customer to delete"
                    )
            }
    )
    ResponseEntity<MessageResponse> delete (@PathVariable("id") Long id);
}
