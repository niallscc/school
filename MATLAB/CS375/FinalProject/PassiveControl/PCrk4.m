function y = PCrk4(t,w,h)
% One step of the Runge-Kutta order 4 method
   
   s1 = PassiveControlFunction(t,w);
   s2 = PassiveControlFunction(t+h/2,w+(h*s1/2));
   s3 = PassiveControlFunction(t+h/2,w+(h*s2./2));
   s4 = PassiveControlFunction(t+h,w+(h*s3));
   y = w+h*(s1+2*s2+2*s3+s4)/6;


