package com.nikitalipatov.springcity.dto;

import java.util.List;

public record CarShopRecord(String name, List<CarRecord> cars) {
}
