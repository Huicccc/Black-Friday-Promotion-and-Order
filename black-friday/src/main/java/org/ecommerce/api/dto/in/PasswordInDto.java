package org.ecommerce.api.dto.in;


import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordInDto {

  private String userId;

  @NotNull(message = "OldPassword cannot be null")
  private String oldPassword;

  @NotNull(message = "NewPassword cannot be null")
  private String newPassword;
}
