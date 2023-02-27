package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.CarDto;
import com.nikitalipatov.springcity.dto.CarRecord;
import com.nikitalipatov.springcity.dto.CarShopDto;
import com.nikitalipatov.springcity.dto.CarShopRecord;
import com.nikitalipatov.springcity.model.CarShop;

public interface CarShopService {

    CarShopDto addCar (int carShopId, CarShopRecord carShopRecord);

    CarShopDto create(CarShopRecord carShopRecord);

    CarShop getCarShop(int carShopId);

    String preorderStatus();
}
