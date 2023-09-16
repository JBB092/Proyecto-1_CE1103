// Store the Arduino pin associated with each input

// Select button is triggered when joystick is pressed
const byte PIN_BUTTON_SELECT = 6; 

const byte PIN_BUTTON_RIGHT = 3;
const byte PIN_BUTTON_UP = 2;
const byte PIN_BUTTON_DOWN = 4;
const byte PIN_BUTTON_LEFT = 5;

const byte PIN_ANALOG_X = 0;
const byte PIN_ANALOG_Y = 1;

void setup() {
  Serial.begin(9600);

  pinMode(PIN_BUTTON_RIGHT, INPUT);  

  pinMode(PIN_BUTTON_LEFT, INPUT);  

  pinMode(PIN_BUTTON_UP, INPUT);  

  pinMode(PIN_BUTTON_DOWN, INPUT);  

  pinMode(PIN_BUTTON_SELECT, INPUT);  
   
}

void loop() {
  if(digitalRead(PIN_BUTTON_UP)==LOW){
    Serial.print("u:");
    Serial.print(digitalRead(PIN_BUTTON_UP));
    Serial.print("\n");
    }
  if(digitalRead(PIN_BUTTON_LEFT)==LOW){
    Serial.print("l:");
    Serial.print(digitalRead(PIN_BUTTON_LEFT));
    Serial.print("\n");
    }
  if(digitalRead(PIN_BUTTON_DOWN)==LOW){
    Serial.print("d:");
    Serial.print(digitalRead(PIN_BUTTON_DOWN));
    Serial.print("\n");
    }
  if(digitalRead(PIN_BUTTON_RIGHT)==LOW){
    Serial.print("r");
    //Serial.print("r:");
    //Serial.print(digitalRead(PIN_BUTTON_RIGHT));
    //Serial.print("\n");
    }
   if(analogRead(PIN_ANALOG_X)>=530 and analogRead(PIN_ANALOG_Y)>=515 and analogRead(PIN_ANALOG_Y)<=530){
    Serial.print("+x");
    //Serial.print("+x:");
    //Serial.print(analogRead(PIN_ANALOG_X));
    //Serial.print("\n");
    }
   if(analogRead(PIN_ANALOG_X)<=515 and analogRead(PIN_ANALOG_Y)>=515 and analogRead(PIN_ANALOG_Y)<=530){
    Serial.print("-x");
    //Serial.print("-x:");
    //Serial.print(analogRead(PIN_ANALOG_X));
    //Serial.print("\n");
    }
   if(analogRead(PIN_ANALOG_Y)>=530 and analogRead(PIN_ANALOG_X)>=515 and analogRead(PIN_ANALOG_X)<=530){
    Serial.print("+y:");
    Serial.print(analogRead(PIN_ANALOG_Y));
    Serial.print("\n");
    }
   if(analogRead(PIN_ANALOG_Y)<=515 and analogRead(PIN_ANALOG_X)>=515 and analogRead(PIN_ANALOG_X)<=530){
    Serial.print("-y:");
    Serial.print(analogRead(PIN_ANALOG_Y));
    Serial.print("\n");
    }
  delay(250);
}
