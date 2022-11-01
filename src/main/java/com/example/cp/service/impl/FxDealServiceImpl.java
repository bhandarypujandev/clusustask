package com.example.cp.service.impl;

import com.example.cp.domain.FxDeal;
import com.example.cp.repo.FxDealRepo;
import com.example.cp.service.FxDealService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class FxDealServiceImpl implements FxDealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FxDealService.class);

    private FxDealRepo fxDealRepo;

    @Autowired
    public FxDealServiceImpl(FxDealRepo fxDealRepo) {
        this.fxDealRepo = fxDealRepo;
    }

    @Override
    public FxDeal saveFxDeal(String row) {
        FxDeal fxDeal = validateFields(row);
        LOGGER.info("Saving Row " + row);
        return fxDealRepo.save(fxDeal);
    }

    //Validating date only cause other validations are unknown
    private FxDeal validateFields(String row) {
        LOGGER.info("Validating Row " + row);
        String[] columns = row.split(",");
        FxDeal fxDeal = new FxDeal();
        if (columns[0] != null)
            fxDeal.setId(Long.valueOf(columns[0]));
        if (columns[1] != null)
            fxDeal.setFromCurrencyIso(columns[1]);
        if (columns[2] != null)
            fxDeal.setToCurrencyIso(columns[2]);
        try {
            fxDeal.setDealTime(new SimpleDateFormat("dd/mm/yyyy").parse(columns[3]));
        } catch (ParseException e) {
            LOGGER.info("Invalid date " + columns[3]);
            e.printStackTrace();
        }
        if (columns[4] != null)
            fxDeal.setAmount(new BigDecimal(columns[4]));
        return fxDeal;
    }
}
