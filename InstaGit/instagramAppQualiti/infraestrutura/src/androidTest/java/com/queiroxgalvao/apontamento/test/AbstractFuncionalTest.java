package com.queiroxgalvao.apontamento.test;


import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.clearText;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class AbstractFuncionalTest<T extends Activity> extends ActivityInstrumentationTestCase2<T> {

    public AbstractFuncionalTest(Class<T> activityClass) {
        super(activityClass);
    }

    public void clicarById(int id){
        onView(withId(id)).perform(click());
    }

    public void escreverById(String texto, int id){
        onView(withId(id)).perform(clearText()).perform(typeText(texto));
    }

    public void adicionarTextoById(String texto, int id){
        onView(withId(id)).perform(typeText(texto));
    }

    public void limparTextoById(int id){
        onView(withId(id)).perform(clearText());
    }

    public void textoIgualById(String texto, int id){
        onView(withId(id)).check(matches(withText(equalTo(texto))));
    }

    public void textoDiferenteById(String texto, int id){
        onView(withId(id)).check(matches(withText(not(equalTo(texto)))));
    }

    public void contemTextoById(String texto, int id){
        onView(withId(id)).check(matches(withText(containsString(texto))));
    }

    public void naoContemTextoById(String texto, int id){
        onView(withId(id)).check(matches(withText(not(containsString(texto)))));
    }

    public void clicarAdapter(Class clazz, int posicao){
        onData(instanceOf(clazz)).atPosition(posicao).perform(click());
    }

    public void clicarAdapterById(Class clazz, int posicao, int id){
        onData(instanceOf(clazz)).inAdapterView(withId(id)).atPosition(posicao)
                .perform(click());
    }

}
