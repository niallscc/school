function y = ACrk4(t,y,h,a)
% One step of the Runge-Kutta order 4 method
   
   s1 = ACFunction(t,y,a);
   s2 = ACFunction(t+h/2,y+(h*s1/2),a);
   s3 = ACFunction(t+h/2,y+(h*s2./2),a);
   s4 = ACFunction(t+h,y+(h*s3),a);
   y = y+h*(s1+2*s2+2*s3+s4)/6;