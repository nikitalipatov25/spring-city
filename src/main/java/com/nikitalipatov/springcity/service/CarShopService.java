package com.nikitalipatov.springcity.service;

import com.nikitalipatov.springcity.dto.*;
import com.nikitalipatov.springcity.model.CarShop;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CarShopService {

    CarShopDto addCar (int carShopId, List<Integer> carIds);

    CarShopDto create(String shopMame);

    CarShop getCarShop(int carShopId);

    PreorderResultDto preorderCar(int carShopId, int carId, PreorderRecord preorderRecord) throws ExecutionException, InterruptedException;

    CarShopResponseDto getPreorder(int requestId);
}
