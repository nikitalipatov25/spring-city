package com.nikitalipatov.springcity.model;

import com.nikitalipatov.springcity.enums.PreorderStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preorder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Preorder {

    @Id
    private int id;
    @Enumerated(EnumType.STRING)
    private PreorderStatus bankPreorderStatus;
    @Enumerated(EnumType.STRING)
    private PreorderStatus GAIPreorderStatus;
    @Enumerated(EnumType.STRING)
    private PreorderStatus drivingSchoolPreorderStatus;
    private int personId;
    private int carId;

}
