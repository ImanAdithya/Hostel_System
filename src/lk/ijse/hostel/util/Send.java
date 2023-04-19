package lk.ijse.hostel.util;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Send {
    public static final String ACCOUNT_SID = System.getenv("AC8850f892acb3902d9e931c05f308453f");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");
    public static final String TWILIO_PHONE_NUMBER = "+94715994936";
    public static void main(String[] args) {

            // Find your Account Sid and Auth Token at twilio.com/console
            // and set the environment variables. See http://twil.io/secure



                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

                Message message = Message.creator(
                                new PhoneNumber("+94788249177"), // To number
                                new PhoneNumber(TWILIO_PHONE_NUMBER), // From number
                                "Hello from Twilio!")
                        .create();

                System.out.println(message.getSid());
    }
}
