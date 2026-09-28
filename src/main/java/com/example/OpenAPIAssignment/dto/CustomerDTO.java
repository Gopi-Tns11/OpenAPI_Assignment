package com.example.OpenAPIAssignment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class CustomerDTO {
    @Schema(
            description = "Unique identifier of the customer",
            example = "101"
        )
        private Long customerId;

//    @NotBlank(message = "Customer name is required")
//    @Schema(
//        description = "Full name of the customer",
//        example = "Gopi"
//    )
//    private String name;
    @NotBlank(message = "Customer name is required")
    @Schema(
        description = "Full name of the customer",
        example = "Gopi",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Schema(
        description = "Email address of the customer",
        example = "gopi@gmail.com",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String email;
        
        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

}
