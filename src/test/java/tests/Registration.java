package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Registration extends TestBase{

    @BeforeMethod
    public void preCondition(){

        if(app.user().isLogOutPresent()){
            app.user().logout();
        }
    }

    @Test
    public void registrationSuccess(){
        int index = (int)(System.currentTimeMillis()/1000)%3600;

        app.user().openRegistrationForm();
        app.user().fillRegistrationForm("Mia","Regen","mia"+index+"@gmail.com","Miar12345$");
        // app.user().checkPolicy();
        app.user().checkPolicyJS();
        app.user().submit();
        Assert.assertEquals(app.user().checkMessage(),"You are logged in success");
    }

    @Test
    public void registrationSuccessModel(){
        int index = (int)(System.currentTimeMillis()/1000)%3600;

        User user = new User().withName("Mia").withLastName("Regen").withEmail("mia"+index+"@gmail.com")
                        .withPassword("Miar12345$");

        app.user().openRegistrationForm();
        app.user().fillRegistrationForm(user);
        // app.user().checkPolicy();
        app.user().checkPolicyJS();
        app.user().submit();
        //app.user().pause(1000);
        Assert.assertEquals(app.user().checkMessage(),"You are logged in success");
    }


    @Test
    public void registrationWrongPasswordModel(){

        User user = new User().withName("Mia").withLastName("Regen").withEmail("mia@gmail.com")
                .withPassword("12345");
        app.user().openRegistrationForm();
        app.user().fillRegistrationForm(user);
        app.user().checkPolicyJS();
        app.user().submit();
       //error + button not active
        app.user().pause(2000);
        Assert.assertTrue(app.user().isErrorDisplayPasswordSize());
        Assert.assertTrue(app.user().isErrorDisplayPasswordFormat());
        Assert.assertFalse(app.user().isYallaButtonNotActive());
        Assert.assertTrue(app.user().isYallaButtonNotClickable());
    }

    @Test
    public void registrationSuccessNew(){
        int index = (int)(System.currentTimeMillis()/1000)%3600;

        app.user().openRegistrationForm();
        app.user().fillRegistrationForm("Mia","Regen","mia"+index+"@gmail.com","Miar12345$");
        // app.user().checkPolicy();
        app.user().checkPolicyJS();
        app.user().submit();
        app.user().pause(2000);
        Assert.assertEquals(app.user().checkMessage(),"You are logged in success");

    }

    @AfterMethod
    public void postCondition(){
        app.user().submitOkButton();
    }
}
