package com.bahadirakin.ml;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

    @Value("${spark.ml.model.path}")
    private String modelPath;

    @Bean(destroyMethod = "stop")
    public SparkContext sparkContext() {
        final SparkConf conf = new SparkConf()
                .setAppName("MLSparkService")
                .setMaster("local");
        return new SparkContext(conf);
    }

    @Bean(destroyMethod = "stop")
    public JavaSparkContext javaSparkContext(SparkContext sparkContext) {
        return new JavaSparkContext(sparkContext);
    }

    @Bean
    public LogisticRegressionModel logisticRegressionModel(SparkContext sparkContext) {
        return LogisticRegressionModel.load(sparkContext, modelPath);
    }
}
