package com.uniandes.abcjobsgrp23;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.text.TextUtils;
import android.widget.TextView;

import com.uniandes.abcjobsgrp23.view.candidato.CandidatoLoginActivity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class CandidatoLoguinTest {

    @Rule
    public ActivityScenarioRule<CandidatoLoginActivity> activityScenarioRule = new ActivityScenarioRule<>(CandidatoLoginActivity.class);

    private static final String TEXT_TO_ENTER = "usuarioEjemplo";
    private static final String PASSWORD_TEXT = "passwordEjemplo";
        long timeoutInicial = 1500;
        long timeoutDisplay = 500;

    @Test
    public void findByIdAlbumViewIsDisplayed()  throws InterruptedException {
        Thread.sleep(timeoutInicial);
        onView(withId(R.id.candidato_login)).check(matches(isDisplayed()));
    }

    @Test
    public void findByIdAlbumViewIsEnabled() throws InterruptedException {
        Thread.sleep(timeoutInicial);
        onView(withId(R.id.candidato_login))
                .check(matches(isEnabled()));
    }

    //------------------------------- InputUsername -------------------------------//
    @Test
    public void checkInputUsernameFieldHint() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername))
                .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.nombre_candidato)));
    }

    @Test
    public void checkInputUsernameFieldIsWritable() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername))
                .perform(ViewActions.typeText(TEXT_TO_ENTER), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername))
                .check(ViewAssertions.matches(ViewMatchers.withText(TEXT_TO_ENTER)));
    }


    //------------------------------- InputPassword -------------------------------//
    @Test
    public void checkInputPasswordFieldHint() {
        // Asegúrate de que el campo de contraseña muestre el texto de sugerencia correcto
        Espresso.onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withHint(R.string.password)));
    }

    @Test
    public void checkInputPasswordFieldIsWritableAndHidden() {
        Espresso.onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .perform(ViewActions.typeText(PASSWORD_TEXT), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(PASSWORD_TEXT)));

        Espresso.onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .check((view, noViewFoundException) -> {
                    CharSequence passwordText = ((TextView) view).getText();
                    // Verifica que el texto no esté vacío y que no contenga el texto sin ocultar
                    if (!TextUtils.isEmpty(passwordText) && !passwordText.toString().contains(PASSWORD_TEXT)) {
                        throw new AssertionError("La contraseña no está oculta.");
                    }
                });
    }

    //------------------------------- BtIniciarSeccion -------------------------------//

    @Test
    public void checkBtIniciarSeccionCandidatoIsClickable() throws InterruptedException {
//        Thread.sleep(timeoutInicial);
        onView(
                allOf(withId(R.id.btnLoginCandidato), withText(R.string.iniciar_sesion),
                        isDisplayed()
                )
        ).check(matches(isClickable()));
    }

    @Test
    public void checkInitBtIniciarSeccionCandidatoIsNotEnabled() throws InterruptedException {
//        Thread.sleep(timeoutInicial);
        onView(
                allOf(withId(R.id.btnLoginCandidato), withText(R.string.iniciar_sesion),
                        isDisplayed()
                )
        ).check(matches(isNotEnabled()));
    }

    @Test
    public void checkBtBtIniciarSeccionCandidatoIsDisplayed() throws InterruptedException {
        Thread.sleep(timeoutInicial);
        onView(
                allOf(withId(R.id.btnLoginCandidato), withText(R.string.iniciar_sesion),
                        isDisplayed()
                )
        ).check(matches(isDisplayed()));
    }

    @Test
    public void testLoginButtonIsDisabledWithOneFieldFilled() {
        // Ingresa texto solo en uno de los campos de entrada (en este caso, solo en el campo de nombre de usuario)
        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername))
                .perform(ViewActions.typeText(TEXT_TO_ENTER), ViewActions.closeSoftKeyboard());

        // Verifica que el botón de inicio de sesión esté deshabilitado
        Espresso.onView(ViewMatchers.withId(R.id.btnLoginCandidato))
                .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }

    @Test
    public void testLoginButtonIsEnabledAfterFillingFields() {
        // Ingresa texto en los campos de entrada
        Espresso.onView(ViewMatchers.withId(R.id.editTextUsername))
                .perform(ViewActions.typeText(TEXT_TO_ENTER), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .perform(ViewActions.typeText(PASSWORD_TEXT), ViewActions.closeSoftKeyboard());

        // Verifica que el botón de inicio de sesión esté habilitado
        Espresso.onView(ViewMatchers.withId(R.id.btnLoginCandidato))
                .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    //------------------------------- BtRegistrarse -------------------------------//
    @Test
    public void  checkBtRegistrarseCandidatoIsClickable() throws InterruptedException {
        Thread.sleep(timeoutInicial);
        onView(
                allOf(withId(R.id.btnRegisterCandidato), withText(R.string.registrarse),
                        isDisplayed()
                )
        ).check(matches(isClickable()));
    }

    @Test
    public void  checkBtRegistrarseCandidatoIsEnabled() throws InterruptedException {
//        Thread.sleep(timeoutInicial);
        onView(
                allOf(withId(R.id.btnRegisterCandidato), withText(R.string.registrarse),
                        isDisplayed()
                )
        ).check(matches(isEnabled()));
    }

    @Test
    public void  checkBtRegistrarseCandidatoIsDisplayed() throws InterruptedException {
//        Thread.sleep(timeoutInicial);
        onView(
                allOf(withId(R.id.btnRegisterCandidato), withText(R.string.registrarse),
                        isDisplayed()
                )
        ).check(matches(isDisplayed()));
    }

}
