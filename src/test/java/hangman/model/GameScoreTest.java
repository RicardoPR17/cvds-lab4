package hangman.model;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import hangman.model.gamescore.*;

/*
 * Clases de equivalencia
 * 
 * - OriginalScore
 *  No valores negativos para el puntaje
 *  Valores válidos [0, 100]
 *  Puntaje no alterado por las letras correctas
 *  Puntaje alterado por penalización, restar 10 puntos
 * 
 *  Fronteras:
 *  Para los valores negativos, probar -> -10
 *  Para los valores válidos, probar -> 0 y 100
 * 
 * - BonusScore
 *  No valores negativos para el puntaje
 *  Por letra correcta, sumar 10 puntos
 *  Por letra incorrecta, restar 5 puntos
 *  Valores válidos [0, ∞)
 * 
 *  Fronteras:
 *  Para los valores negativos, probar -> -5
 *  Para los valores válidos, probar -> 0
 * 
 * - PowerScore
 *  No valores negativos para el puntaje
 *  Por letra correcta, sumar 5^i puntos
 *  Por letra incorrecta, restar 8 puntos
 *  Valores válidos [0, 500]
 * 
 *  Fronteras:
 *  Para los valores negativos, probar -> -3
 *  Para valores válidos, probar -> 0 y 500
 *  Para puntajes no mayores a 500, probar -> 5^4
 * 
 * 
 */
public class GameScoreTest {
    private OriginalScore gameOriginal = new OriginalScore();
    private BonusScore gameBonus = new BonusScore();
    private PowerScore gamePower = new PowerScore();

    // OriginalScore
    @Test
    public void calculateScoreOriginal_PuntajeNegativo_PuntajeMinimo() {
        // Arrange
        int correctas = 0;
        int incorrectas = 11;
        // Act
        int puntaje = gameOriginal.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(0, puntaje);
    }

    @Test
    public void calculateScoreOriginal_PuntajeValido_Puntaje100() {
        // Arrange
        int correctas = 0;
        int incorrectas = 0;
        // Act
        int puntaje = gameOriginal.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(100, puntaje);
    }

    @Test
    public void calculateScoreOriginal_PuntajeValido_Puntaje0() {
        // Arrange
        int correctas = 0;
        int incorrectas = 10;
        // Act
        int puntaje = gameOriginal.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(0, puntaje);
    }

    @Test
    public void calculateScoreOriginal_PuntajeNoAlteradoCorrectas_PuntajeNoAlterado() {
        // Arrange
        int correctas = 7;
        int incorrectas = 0;
        // Act
        int puntaje = gameOriginal.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(100, puntaje);
    }

    @Test
    public void calculateScoreOriginal_PuntajeAlteradoIncorrectas_PuntajeAlterado() {
        // Arrange
        int correctas = 5;
        int incorrectas = 3;
        // Act
        int puntaje = gameOriginal.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(70, puntaje);
    }

    // BonusScore
    @Test
    public void calculateScoreBonus_PuntajeNegativo_PuntajeMinimo() {
        // Arrange
        int correctas = 0;
        int incorrectas = 1;
        // Act
        int puntaje = gameBonus.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(0, puntaje);
    }

    @Test
    public void calculateScoreBonus_BonusCorrecta_PuntajeIncrementado() {
        // Arrange
        int correctas = 5;
        int incorrectas = 0;
        // Act
        int puntaje = gameBonus.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(50, puntaje);
    }

    @Test
    public void calculateScoreBonus_PenalizacionIncorrecta_PuntajeDecrementado() {
        // Arrange
        int correctas = 3;
        int incorrectas = 2;
        // Act
        int puntaje = gameBonus.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(20, puntaje);
    }

    @Test
    public void calculateScoreBonus_ValoresValidos_PuntajeEn0() {
        // Arrange
        int correctas = 0;
        int incorrectas = 0;
        // Act
        int puntaje = gameBonus.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(0, puntaje);
    }

    // PowerScore
    @Test
    public void calculateScorePower_PuntajeNegativo_PuntajeMinimo() {
        // Arrange
        int correctas = 0;
        int incorrectas = 1;
        // Act
        int puntaje = gamePower.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(0, puntaje);
    }

    @Test
    public void calculateScorePower_BonusCorrecto_PuntajeIncrementado() {
        // Arrange
        int correctas = 1;
        int incorrectas = 0;
        // Act
        int puntaje = gamePower.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(5, puntaje);
    }

    @Test
    public void calculateScorePower_PenalizacionIncorrecta_PuntajeDecrementado() {
        // Arrange
        int correctas = 2;
        int incorrectas = 1;
        // Act
        int puntaje = gamePower.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(22, puntaje);
    }

    @Test
    public void calculateScorePower_PuntajeValido_Puntaje0() {
        // Arrange
        int correctas = 0;
        int incorrectas = 0;
        // Act
        int puntaje = gamePower.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(0, puntaje);
    }

    @Test
    public void calculateScorePower_PuntajeMaximo_Puntaje500() {
        // Arrange
        int correctas = 4;
        int incorrectas = 0;
        // Act
        int puntaje = gamePower.calculateScore(correctas, incorrectas);
        // Assert
        assertEquals(500, puntaje);
    }
}
