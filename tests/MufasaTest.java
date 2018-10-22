import static org.junit.Assert.*;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class MufasaTest {
    MufasaAccountConfirmation muffAccConf;
    Mufasa muf;

    @Before
    public void setup() {
        muf = new Mufasa();

        muffAccConf = EasyMock.createMock(MufasaAccountConfirmation.class);
        muf.setMufAccConf(muffAccConf);

    }

    @Test
    public void testDeductionPlusRefund() {
        EasyMock.expect(muffAccConf.deductMoney(111)).andReturn(true);
        EasyMock.expect(muffAccConf.refundMoney()).andReturn(true);
        EasyMock.replay(muffAccConf); 
        assertEquals(true, muf.verifyAccountAttachmentConfirmation(111));
    }

    @Test
    public void testDeductionOnly_T(){
        EasyMock.expect(muffAccConf.deductMoney(111)).andReturn(true);
        EasyMock.replay(muffAccConf); 
        assertEquals("Pass", muf.confirmAccountDeduction(111));
    }

    @Test
    public void testDeductionOnly_F(){
        EasyMock.expect(muffAccConf.deductMoney(111)).andReturn(false);
        EasyMock.replay(muffAccConf); 
        assertEquals("Failed", muf.confirmAccountDeduction(111));
    }

    @Test
    public void testRefundOnly_T(){
        EasyMock.expect(muffAccConf.refundMoney()).andReturn(true);
        EasyMock.replay(muffAccConf); 
        assertEquals("Refund Success", muf.confirmAccountRefund());
    }

    @Test
    public void testRefundOnly_F(){
        EasyMock.expect(muffAccConf.refundMoney()).andReturn(false);
        EasyMock.replay(muffAccConf); 
        assertEquals("Refund Failed", muf.confirmAccountRefund());
    }
    
    @Test(expected = Exception.class) 
    public void testUserCreation(){
        try{
            muf.createUserFromForm("firstName", "lastName", "userName",
         "password", "passwordValidation", "country", "birthDate", "phoneNumber",
          "email", "emailValidation");}
         catch (Exception ex){};
        
    }
}