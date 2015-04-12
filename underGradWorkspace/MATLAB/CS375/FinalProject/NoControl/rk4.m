function y = rk4(t,w,h)
% One step of the Runge-Kutta order 4 method
   
   s1 = NoControlFunction(t,w);
   s2 = NoControlFunction(t+h/2,w+(h*s1/2));
   s3 = NoControlFunction(t+h/2,w+(h*s2./2));
   s4 = NoControlFunction(t+h,w+(h*s3));
   y = w+h*(s1+2*s2+2*s3+s4)/6;


