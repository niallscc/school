function y = AltDesignrk4(t,y,h,omega,A)
% One step of the Runge-Kutta order 4 method
   
   s1 = AltDesignFunction(t,y,omega,A);
   s2 = AltDesignFunction(t+h/2,y+(h*s1/2),omega,A);
   s3 = AltDesignFunction(t+h/2,y+(h*s2./2),omega,A);
   s4 = AltDesignFunction(t+h,y+(h*s3),omega,A);
   y = y+h*(s1+2*s2+2*s3+s4)/6;