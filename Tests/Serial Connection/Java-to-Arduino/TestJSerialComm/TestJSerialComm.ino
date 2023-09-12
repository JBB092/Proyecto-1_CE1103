void setup() {
  Serial.begin(9600);
  pinMode(12,OUTPUT);
}
//----------------------------------------
void loop() {
  byte inByte;
  if(Serial.available()){
    inByte=Serial.read();
    for(byte i=1; i<=inByte*2;i++){
      digitalWrite(12, !digitalRead(12));
      delay(200);
      }
    }

}
