package com.uniandes.abcjobsgrp23;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isNotEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.uniandes.abcjobsgrp23.view.candidato.CandidatoRegisterActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CandidatoRegisterTest {

    @Rule
    public ActivityScenarioRule<CandidatoRegisterActivity> activityScenarioRule = new ActivityScenarioRule<>(CandidatoRegisterActivity.class);

    long timeoutInicial = 1500;


    //------------------------------- Input Hint validation -------------------------------//
    @Test
    public void checkPhoneFieldHint() {
        onView(withId(R.id.textInputPhone))
                .check(matches(hasDescendant(withHint(TestDataCandidate.PHONE_HINT))));
    }

    @Test
    public void checkEmailFieldHint() {
        onView(withId(R.id.textInputEmail))
                .check(matches(hasDescendant(withHint(TestDataCandidate.EMAIL_HINT))));
    }

    @Test
    public void checkCountryFieldHint() {
        onView(withId(R.id.textInputCountry))
                .check(matches(hasDescendant(withHint(TestDataCandidate.COUNTRY_HINT))));
    }

    @Test
    public void checkCityFieldHint() {
        onView(withId(R.id.textInputCity))
                .check(matches(hasDescendant(withHint(TestDataCandidate.CITY_HINT))));
    }

    @Test
    public void checkLanguagesFieldHint() {
        onView(withId(R.id.textInputLanguages))
                .check(matches(hasDescendant(withHint(TestDataCandidate.LANGUAGES_HINT))));
    }

    @Test
    public void checkTraitsFieldHint() {
        onView(withId(R.id.textInputTraits))
                .check(matches(hasDescendant(withHint(TestDataCandidate.TRAITS_HINT))));
    }

    @Test
    public void checkPasswordFieldHint() {
        onView(withId(R.id.textInputPassword))
                .check(matches(hasDescendant(withHint(TestDataCandidate.PASSWORD_HINT))));
    }

    @Test
    public void checkConfirmPasswordFieldHint() {
        onView(withId(R.id.textInputConfirmPassword))
                .check(matches(hasDescendant(withHint(TestDataCandidate.CONFIRM_PASSWORD_HINT))));
    }


    //------------------------------- Input is Writable check -------------------------------//
    @Test
    public void checkNameFieldIsWritable() {
        onView(withId(R.id.register_nombre_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.NOMBRE), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_nombre_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.NOMBRE)));
    }

    @Test
    public void checkAgeFieldIsWritable() {
        onView(withId(R.id.register_edad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EDAD), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_edad_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.EDAD)));
    }

    @Test
    public void checkPhoneFieldIsWritable() {
        onView(withId(R.id.register_telefono_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.TELEFONO), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_telefono_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.TELEFONO)));
    }

    @Test
    public void checkEmailFieldIsWritable() {
        onView(withId(R.id.register_correo_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EMAIL), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_correo_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.EMAIL)));
    }

    @Test
    public void checkCityFieldIsWritable() {
        onView(withId(R.id.register_ciudad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.CIUDAD), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_ciudad_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.CIUDAD)));
    }

    @Test
    public void checkLanguagesFieldIsWritable() {
        onView(withId(R.id.register_idiomas_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.IDIOMAS), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_idiomas_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.IDIOMAS)));
    }

    @Test
    public void checkTraitsFieldIsWritable() {
        onView(withId(R.id.register_rasgos_personales_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.RASGOS_PERSONALES), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_rasgos_personales_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.RASGOS_PERSONALES)));
    }

    @Test
    public void checkPasswordFieldIsWritable() {
        onView(withId(R.id.register_pasw_1_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.PASSWORD), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_pasw_1_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.PASSWORD)));
    }

    @Test
    public void checkConfirmPasswordFieldIsWritable() {
        onView(withId(R.id.register_pasw_2_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.PASSWORD), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_pasw_2_candidato))
                .check(ViewAssertions.matches(ViewMatchers.withText(TestDataCandidate.PASSWORD)));
    }


    //------------------------------- Registration Button Disabled With some fields check -------------------------------//

    @Test
    public void testRegistrationButtonDisabledWithOneField() {
        onView(withId(R.id.register_nombre_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.NOMBRE), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonRegisterCandidato))
                .check(ViewAssertions.matches(not(ViewMatchers.isEnabled())));
    }

    @Test
    public void testRegistrationButtonDisabledWithTwoFields() {
        onView(withId(R.id.register_nombre_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.NOMBRE), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_edad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EDAD), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonRegisterCandidato))
                .check(ViewAssertions.matches(not(ViewMatchers.isEnabled())));
    }

    @Test
    public void testRegistrationButtonDisabledWithThreeFields() {
        onView(withId(R.id.register_nombre_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.NOMBRE), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_edad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EDAD), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.register_telefono_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.TELEFONO), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.buttonRegisterCandidato))
                .check(ViewAssertions.matches(not(ViewMatchers.isEnabled())));
    }


    //------------------------------- Registration Button EnabledWhen Passwords Match check -------------------------------//

    @Test
    public void checkRegistrationButtonEnabledWhenPasswordsMatchAndAllFieldsFilled() {
        // Ingresa datos en los campos de entrada
        onView(ViewMatchers.withId(R.id.register_nombre_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.NOMBRE), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_edad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EDAD), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_telefono_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.TELEFONO), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_correo_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EMAIL), ViewActions.closeSoftKeyboard());

        // Haz clic en el campo de país para abrir el dropdown
        onView(withId(R.id.register_pais_candidato)).perform(ViewActions.click());
        // Luego, selecciona un elemento del menú desplegable por su texto (por ejemplo, "Colombia")
        onView(withId(R.id.register_pais_candidato)).perform(replaceText(TestDataCandidate.COLOMBIA), closeSoftKeyboard());
        onView(withId(R.id.register_ciudad_candidato)).perform(ViewActions.click());

        onView(ViewMatchers.withId(R.id.register_ciudad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.CIUDAD), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_idiomas_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.IDIOMAS), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_rasgos_personales_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.RASGOS_PERSONALES), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.PASSWORD), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_pasw_2_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.PASSWORD), ViewActions.closeSoftKeyboard());

        // Asegúrate de que el botón de registro esté habilitado
        onView(ViewMatchers.withId(R.id.buttonRegisterCandidato))
                .check(ViewAssertions.matches(ViewMatchers.isEnabled()));
    }

    @Test
    public void checkRegistrationButtonDisabledWhenPasswordsMismatch() {
        // Ingresa datos en los campos de entrada, incluyendo contraseñas diferentes
        onView(ViewMatchers.withId(R.id.register_nombre_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.NOMBRE), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_edad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EDAD), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_telefono_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.TELEFONO), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_correo_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.EMAIL), ViewActions.closeSoftKeyboard());

        // Haz clic en el campo de país para abrir el dropdown
        onView(withId(R.id.register_pais_candidato)).perform(ViewActions.click());
        // Luego, selecciona un elemento del menú desplegable por su texto (por ejemplo, "Colombia")
        onView(withId(R.id.register_pais_candidato)).perform(replaceText(TestDataCandidate.COLOMBIA), closeSoftKeyboard());
        onView(withId(R.id.register_ciudad_candidato)).perform(ViewActions.click());

        onView(ViewMatchers.withId(R.id.register_ciudad_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.CIUDAD), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_idiomas_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.IDIOMAS), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_rasgos_personales_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.RASGOS_PERSONALES), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_pasw_1_candidato))
                .perform(ViewActions.typeText(TestDataCandidate.PASSWORD), ViewActions.closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.register_pasw_2_candidato))
                .perform(ViewActions.typeText("differentpassword"), ViewActions.closeSoftKeyboard());

        // Asegúrate de que el botón de registro esté deshabilitado
        onView(ViewMatchers.withId(R.id.buttonRegisterCandidato))
                .check(ViewAssertions.matches(ViewMatchers.isNotEnabled()));
    }

    //------------------------------- buttonRegisterCandidato -------------------------------//

    @Test
    public void checkBtRegisterCandidatoIsClickable() throws InterruptedException {
        onView(
                allOf(withId(R.id.buttonRegisterCandidato), withText(R.string.registrarse),
                        isDisplayed()
                )
        ).check(matches(isClickable()));
    }

    @Test
    public void checkInitBtRegisterCandidatoIsNotEnabled() throws InterruptedException {
        onView(
                allOf(withId(R.id.buttonRegisterCandidato), withText(R.string.registrarse),
                        isDisplayed()
                )
        ).check(matches(isNotEnabled()));
    }

    @Test
    public void checkBtBtRegisterCandidatoIsDisplayed() throws InterruptedException {
        onView(
                allOf(withId(R.id.buttonRegisterCandidato), withText(R.string.registrarse),
                        isDisplayed()
                )
        ).check(matches(isDisplayed()));
    }

    @Test
    public void testEnableBtRegisterAfterFillingFields() {
        // Ingresa texto en los campos de entrada utilizando las variables reutilizables
        onView(withId(R.id.register_nombre_candidato)).perform(typeText(TestDataCandidate.NOMBRE), closeSoftKeyboard());
        onView(withId(R.id.register_edad_candidato)).perform(typeText(TestDataCandidate.EDAD), closeSoftKeyboard());
        onView(withId(R.id.register_telefono_candidato)).perform(typeText(TestDataCandidate.TELEFONO), closeSoftKeyboard());
        onView(withId(R.id.register_correo_candidato)).perform(typeText(TestDataCandidate.EMAIL), closeSoftKeyboard());

        // Haz clic en el campo de país para abrir el dropdown
        onView(withId(R.id.register_pais_candidato)).perform(ViewActions.click());
        // Luego, selecciona un elemento del menú desplegable por su texto (por ejemplo, "Colombia")
        onView(withId(R.id.register_pais_candidato)).perform(replaceText(TestDataCandidate.COLOMBIA), closeSoftKeyboard());

        onView(withId(R.id.register_ciudad_candidato)).perform(ViewActions.click());
        onView(withId(R.id.register_ciudad_candidato)).perform(typeText(TestDataCandidate.CIUDAD), closeSoftKeyboard());
        onView(withId(R.id.register_idiomas_candidato)).perform(typeText(TestDataCandidate.IDIOMAS), closeSoftKeyboard());
        onView(withId(R.id.register_rasgos_personales_candidato)).perform(typeText(TestDataCandidate.RASGOS_PERSONALES), closeSoftKeyboard());
        onView(withId(R.id.register_pasw_1_candidato)).perform(typeText(TestDataCandidate.PASSWORD), closeSoftKeyboard());
        onView(withId(R.id.register_pasw_2_candidato)).perform(typeText(TestDataCandidate.PASSWORD), closeSoftKeyboard());

        // Verifica que el botón de registro esté habilitado
        onView(allOf(withId(R.id.buttonRegisterCandidato), withText(R.string.registrarse), isClickable()));
    }

    //------------------------------- buttonCancelRegisterCandidato -------------------------------//
    @Test
    public void  checkBtCancelRegisterCandidatoIsClickable() throws InterruptedException {
        onView(
                allOf(withId(R.id.buttonCancelRegisterCandidato), withText(R.string.cancelar),
                        isDisplayed()
                )
        ).check(matches(isClickable()));
    }

    @Test
    public void  checkBtCancelRegisterCandidatoIsEnabled() throws InterruptedException {
        onView(
                allOf(withId(R.id.buttonCancelRegisterCandidato), withText(R.string.cancelar),
                        isDisplayed()
                )
        ).check(matches(isEnabled()));
    }

    @Test
    public void  checkBtCancelRegisterCandidatoIsDisplayed() throws InterruptedException {
        onView(
                allOf(withId(R.id.buttonCancelRegisterCandidato), withText(R.string.cancelar),
                        isDisplayed()
                )
        ).check(matches(isDisplayed()));
    }




}
