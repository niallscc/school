%-----------
% g = function to call
% x0= starting value 
% k = max number to go to. 
% good starting values were found by
% plotting plot(x, x-4*sin(2*x)-3)
% using x = -pi:pi/100:4*pi;
% which gives us x0 values of: -.75,-.5, .6, 3.25, and 4.5
% root1= -0.544442400680698
% root2= 3.161826486551968
%-----------
function X = fpi()
%function xc =fpi()
    format long; 
    k=100;
    x0=3.25;
    
    function y= g(x) 
        y = -sin(2*x) + ((5/4)*x) -(3/4); 
    end

    X(1) = x0;
    x1 = x0;
    
    for i=1:k
        x1 = g(x0);
        x0 = x1;
        X(i+1) = x1;
    end
    
    display (x1);
    plot(X);
end
    
    
