package at.spengergasse.rmbackend.service.dto.command;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(@NotBlank String email, @NotBlank String password) {
}
