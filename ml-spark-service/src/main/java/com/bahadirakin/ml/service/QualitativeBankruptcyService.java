package com.bahadirakin.ml.service;

import com.bahadirakin.ml.dto.CompanyInfo;
import com.bahadirakin.ml.dto.CompanyPrediction;

public interface QualitativeBankruptcyService {

    CompanyPrediction predict(CompanyInfo companyInfo);
}
