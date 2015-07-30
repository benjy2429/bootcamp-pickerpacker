package com.sky.bootcamp.pickerpacker;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;
import com.sky.bootcamp.pickerpacker.activities.TabbedActivity;

@SuppressWarnings("rawtypes")
public class LoginTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "com.sky.bootcamp.pickerpacker.activities.LoginActivity";
    private final String LOGIN_EMAIL = "testpicker@test.com";
    private final String LOGIN_PASSWORD = "root";

    private static Class<?> launcherActivityClass;
    static{
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public LoginTest() throws ClassNotFoundException {
        super(launcherActivityClass);
    }

    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testCorrectLogin() {
        solo.waitForActivity("LoginActivity", 2000);
        //solo.clearEditText((android.widget.AutoCompleteTextView) solo.getView("email"));
        solo.enterText((android.widget.AutoCompleteTextView) solo.getView("email"), LOGIN_EMAIL);
        solo.enterText((android.widget.EditText) solo.getView("password"), LOGIN_PASSWORD);
        solo.clickOnButton("Login");
        solo.assertCurrentActivity("TabbedActivity", TabbedActivity.class);
    }

    public void testIncorrectPassword() {
        solo.waitForActivity("LoginActivity", 2000);
        solo.enterText((android.widget.AutoCompleteTextView) solo.getView("email"), LOGIN_EMAIL);
        solo.enterText((android.widget.EditText) solo.getView("password"), "123456");
        solo.clickOnButton("Login");
        assertTrue(solo.waitForText("The email or password is incorrect"));
    }
}