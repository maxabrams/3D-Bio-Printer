import RPi.GPIO as GPIO

pin = sys.argv[1] 
GPIO.setwarnings(False) 
GPIO.setmode(GPIO.BCM)

GPIO.setup(pin, GPIO.OUT)
GPIO.output(pin, GPIO.LOW)
