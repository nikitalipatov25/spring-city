package com.nikitalipatov.springcity.controller;

import com.nikitalipatov.springcity.dto.*;
import com.nikitalipatov.springcity.service.CarShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/car_shop")
public class CarShopController {

    private final CarShopService carShopService;

    @PostMapping(value = "/create")
    public CarShopDto createCarShop(@RequestBody CarShopRecord carShopRecord) {
        return carShopService.create(carShopRecord);
    }

    @PutMapping(value = "/{carShopId}/add_car")
    public CarShopDto addCar(@PathVariable int carShopId, @RequestBody CarShopRecord carShopRecord) {
        return carShopService.addCar(carShopId, carShopRecord);
    }

    @GetMapping(value = "/preorder/status")
    public String preorderStatus() {
        return carShopService.preorderStatus();
    }

    @GetMapping(value = "/{carShopId}/preorder/{carId}")
    public CarShopResponseDto preorderCar (@PathVariable int carShopId, @PathVariable int carId, @RequestBody PreorderRecord preorderRecord) {
        preorderStatus();
        return carShopService.preorderCar(carShopId, carId, preorderRecord);
    }
}
