# Laboratorio 4 - HANGMAN
## CVDS 2023-1
#### Integrantes
+ Jessica Muñoz
+ Ricardo Pulido
## Desarrollo
### Parte I
1. Se clona el proyecto brindado en la guía del laboratorio con el código para el juego del ahorcado (Hangman en inglés) usando el comando `git clone <URL del repositorio>`.

2. Para la implementación de los cascarones del modelo, se crea la interfaz *GameScore* y 3 clases que la implementan que serán *OriginalScore*, *BonusScore* y *PowerScore*.
	* GameScore
	```
	package hangman.model.gamescore;

	public interface GameScore {
    		public int calculateScore(int correctCount, int incorrectCount);
	}
	```
	* OriginalScore
	```
	package hangman.model.gamescore;

	public class OriginalScore implements GameScore {
    		@Override
    		public int calculateScore(int correctCount, int incorrectCount) {
        		return 0;
    		}
	}
	```
	* BonusScore
	```
	package hangman.model.gamescore;

	public class BonusScore implements GameScore {
    		@Override
    		public int calculateScore(int correctCount, int incorrectCount) {
        		return 0;
    		}
	}
	```
	* PowerScore
	```
	package hangman.model.gamescore;

	public class PowerScore implements GameScore {
    		@Override
	    	public int calculateScore(int correctCount, int incorrectCount) {
	        return 0;
    		}
	}
	```
	
3. Ahora, siguiendo las indicaciones para cada modo de calcular el puntaje. Se implementa la especificación de las 3 clases de *GameScore*
	* GameScore
	```
	package hangman.model.gamescore;

	public interface GameScore {
	    /**
	     * @param correctCount   An integer that represent the number of correct guesses
	     * 
	     * @param incorrectCount An integer that represent the number of incorrect
	     *                       guesses
	     * 
	     * @return The actual score of the game
	     */
	    public int calculateScore(int correctCount, int incorrectCount);
	}
	```
	* OriginalScore
	```
	package hangman.model.gamescore;

	public class OriginalScore implements GameScore {
	    private int score = 100;
	    private int wrong_letter = 10;
	    private static final int min_score = 0;

	    /**
	     * Calculate the score of the game following the rules of the original mode:
	     * - Start with 100 points
	     * - The correct guesses have not bonus
	     * - The incorrect guesses have a penalty of 10 points
	     * - The minimum score is 0 points
	     /
	    @Override
	    public int calculateScore(int correctCount, int incorrectCount) {
		return 0;
	    }
	}
	```
	* BonusScore
	```
	package hangman.model.gamescore;

	public class BonusScore implements GameScore {
	    private int score = 0;
	    private static final int bonus = 10;
	    private static final int penalty = 5;
	    private static final int min_score = 0;

	    /**
	     * Calculate the score of the game following the rules of the bonus mode:
	     * - Start with 0 points
	     * - The correct guesses have a bonus of 10 points
	     * - The incorrect guesses have a penalty of 5 points
	     * - The minimum score is 0 points
	     /
	    @Override
	    public int calculateScore(int correctCount, int incorrectCount) {
		return 0;
	    }
	}
	```
	* PowerScore
	```
	package hangman.model.gamescore;

	import java.lang.Math;

	public class PowerScore implements GameScore {
	    private int score = 0;
	    private static final int bonus = 5;
	    private static final int penalty = 8;
	    private static final int min_score = 0;
	    private static final int max_score = 500;

	    /**
	     * Calculate the score of the game following the rules of the power mode:
	     * - Start with 0 points
	     * - The correct guesses have a bonus of 5 raise to the power of i, where i is
	     * each number of correct guesses
	     * - The incorrect guesses have a penalty of 8 points
	     * - The minimum score is 0 points
	     * - The maximum score is 500 points
	     /
	    @Override
	    public int calculateScore(int correctCount, int incorrectCount) {
		return 0;
	    }
	}
	```

4. Con estas modificaciones hechas, se realiza el commit correspondiente

