
import network
import uasyncio
import time
import machine
from umqtt.simple import MQTTClient
import dht
import random 
global sub,led,proximity,ground,d
#Configuracion inicial de WiFi

class Alert:
  def __init__(self,temp,light,humidity,ground,proximity):
    self.temp = temp
    self.light = light
    self.humidity = humidity
    self.ground = ground
    self.proximity = proximity
  def __str__(self):
    return '{"temp":'+str(self.temp)+',"light":'+ str(self.light)+',"humidity":'+str(self.humidity)+',"ground":'+str(self.ground)+',"proximity":'+str(self.proximity)+"}"
    
def sub_cb(topic, msg):
  print(topic, msg)
      
async def taskC():
  t,h,r=0,0,0
  while True:
    t,h = callDht()
    r = resistence()
    g = 10 if ground.value() == 1 else 25
    p = 5 if proximity.value() == 0 else 23
    alert = Alert(t,r,h,g,p)
    sub.publish(topic="a:a/kafka/data",msg=str(alert))
    await uasyncio.sleep_ms(10000)

def callDht():
  global temperature,humidity,d
  temperature = d.temperature()
  humidity = d.humidity()
  return temperature,humidity
def resistence():
  return photoResistence.read()

led = machine.Pin(5,machine.Pin.OUT)
proximity = machine.Pin(12,machine.Pin.IN)
ground = machine.Pin(0,machine.Pin.IN)
photoResistence = machine.ADC(0)
d = dht.DHT11(machine.Pin(14))
d.measure()

ssid = 'Ubee34E9_EXT'  #Nombre de la Red
password = '5BADF034E9' #Red Password 
wlan = network.WLAN(network.STA_IF)
wlan.active(True) #Activa el Wifi
wlan.connect(ssid, password) #Hace la conexion
sub = MQTTClient("unique-identifier","3.238.238.178",user="a",password="a",port=1884)
sub.set_callback(sub_cb)
sub.connect()
print("Conectado")
#sub.subscribe('dwkfbgqo:dwkfbgqo/controller')

while(wlan.isconnected() == False):
  pass
print(wlan.ifconfig())
led.on()
loop = uasyncio.get_event_loop()
loop.create_task(taskC())
loop.run_forever()
