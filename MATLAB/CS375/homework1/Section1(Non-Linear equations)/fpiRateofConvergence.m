function fpiRateofConvergence ()
%-----------------------
%This function calcuates the rate of convergence for
%the fixed point iteration method of root finding. 
%-----------------------

    r= 3.161826486551968;
    p=1;
    F= fpi();
    k=100;
   
    for i=1:(k-1)
        en= abs( F(i)-r);
        en1= abs(F(i+1)-r);
        
        E(i)=(en1)/(en^p);
        
    end

    plot(E);
    
end
