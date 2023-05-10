package fr.wcs.springtests.data;

public class DemoService {
    public boolean isLiquid(String objectName) {
        return objectName.equals("water")
                || objectName.equals("coca")
                || objectName.equals("milk");
    }
}
