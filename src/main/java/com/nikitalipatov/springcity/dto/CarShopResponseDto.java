package com.nikitalipatov.springcity.dto;

import com.nikitalipatov.springcity.enums.PreorderStatus;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarShopResponseDto {
    private boolean success;
    private PreorderStatus bankPreorderStatus;
    private PreorderStatus GAIPreorderStatus;
    private PreorderStatus drivingSchoolPreorderStatus;
    private String message;
}
