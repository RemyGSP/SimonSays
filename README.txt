En la aplicacion he puesto toda la logica del simon says y todo en un clase y luego para dibujar con el canvas los circulos lo hago desde otra
Estoy utilizando soundPool como pedistes para los sonidos, para añadir los circulos del canvas a un XML tienes que crear el objeto dentro del XML
seria algo asi     <com.guillem.simonsays.SimonSaysView
        android:id="@+id/simonSaysView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

Cuando se te esta enseñando la secuencia del simon says el circulo actual se vuelve negro para mas claridad visual y hace el sonido del circulo,
si fallas en un circulo el juego no te lo notificara hasta que hallas intentado el numero de circulos maximo por ejemplo si estas en el nivel 5
y fallas en el segundo circulo que pulses hasta que no pulses 5 circulos no te lo notifica

Los circulos hacen ruido al ser pulsados
He manejado la logica del simon says con un par de arrays una que se va rellenando con la sequencia del juego y otra que es la secuencia que va introduciendo
el jugador y las comparo para saber si ha acertado o no