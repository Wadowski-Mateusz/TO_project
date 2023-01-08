package org.shop.classes;

import org.shop.interfaces.Convertible;

//TODO
// Payment is realised by outside app
// (we only get result)
// we can "roll a dice" for payment realisation

public class Payment implements Convertible {

    public static final String status_payment_false = "Nieoplacone";
    public static final String status_payment_true = "Oplacone";
    private final int id;
    private final float value;
    private String status;

    /**
     * @param id same as order id
     * @param value same as order value
     */
    public Payment(int id, float value){
        this.id = id;
        this.value = value;
        this.status = status_payment_false;
        DatabaseConnector dbc = DatabaseConnector.getInstance();
        if(!dbc.saveToFile(this)){
            System.out.println("Failed save to file");
        }
    }

    private Payment(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.value = Float.parseFloat(data[1]);
        this.status = data[2];
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String convertToRecord() {
        String result = this.id + ","
                + String.format("%.2f", this.value).replace(",",".")
                + "," + this.status;
        return result;
    }

    static Convertible convertFromRecord(int id) {
        DatabaseConnector db = DatabaseConnector.getInstance();
        String record = db.recordFromFile(id, Payment.class);
        if(record.isEmpty())
            return null;
        String[] data = record.split(",");
        return new Payment(data);
    }

}
