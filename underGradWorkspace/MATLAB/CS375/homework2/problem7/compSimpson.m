function y = compSimpson ( f, a, b, n )

%SIMP       approximate the definite integral of an arbitrary function
%           using the composite Simpson's rule (i.e., the closed
%           Newton-Cotes quadrature rule with n=2)
%
%     calling sequences:
%             y = simp ( 'f', a, b, n )
%             simp ( 'f', a, b, n )
%
%     inputs:
%             f       string containing name of m-file defining integrand
%             a       lower limit of integration
%             b       upper limit of integration
%             n       number of uniformly sized subintervals into which
%                     integration interval is to be divided - must be even
%                     (the resulting approximation will require n+1
%                     function evaluations)
%
%     output:
%             y       approximate value of the definite integral of f(x)
%                     over the interval a < x < b
%
%     NOTE:
%             if SIMP is called with no output arguments, the approximate 
%             value of the definite integral of f(x) over the interval 
%             a < x < b will be displayed
%

    if ( rem(n,2) ~= 0 )
       disp ( 'n must be an even integer' )
       return
    end

    h = (b-a)/n;
    x = linspace ( a, b, n+1 );
    for i = 1:n+1
        fx(i) = feval ( f, x(i) );
    end
    w = [ 1 zeros(1,n-1) 1 ];
    w(2:2:n) = 4*ones(1,n/2);
    w(3:2:n-1) = 2*ones(1,n/2-1);

    y = (h/3) * sum ( w .* fx );
    % fourth derivative for fun1: 25*(5*x^4= 10*x^2+1)/(x^2+1)^5
    % fourth derivative for fun2: pi/4  : -(120/((pi-4*x)^2)^(7/4) 
    %                   for fun2: 0     : -(15/16*(x^2)^(7/4)
    % fourth derivative for fun3: 162*pi^4*e^(sin(6*pi*b))+(-4*sin(6*pi*b)-12*sin(18*pi*b)-24*cos(12*pi*b)+cos(24*pi*b)-1)
    
    %error1= (((b-a)*h^4)/180) * (25*(5*b^4-10*b^2+1))/(b^2+1)^5;
    %error2= (((b-a)*h^4)/180) * (-(120/((pi-4*b)^2)^(7/4)));
    %error3= (((b-a)*h^4)/180) * (-(15/16*(b^2)^(7/4)));
    %error4= (((b-a)*h^4)/180) * 162*pi^4*exp(sin(6*pi*b))+(-4*sin(6*pi*b)-12*sin(18*pi*b)-24*cos(12*pi*b)+cos(24*pi*b)-1);

end