package com.holzhausen.bmicounter;

import android.view.View;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.holzhausen.bmicounter.activities.MainActivity;

import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.CoreMatchers.is;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.RootMatchers.*;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.*;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;

@RunWith(AndroidJUnit4.class)
public class BmiAppInstrumentedTest {

    private View decorView;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void testToastAfterWrongDataInput() {
        countBmi("555", "84");

        onView(withText("The value 555 is not proper for provided height"))
                .inRoot(withDecorView(not(is(decorView)))).check(matches(isDisplayed()));
        
    }

    @Test
    public void testMetricBmiCountAndInfoAfterClickingOnIt(){

        openMenuAndClick(R.string.metric_bmi);

        countBmi("180", "93");
        checkBmiValueAndColor("28.7", R.color.overweight);

        onView(withId(R.id.bmi_value)).perform(click());

        String description = "You are overweight";
        double bmi = 93 / (1.8 * 1.8);

        checkDescriptionIntentAndActivity(description, bmi);

    }

    @Test
    public void testImperialBmiCountAndInfoAfterClickingOnIt(){

        openMenuAndClick(R.string.imperial_bmi);

        countBmi("70", "120");
        checkBmiValueAndColor("17.22", R.color.mild_thinness);

        onView(withId(R.id.bmi_value)).perform(click());

        String description = "You are mildly thin";
        double bmi = (120.0 / (70 * 70)) * 703;

        checkDescriptionIntentAndActivity(description, bmi);

    }

    @Test
    public void testAboutView(){
        openMenuAndClick(R.string.about_option);
        intended(allOf(
                hasComponent(hasShortClassName(".activities.AboutActivity")),
                toPackage("com.holzhausen.bmicounter")
        ));

    }

    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(activity -> {
            decorView = activity.getWindow().getDecorView();
        });
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    private void countBmi(String height, String weight){
        onView(withId(R.id.height_input)).perform(typeText(height));
        onView(withId(R.id.weight_input)).perform(typeText(weight));
        onView(withId(R.id.count_bmi_button)).perform(click());
    }

    private void checkBmiValueAndColor(String bmiValue, int colorId){
        onView(withId(R.id.bmi_value)).check(matches(withText(bmiValue)));
        onView(withId(R.id.bmi_value)).check(matches(hasTextColor(colorId)));
    }

    private void checkDescriptionIntentAndActivity(String description, double bmi){
        intended(allOf(
                hasComponent(hasShortClassName(".DescriptionActivity")),
                toPackage("com.holzhausen.bmicounter"),
                hasExtra("bmi", bmi),
                hasExtra("description", description)
                )
        );

        onView(withId(R.id.description)).check(matches(withText(description)));
        onView(withId(R.id.bmi_value_description)).check(matches(withText(String.valueOf(bmi))));
    }

    private void openMenuAndClick(int itemTitleId){
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry
                .getInstrumentation().getTargetContext());
        onView(withText(itemTitleId)).perform(click());
    }

}
