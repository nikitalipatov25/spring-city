package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.converter.CarShopConverter;
import com.nikitalipatov.springcity.dto.CarShopDto;
import com.nikitalipatov.springcity.dto.CarShopResponseDto;
import com.nikitalipatov.springcity.dto.PreorderRecord;
import com.nikitalipatov.springcity.dto.PreorderResultDto;
import com.nikitalipatov.springcity.enums.PreorderStatus;
import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.CarShop;
import com.nikitalipatov.springcity.model.Preorder;
import com.nikitalipatov.springcity.repository.CarShopRepository;
import com.nikitalipatov.springcity.repository.PreorderRepository;
import com.nikitalipatov.springcity.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class CarShopServiceImpl implements CarShopService {

    private final CarShopRepository carShopRepository;
    private final CarShopConverter carShopConverter;
    private final CarService carService;
    private final BankService bankService;
    private final GAIService gaiService;
    private final PreorderRepository preorderRepository; // todo PreorderService
    private final PreorderService preorderService;

    @Override
    @Transactional
    public CarShopDto create(String shopName) {
        return carShopConverter.toDto(carShopRepository.save(carShopConverter.toEntity(shopName)));
    }

    @Override
    public CarShopDto addCar(int carShopId, List<Integer> carIds) {
        CarShop carShop = getCarShop(carShopId);
        return carShopConverter.toDto(carShopRepository.save(carShopConverter.toEntity(carShop, carIds)));
    }

    @Override
    @Transactional
    public PreorderResultDto preorderCar(int carShopId, int carId, PreorderRecord preorderRecord) throws ExecutionException, InterruptedException {

        var preorderId = (int) (Math.random() * (99 - 1) + 1);
        Preorder preorder = Preorder.builder()
                .id(preorderId)
                .personId(preorderRecord.personId())
                .carId(carId)
                .bankPreorderStatus(PreorderStatus.UNCHECKED)
                .drivingSchoolPreorderStatus(PreorderStatus.UNCHECKED)
                .GAIPreorderStatus(PreorderStatus.UNCHECKED)
                .build();

        var carPrice = carService.getCar(carId).getPrice();
        fillPreorderDataAsync(preorder, preorderRecord, carPrice);
        return new PreorderResultDto(true, preorderId, "Заявка в работе");
    }

    @Override
    public CarShopResponseDto getPreorder(int requestId) {
        var preorder = preorderService.getPreorder(requestId);
        boolean success = preorder.getBankPreorderStatus().equals(PreorderStatus.SUCCESS)
                && preorder.getGAIPreorderStatus().name().equals("SUCCESS")
                && preorder.getDrivingSchoolPreorderStatus().name().equals("SUCCESS");
        return carShopConverter.toDto(preorder, success);
    }

    @Override
    public CarShop getCarShop(int carShopId) {
        return carShopRepository.findById(carShopId).orElseThrow(
                () -> new ResourceNotFoundException("No such car shop with id " + carShopId)
        );
    }

    private void fillPreorderDataAsync(Preorder preorder, PreorderRecord preorderRecord, double carPrice) throws ExecutionException, InterruptedException {
        CompletableFuture.allOf(
                bankService.checkBalance(preorderRecord.userBankAccount(), carPrice)
                        .thenAccept(preorder::setBankPreorderStatus)
                        .thenRun(() -> preorderRepository.save(preorder)),
                gaiService.checkDrivingLicense(preorderRecord.userDrivingLicense(), preorderRecord.personName())
                        .thenAccept(preorder::setGAIPreorderStatus)
                        .thenRun(() -> preorderRepository.save(preorder)),
                gaiService.checkDrivingCert(preorderRecord.userDrivingLicense(), preorderRecord.personName())
                        .thenAccept(preorder::setDrivingSchoolPreorderStatus)
                        // insertInto
                        .thenRun(() -> preorderRepository.save(preorder))
        );
    }
}
