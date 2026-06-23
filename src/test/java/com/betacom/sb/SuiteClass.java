package com.betacom.sb;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.betacom.sb.SuspensionType.SuspensionTypeTest;
import com.betacom.sb.bicycle.BicycleTest;
import com.betacom.sb.brakeType.BrakeTypeTest;
import com.betacom.sb.category.CategoryTest;

@Suite
@SelectClasses({
	CategoryTest.class,
	BrakeTypeTest.class,
	SuspensionTypeTest.class,
	BicycleTest.class
})
public class SuiteClass {
	
}
