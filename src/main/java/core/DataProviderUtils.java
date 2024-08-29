package core;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class DataProviderUtils {
    @DataProvider(name = "TestData")
    public Object[][] getData(Method method){
        String testName = method.getName();
        String dataPath = CommonFunction.getTestDataPath(testName);
        return ExcelUtils.getTableArray(dataPath,false);
    }
}
