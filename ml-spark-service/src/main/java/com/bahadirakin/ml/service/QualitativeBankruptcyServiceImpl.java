package com.bahadirakin.ml.service;

import com.bahadirakin.ml.dto.CompanyInfo;
import com.bahadirakin.ml.dto.CompanyPrediction;
import com.bahadirakin.ml.dto.RiskStatus;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class QualitativeBankruptcyServiceImpl implements QualitativeBankruptcyService {

    private final JavaSparkContext javaSparkContext;
    private final LogisticRegressionModel logisticRegressionModel;

    @Autowired
    public QualitativeBankruptcyServiceImpl(JavaSparkContext javaSparkContext,
                                            LogisticRegressionModel logisticRegressionModel) {
        this.javaSparkContext = javaSparkContext;
        this.logisticRegressionModel = logisticRegressionModel;
    }

    @Override
    public CompanyPrediction predict(CompanyInfo companyInfo) {
        final JavaRDD<Vector> normalizedCompanyInfo = javaSparkContext
                .parallelize(Collections.singletonList(companyInfo))
                .map(info -> Vectors.dense( // Order is important!
                        normalizeFeature(companyInfo.getIndustrialRisk()),
                        normalizeFeature(companyInfo.getManagementRisk()),
                        normalizeFeature(companyInfo.getFinancialFlexibility()),
                        normalizeFeature(companyInfo.getCredibility()),
                        normalizeFeature(companyInfo.getCompetitiveness()),
                        normalizeFeature(companyInfo.getOperatingRisk())
                ));
        final double prediction = logisticRegressionModel.predict(normalizedCompanyInfo).first();
        return deNormalizeResult(prediction);
    }

    private static double normalizeFeature(RiskStatus riskStatus) {

        if (riskStatus == RiskStatus.POSITIVE) return 1.0;
        if (riskStatus == RiskStatus.AVERAGE) return 0.0;
        if (riskStatus == RiskStatus.NEGATIVE) return -1.0;

        throw new IllegalArgumentException("Unexpected riskStatus: " + riskStatus);
    }

    private static CompanyPrediction deNormalizeResult(double result) {
        if (result == 1.0) return CompanyPrediction.NON_BANKRUPTCY;
        if (result == 0.0) return CompanyPrediction.BANKRUPTCY;

        throw new IllegalArgumentException("Unexpected prediction result: " + result);
    }
}
