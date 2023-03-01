package com.nikitalipatov.springcity.controller;

import com.nikitalipatov.springcity.dto.*;
import com.nikitalipatov.springcity.service.CarShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/shop")
public class CarShopController {

    private final CarShopService carShopService;

    @PostMapping(value = "/create/{shopName}")
    public CarShopDto createCarShop(@PathVariable String shopName) {
        return carShopService.create(shopName);
    }

    @PutMapping(value = "/{carShopId}/add")
    public CarShopDto addCar(@PathVariable int carShopId, @RequestBody List<Integer> carIds) {
        return carShopService.addCar(carShopId, carIds);
    }

    @GetMapping(value = "/{carShopId}/preorder/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PreorderResultDto preorderStatus(@PathVariable int carShopId, @PathVariable int carId, @RequestBody PreorderRecord preorderRecord) throws ExecutionException, InterruptedException {
        return carShopService.preorderCar(carShopId, carId, preorderRecord);
    }

    @GetMapping(value = "/preorder/{requestId}")
    public CarShopResponseDto preorderByRequest(@PathVariable int requestId) {
        return carShopService.getPreorder(requestId);
    }
}
