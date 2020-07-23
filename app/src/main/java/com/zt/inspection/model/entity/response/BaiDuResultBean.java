package com.zt.inspection.model.entity.response;

public class BaiDuResultBean {
    private String formatted_address;
    private BDaddressComponent addressComponent;

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public BDaddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(BDaddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }
}
