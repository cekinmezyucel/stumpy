package com.stumpy.controller;

import static com.stumpy.localization.SwaggerLocalizations.STANDART_PROD_CONS;
import static org.springframework.http.HttpStatus.OK;

import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stumpy.service.StatisticService;

@RestController
@RequestMapping("/statistic")
@Api(value = "statistic", produces = STANDART_PROD_CONS, protocols = "https", consumes = STANDART_PROD_CONS,
    tags = {"statistic"})
public class StatisticController {

  @Autowired
  private StatisticService statisticService;

  @ResponseStatus(OK)
  @GetMapping(value = "/count/{statisticTypeName}/{shortUrl}")
  public Long getHitCount(@PathVariable("statisticTypeName") String statisticTypeName,
      @PathVariable("shortUrl") String shortUrl) {
    return statisticService.getStatisticCount(shortUrl, statisticTypeName);
  }

}
