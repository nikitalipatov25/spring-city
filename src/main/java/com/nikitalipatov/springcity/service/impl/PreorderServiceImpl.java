package com.nikitalipatov.springcity.service.impl;

import com.nikitalipatov.springcity.exeption.ResourceNotFoundException;
import com.nikitalipatov.springcity.model.Preorder;
import com.nikitalipatov.springcity.repository.PreorderRepository;
import com.nikitalipatov.springcity.service.PreorderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PreorderServiceImpl implements PreorderService {

    private final PreorderRepository preorderRepository;

    @Override
    public Preorder getPreorder(int requestId) {
        return preorderRepository.findById(requestId).orElseThrow(
                () -> new ResourceNotFoundException("No such preorder with request number " + requestId + " " +
                        ". If you did not made preorder check request, please do it before pre ordering a car, or check request id")
        );
    }
}
