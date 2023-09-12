const int botonPin = 2; // Pin donde está conectado el botón
const int ledPin = 13;  // Pin donde está conectado el LED

void setup() {
  Serial.begin(9600);
  pinMode(botonPin, INPUT);  // Configura el pin del botón como entrada
  pinMode(ledPin, OUTPUT);   // Configura el pin del LED como salida
}

void loop() {
  int estadoBoton = digitalRead(botonPin);  // Lee el estado del botón (HIGH o LOW)

  // Si el botón se presiona (estado LOW), enciende el LED
  if (estadoBoton == LOW) {
    digitalWrite(ledPin, LOW); // Enciende el LED
  } else {
    Serial.print("LED encendido");
    Serial.print(" ");
    Serial.print(digitalRead(ledPin));
    Serial.print("\n");
    digitalWrite(ledPin, HIGH);  // Apaga el LED
    delay(350);
  }
}
