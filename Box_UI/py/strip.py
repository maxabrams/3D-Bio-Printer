import sys
import pigpio

pi = pigpio.pi()
pi.set_PWM_dutycycle(float(sys.argv[1]),float(sys.argv[4]))
pi.set_PWM_dutycycle(float(sys.argv[2]),float(sys.argv[5]))
pi.set_PWM_dutycycle(float(sys.argv[3]),float(sys.argv[6]))
