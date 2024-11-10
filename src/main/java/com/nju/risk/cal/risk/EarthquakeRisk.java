package com.nju.risk.cal.risk;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class EarthquakeRisk implements DeviceBrokenRisk {
    private static final Map<String, Double> intensityToPGA = new LinkedHashMap<>();
    private static final Map<String, double[]> riskStateToKValues = new LinkedHashMap<>();

    static {
        // 初始化烈度到PGA的映射
        intensityToPGA.put("I", 0.0018);
        intensityToPGA.put("II", 0.00369);
        intensityToPGA.put("III", 0.00757);
        intensityToPGA.put("IV", 0.0155);
        intensityToPGA.put("V", 0.0319);
        intensityToPGA.put("VI", 0.0653);
        intensityToPGA.put("VII", 0.135);
        intensityToPGA.put("VIII", 0.279);
        intensityToPGA.put("IX", 0.577);
        intensityToPGA.put("X", 1.19);
        intensityToPGA.put("XI", 2.47);
        intensityToPGA.put("XII", 3.55);

        // 初始化风险状态到k值的映射
        riskStateToKValues.put("a", new double[]{0.43, 1.26});
        riskStateToKValues.put("b", new double[]{-2.83, 1.64});
        riskStateToKValues.put("c", new double[]{1.77, 1.14});
        riskStateToKValues.put("d", new double[]{-0.92, 1.25});
    }

    public double[] calculateRisk(String intensity, String riskState) {
        Double pga = intensityToPGA.get(intensity);
        double[] kValues = riskStateToKValues.get(riskState);

        if (pga == null || kValues == null) {
            throw new IllegalArgumentException("Invalid intensity or risk state provided.");
        }

        // 返回包含k1, k2和PGA的数组
        return new double[]{kValues[0], kValues[1], pga};
    }

    @Override
    public Map<String, Double> calculate(CompanyInfo companyInfo, StorageTank storageTank) {
        String[] intensities = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};
        String[] riskStates = new String[]{"a", "b", "c", "d"};
        LinkedHashMap<String, Double> res = new LinkedHashMap<>();
        for (String intensity : intensities) {
            for (String riskState : riskStates) {
                double[] kValues = calculateRisk(intensity, riskState);
                Double risk = definiteIntegral(kValues[0], kValues[1], kValues[2]);
                res.put(String.format("烈度：%s，风险状态：%s", intensity, riskState), risk);
            }
        }
        return res;
    }

    Double definiteIntegral(Double k1, Double k2, Double PGA) {
        double Y = k1 + k2 * Math.log(PGA);
        NormalDistribution normalDistribution = new NormalDistribution(0, 1);
        return normalDistribution.cumulativeProbability(Y-5.0);
    }
}
