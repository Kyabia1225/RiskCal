package com.nju.risk.util;

import com.nju.risk.model.CompanyInfo;
import com.nju.risk.model.StorageTank;

public class UtilCollections {
    public static boolean checkIfRequireCalculationGlobally(StorageTank tank) {
        boolean requiresCalculation = true;
        requiresCalculation &= tank.getInstallationType().equals(1);
        requiresCalculation &= tank.isWorkingAsConstantPressure();
        requiresCalculation &= !tank.getSubstanceState().equals(2);
        return requiresCalculation;
    }

    public static void prepareTankDefaultData(StorageTank tank, CompanyInfo companyInfo) {
        if (tank.getTankLatitude() == null || tank.getTankLongitude() == null) {
            tank.setTankLatitude(companyInfo.getLatitude());
            tank.setTankLongitude(companyInfo.getLongitude());
        }
        if (tank.getSubstanceState() == 1) {
            tank.setSubstanceDensity(1.29);
        }
    }
}
