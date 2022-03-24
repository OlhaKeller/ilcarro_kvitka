package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){

        if(app.user().isLogOutPresent()){
            app.user().logout();
        }
    }

    @Test
    public void loginSuccess(){

        app.user().openLoginForm();
        app.user().fillLoginForm("kvitka@gmail.com","Kvitka12345$");
        app.user().submit();
        app.user().pause(1000);
        Assert.assertEquals(app.user().checkMessage(),"Logged in success");
    }

    @Test
    public void loginSuccessModel(){
        User user = new User().withEmail("kvitka@gmail.com").withPassword("Kvitka12345$");
        //user.setEmail("");
        //user.setPassword(""); war bei Java


        app.user().openLoginForm();
        app.user().fillLoginForm(user);
        app.user().submit();
        app.user().pause(1000);
        Assert.assertEquals(app.user().checkMessage(),"Logged in success");
    }

    @Test
    public void loginSuccessNew(){

       app.user().openLoginForm();
       app.user().fillLoginForm("kvitka@gmail.com","Kvitka12345$");
       app.user().submit();
       app.user().pause(1000);
       Assert.assertEquals(app.user().checkMessage(),"Logged in success");



    }

    @AfterMethod
    public void postCondition(){
        app.user().submitOkButton();
    }
}
