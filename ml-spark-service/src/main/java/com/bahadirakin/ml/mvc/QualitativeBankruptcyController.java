package com.bahadirakin.ml.mvc;


import com.bahadirakin.ml.dto.CompanyInfo;
import com.bahadirakin.ml.dto.CompanyPrediction;
import com.bahadirakin.ml.service.QualitativeBankruptcyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rest")
public class QualitativeBankruptcyController {

    private static final Logger logger = LoggerFactory.getLogger(QualitativeBankruptcyController.class);

    @Autowired
    private QualitativeBankruptcyService qualitativeBankruptcyService;

    @RequestMapping(path = "/predict", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CompanyPrediction> createUser(@RequestBody CompanyInfo companyInfo) {
        try {
            final CompanyPrediction prediction = qualitativeBankruptcyService.predict(companyInfo);
            return new ResponseEntity<>(prediction, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Cannot predict result for {}", companyInfo, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
