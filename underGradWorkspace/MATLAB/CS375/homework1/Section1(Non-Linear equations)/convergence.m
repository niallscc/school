% this program tests the convergence
% of the two methods used to find the roots
% of a non linear equation, specifically
% fixed point iteration and Newtons method. 

function convergence()
    format long;
    N = newton( 'x-4*sin(2*x)-3', '1-8*cos(2*x)', 3.25, 0.5*10^-5, 102 );
    F = fpi();
    
    k=100;
   
    for i=1:k
        E(i)=abs( F(i)-N(i));
        
    end
    
    semilogy(E);
end
