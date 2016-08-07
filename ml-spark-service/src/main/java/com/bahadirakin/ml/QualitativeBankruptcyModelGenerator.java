package com.bahadirakin.ml;


import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.classification.LogisticRegressionWithLBFGS;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import scala.Tuple2;

public class QualitativeBankruptcyModelGenerator {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf()
                .setAppName("QualitativeBankruptcyModelGenerator")
                .setMaster("local");
        final SparkContext sparkContext = new SparkContext(conf);
        final JavaSparkContext sc = new JavaSparkContext(sparkContext);

        final String path = "src/main/resources/Qualitative_Bankruptcy.data.txt";
        final JavaRDD<String> textFile = sc.textFile(path, 1);
        System.out.println("Data Count: " + textFile.count());

        final JavaRDD<LabeledPoint> data = textFile.map(line -> {
            final String[] split = line.split(",");
            final double label = normalizeLabel(split[split.length - 1]);
            final double[] doubles = new double[split.length - 1];
            for (int i = 0; i < split.length - 1; i++) {
                doubles[i] = normalizeFeature(split[i]);
            }
            final Vector features = Vectors.dense(doubles);
            return new LabeledPoint(label, features);
        });

        data.take(10).forEach(System.out::println);

        // Split initial RDD into two... [60% training data, 40% testing data].
        JavaRDD<LabeledPoint>[] splits = data.randomSplit(new double[]{0.6, 0.4}, 11L);
        JavaRDD<LabeledPoint> training = splits[0].cache();
        JavaRDD<LabeledPoint> test = splits[1];

        // Run training algorithm to build the model.
        final LogisticRegressionModel model = new LogisticRegressionWithLBFGS()
                .setNumClasses(2)
                .run(training.rdd());

        // Compute raw scores on the test set.
        JavaRDD<Tuple2<Object, Object>> predictionAndLabels = test.map(p -> {
            Double prediction = model.predict(p.features());
            return new Tuple2<>(prediction, p.label());
        });

        // Get evaluation metrics.
        final MulticlassMetrics metrics = new MulticlassMetrics(predictionAndLabels.rdd());
        System.out.println("Accuracy = " + metrics.accuracy());

        // Save model
        final String modelLink = "target/model/Qualitative_Bankruptcy_Model";
        model.save(sparkContext, modelLink);


        sparkContext.stop();
        sc.stop();
    }

    private static double normalizeFeature(String data) {
        if ("P".equals(data)) return 1.0;
        if ("A".equals(data)) return 0.0;
        if ("N".equals(data)) return -1.0;

        throw new IllegalArgumentException("Unexpected data: " + data);
    }

    private static double normalizeLabel(String data) {
        if ("NB".equals(data)) return 1.0;
        if ("B".equals(data)) return 0.0;

        throw new IllegalArgumentException("Unexpected data: " + data);
    }
}
