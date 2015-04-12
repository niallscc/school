function y = compMidpoint ( f, a, b, n )

%MIDP       approximate the definite integral of an arbitrary function
%           using the composite Midpoint rule (i.e., the open
%           Newton-Cotes quadrature rule with n=0)
%
%     calling sequences:
%             y = midp ( 'f', a, b, n )
%             midp ( 'f', a, b, n )
%
%     inputs:
%             f       string containing name of m-file defining integrand
%             a       lower limit of integration
%             b       upper limit of integration
%             n       number of uniformly sized subintervals into which
%                     integration interval is to be divided
%                     (the resulting approximation will require n
%                     function evaluations)
%
%     output:
%             y       approximate value of the definite integral of f(x)
%                     over the interval a < x < b
%
%     NOTE:
%             if compMidpoint is called with no output arguments, the approximate 
%             value of the definite integral of f(x) over the interval 
%             a < x < b will be displayed
%

    h = (b-a)/(2*n);
    x = linspace ( a+h, b-h, n );
    sum = 0.0;
    for i = 1:n
        sum = sum + feval ( f, x(i) );
    end

    y = 2 * h * sum;

    % problem 7 second derivative for fun1: ((6*x^2)-2)/(x^2 + 1)^3
    % problem 8 second derivative for fun2 with pi/4:  (2/((pi-4*x)^2)^(3/4)
    %                                           0   :  -(1/(4*(x^2)^(3/4)) 
    % problem 9 second derivative for fun3 d^2/dx^2(e^(sin(6 pi x))) = 18 pi^2 e^(sin(6 pi x)) (-2 sin(6 pi x)+cos(12 pi x)+1)
    
    %error1 = (((b-a)*h^2)/6)* ((6*b^2)-2)/(b^2 + 1)^3;
    %error2 = (((b-a)*h^2)/6)* (2/((pi-4*b)^2)^(3/4));
    %error3 = (((b-a)*h^2)/6)* -(1/(4*(b^2)^(3/4)));
    %error4 = (((b-a)*h^2)/6)* (18 * pi^2 *exp(sin(6 * pi * b)))+(-2 * sin(6 * pi * b))+(cos(12 * pi * b)+1);
   
end