![Commit especificación métodos](https://user-images.githubusercontent.com/123812772/222233842-6b26c7d9-da12-447e-bd2c-d37b97cae8e6.png)

5. Ahora, entramos al `pom.xml` para actualizar las dependencias de la versión del compilador de Java en 1.8 y la versión de JUnit a 4.13.2

![Actualización del pom.xml](https://user-images.githubusercontent.com/123812772/222234211-62631c9d-5b5f-4e1d-9bcf-0aa5d6173443.png)

6. Teniendo estas modificaciones presentes, seguimos con el diseño de las pruebas unitarias iniciando con la definición de las clases de equivalencia
	```
	Clases de equivalencia
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
	 ```

7. Siguiendo estas definiciones, pasamos a crear nuestras pruebas unitarias
	```
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
	```

8. Hecho esto, hacemos un nuevo commit con los cambios en las clases y el diseño de las pruebas

![Commit implementación pruebas](https://user-images.githubusercontent.com/123812772/222235522-3c1a1204-1b99-4971-a298-25eaa6cf938d.png)

9. Procedemos a implementar las clases con la lógica necesaria para cumplir con los requisitos indicados.

	* OriginalScore
	```
	package hangman.model.gamescore;

	public class OriginalScore implements GameScore {
	    private static final int score = 100;
	    private static final int penalty = 10;
	    private static final int min_score = 0;

	    /**
	     * Calculate the score of the game following the rules of the original mode:
	     * - Start with 100 points
	     * - The correct guesses have not bonus
	     * - The incorrect guesses have a penalty of 10 points
	     * - The minimum score is 0 points
	     */
	    @Override
	    public int calculateScore(int correctCount, int incorrectCount) {
		int actualScore;
		actualScore = score - incorrectCount * penalty;
		if (actualScore <= min_score) {
		    return min_score;
		}
		return actualScore;
	    }
	}
	```
	* BonusScore
	```
	package hangman.model.gamescore;

	public class BonusScore implements GameScore {
	    private static final int score = 0;
	    private static final int bonus = 10;
	    private static final int penalty = 5;
	    private static final int min_score = 0;

	    /**
	     * Calculate the score of the game following the rules of the bonus mode:
	     * - Start with 0 points
	     * - The correct guesses have a bonus of 10 points
	     * - The incorrect guesses have a penalty of 5 points
	     * - The minimum score is 0 points
	     */
	    @Override
	    public int calculateScore(int correctCount, int incorrectCount) {
		int actualScore;
		actualScore = score + correctCount * bonus - incorrectCount * penalty;

		if (actualScore < min_score) {
		    return min_score;
		}
		return actualScore;
	    }
	}
	```
	* PowerScore
	```
	package hangman.model.gamescore;

	import java.lang.Math;

	public class PowerScore implements GameScore {
	    private static final int score = 0;
	    private static final int bonus = 5;
	    private static final int penalty = 8;
	    private static final int min_score = 0;
	    private static final int max_score = 500;

	    /**
	     * Calculate the score of the game following the rules of the power mode:
	     * - Start with 0 points
	     * - The correct guesses have a bonus of 5 raise to the power of i, where i is
	     * each number of correct guesses
	     * - The incorrect guesses have a penalty of 8 points
	     * - The minimum score is 0 points
	     * - The maximum score is 500 points
	     */
	    @Override
	    public int calculateScore(int correctCount, int incorrectCount) {
		int actualScore;
		actualScore = score;

		for (int i = 1; i <= correctCount; i++) {
		    actualScore += Math.pow(bonus, i);
		}

		actualScore -= incorrectCount * penalty;

		if (actualScore < min_score) {
		    return min_score;
		} else if (actualScore > max_score) {
		    return max_score;
		}

		return actualScore;
	    }
	}
	```
Una vez completada la implementación, verificamos que las pruebas corran de forma satisfactoria

![Pruebas satisfactorias](https://user-images.githubusercontent.com/123812772/222236673-c27508db-c60d-4e0b-8212-82eddb3258b3.png)

10. Finalmente, hacemos un nuevo commit con la implementación del modelo

![Commit implementación del modelo](https://user-images.githubusercontent.com/123812772/222236871-619f2dc4-ed48-4a23-bbfe-7fd6dd7d0cdb.png)

11. Subimos los cambios al repositorio usando el comando `git push <URL> master` para acceder a los cambios de forma remota.


### Parte II
1. Entramos a la clase `HangmanFactoryMethod` se incluye la configuración del modo de juego  y en la clase de `HangmanDefaultFactoryMethod` se asigna el modo de `OriginalScore`.
	* HangmanFactoryMethod
	```
	package hangman.setup.factoryMethod;

	import hangman.model.Language;
	import hangman.model.dictionary.HangmanDictionary;
	import hangman.model.gamescore.GameScore;
	import hangman.view.HangmanPanel;

	abstract public class HangmanFactoryMethod {
	    abstract public Language createLanguage();

	    abstract public HangmanDictionary createDictionary();

	    abstract public HangmanPanel createHangmanPanel();

	    abstract public GameScore createGameScore();
	}
	```
	* HangmanDefaultFactoryMethod
	```
	package hangman.setup.factoryMethod;

	import hangman.controller.GameController;
	import hangman.model.English;
	import hangman.model.Language;
	import hangman.model.dictionary.EnglishDictionaryDataSource;
	import hangman.model.dictionary.HangmanDictionary;
	import hangman.model.gamescore.GameScore;
	import hangman.model.gamescore.OriginalScore;
	import hangman.view.HangmanPanel;
	import hangman.view.HangmanStickmanPanel;

	public class HangmanDefaultFactoryMethod extends HangmanFactoryMethod {
	    @Override
	    public Language createLanguage() {
		return new English();
	    }

	    @Override
	    public HangmanDictionary createDictionary() {
		return new EnglishDictionaryDataSource();
	    }

	    @Override
	    public HangmanPanel createHangmanPanel() {
		return new HangmanStickmanPanel();
	    }

	    @Override
	    public GameScore createGameScore() {
		return new OriginalScore();
	    }
	}
	```

2. Actualizamos las dependencias de Google Guice en el `pom.xml` a la versión 5.1.0

![Actualización Guice](https://user-images.githubusercontent.com/123812772/222238071-688e88c4-540e-42f2-8e5c-11af07ee9059.png)

3. En la clase de `SwingProject` modificamos el método main para que el juego inicie utilizando la inyección de dependencias de Guice

	```
	// method: main
    	// purpose: the entry-point to our application
    	public static void main(String[] args) {
        	createGUIUsingGuice().play();
    	}
	```

4. Para aplicar las inyecciones, se hacen las siguientes modificaciones para que se apliquen las inyecciones de dependencias en el juego
	* Modificar la clase `HangmanFactoryServices`, inyetando el modo de juego de BonusScore y el juego en idioma Español junto al modelo de Stickman
	```
	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */
	package hangman.setup.guice;

	import java.util.Dictionary;

	/**
	 *
	 * @author 2106913
	 */

	import hangman.model.French;
	import hangman.model.Spanish;
	import hangman.model.Language;
	import hangman.model.dictionary.HangmanDictionary;
	import hangman.model.dictionary.FrenchDictionaryDataSource;
	import hangman.model.dictionary.SpanishDictionaryDataSource;
	import hangman.view.*;
	import hangman.view.HangmanPanel;
	import hangman.model.gamescore.*;;

	public class HangmanFactoryServices extends com.google.inject.AbstractModule {

	    @Override
	    protected void configure() {
		/* Guice dependency injection */
		// bind(Interface.class).to(Concrete.class);
		bind(Language.class).to(Spanish.class);
		bind(HangmanDictionary.class).to(SpanishDictionaryDataSource.class);
		bind(HangmanPanel.class).to(HangmanStickmanPanel.class);
		bind(GameScore.class).to(BonusScore.class);
	    }
	}
	```
	* Modificamos la clase `GUI` creando el parámetro *gameScore* y lo añadimos en los 2 constructores, además en el método setup se envía como parámetro el modo de tomar el puntaje del juego.
	```
	private GameScore gameScore;
	...
	// Use Factory method
        public GUI(HangmanFactoryMethod factoryMethod) {
                this.language = factoryMethod.createLanguage();
                this.dictionary = factoryMethod.createDictionary();
                this.hangmanPanel = factoryMethod.createHangmanPanel();
                this.gameScore = factoryMethod.createGameScore();
        }

        @Inject
        // Use Guice constructor
        public GUI(Language language, HangmanDictionary dictionary, HangmanPanel hangmanPanel, GameScore gameScore) {
                this.language = language;
                this.dictionary = dictionary;
                this.hangmanPanel = hangmanPanel;
                this.gameScore = gameScore;
        }
	...
	// method: setup
        // purpose: Create the various panels (game screens) for our game
        // and attach them to the main frame.
        private void setup() {
	...
		GameModel gameModel = new GameModel(dictionary, gameScore);
	```
	* Modificamos la clase `GameModel` para que esta no se encargue del puntaje sino siga la configuración hecha en la inyección de dependencias. Añadiendo el parámetro de `score` y eliminando la lógica del OriginalScore de esta clase haciendo que sea acorde al modo de puntaje seleccionado para que inyecte la lógica necesaria.
	```
	private GameScore score;
	...
	public GameModel(HangmanDictionary dictionary, GameScore score) {
		// this.dictionary = new EnglishDictionaryDataSource();
		this.dictionary = dictionary;
		randomWord = selectRandomWord();
		randomWordCharArray = randomWord.toCharArray();
		this.score = score;
		incorrectCount = 0;
		correctCount = 0;
		gameScore = score.calculateScore(correctCount, incorrectCount);

    	}
	// method: reset
    	// purpose: reset this game model for a new game
    	public void reset() {
        	randomWord = selectRandomWord();
        	randomWordCharArray = randomWord.toCharArray();
        	incorrectCount = 0;
        	correctCount = 0;
        	gameScore = score.calculateScore(correctCount, incorrectCount);
    	}
	...
	// method: makeGuess
    	// purpose: check if user guess is in string. Return a
    	// list of positions if character is found in string
    	public ArrayList<Integer> makeGuess(String guess) {
        	char guessChar = guess.charAt(0);
        	ArrayList<Integer> positions = new ArrayList<>();
        	for (int i = 0; i < randomWordCharArray.length; i++) {
            	if (randomWordCharArray[i] == guessChar) {
	                positions.add(i);
	            }
	        }
	        if (positions.size() == 0) {
	            incorrectCount++;
	            gameScore = score.calculateScore(correctCount, incorrectCount);
	        } else {
	            correctCount += positions.size();
	            gameScore = score.calculateScore(correctCount, incorrectCount);
	        }
	        return positions;

    	}
	```
5. Se hace un ejemplo de la ejecución con el juego en español, la visualización de Stickman y con el modo de puntaje BonusScore.

![Pantalla inicial](https://user-images.githubusercontent.com/123812772/222242057-f8894daf-60f2-42d4-ad25-47e48c479346.png)
![Puntaje en 0](https://user-images.githubusercontent.com/123812772/222242263-4f0d72d3-b1ac-445c-8aa3-51dbb4dd9d47.png)
Al ingresar una letra correcta, el puntaje aumenta en 10 puntos
![Letra correcta](https://user-images.githubusercontent.com/123812772/222242435-9860aa78-6ad2-43b6-902d-2d7e7fb6e436.png)
Si ingresamos una letra incorrecta, restará 5 puntos
![Letra incorrecta](https://user-images.githubusercontent.com/123812772/222242607-8c1d28e9-a866-452a-9a13-bbd33793d809.png)

Antes de ingresar la última letra, se llevan 6 letras correctas y 3 errores. Esto debe dar un puntaje de 45
![Previo al final](https://user-images.githubusercontent.com/123812772/222243082-090afa0b-6450-49f1-aba9-fc85690105b5.png)

Al finalizar, nos muestra que el puntaje fue de 55
![Fin del juego](https://user-images.githubusercontent.com/123812772/222243198-7ebc8d41-7f8e-4a9b-bd19-7bb22cb5b442.png)


## Bibliografía
MVNRepository. (25 de enero del 2022). *Google Guice Core Library >> 5.1.0*. Recuperado de: <https://mvnrepository.com/artifact/com.google.inject/guice/5.1.0>
