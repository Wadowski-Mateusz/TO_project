package org.shop.classes;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.shop.interfaces.DbcAdapter;


class PaymentTest {

    int id = 1;
    float value = 123.45F;
    String status = Payment.STATUS_PAYMENT_FALSE;
    String str = String.valueOf(id) + "," + String.valueOf(value) + "," + status;

    @Test
    void convertingTest() {

        Payment.setDbcAdapter(getMock());
        DbcAdapter<String> mock = getMock();
        Payment p = (Payment) Payment.convertFromRecord(id);
        assert p != null;
        assert p.convertToRecord().equals(str);

    }


    private DbcAdapter<String> getMock(){
        DbcAdapter<String> mock = Mockito.mock(DbcAdapterRecordString.class);
        Mockito.when(mock.loadData(id, Payment.class)).thenReturn(str);
        Mockito.when(mock.adaptDataToDBFormat(ArgumentMatchers.anyString())).thenAnswer(s -> s.getArguments()[0]);
        return mock;

    }

}

