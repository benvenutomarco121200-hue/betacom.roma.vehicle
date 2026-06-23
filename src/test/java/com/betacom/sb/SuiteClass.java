package com.betacom.sb;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.sb.category.CategoryTest;
import com.betacom.sb.fuelType.FuelTypeTest;
import com.betacom.sb.vehicle.VehicleTest;
import com.betacom.sb.vehicleType.VehicleTypeTest;
import com.betacom.sb.delete.DeleteFinalTest;

@Suite
@SelectClasses({
    VehicleTypeTest.class,
    CategoryTest.class,
    FuelTypeTest.class,
    VehicleTest.class,
    DeleteFinalTest.class
})
public class SuiteClass {

}