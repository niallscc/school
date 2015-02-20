function newtonRateofConvergence ()
%-----------------------
%This function calcuates the rate of convergence for
%Newton's method of root finding. 
%For this problem we are using a root of 3.161826486551968
%-----------------------
    r= 3.161826486551968;
    p=1;
    N = newton( 'x-4*sin(2*x)-3', '1-8*cos(2*x)', 3.25, 0.5*10^-5, 102 );
    
    k=100;
   
    for i=1:(k-1)
        en= abs( N(i)-r);
        en1= abs(N(i+1)-r);
        
        E(i)=(en1)/(en^p);
        
    end

    plot(E);


end