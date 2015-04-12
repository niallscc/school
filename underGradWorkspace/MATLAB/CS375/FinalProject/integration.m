function integration()
    g = 9.81;
    l = 0.4; 
    phi(1)= (5*pi)/180;
    %phi(1)= 0;
    %syms x; 
    x=0;
    T= 2*sqrt(l/g) * int(1/sqrt( 1- cos(phi(1)/2)^2 * sin(x)^2), x, 0,pi);
   
    T

end
 