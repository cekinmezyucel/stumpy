package com.stumpy.controller;

import static com.stumpy.localization.SwaggerLocalizations.STANDART_PROD_CONS;

import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistic")
@Api(value = "statistic", produces = STANDART_PROD_CONS, protocols = "https", consumes = STANDART_PROD_CONS,
    tags = {"statistic"})
public class StatisticController {

}
